package com.same.engine.platform.common.constant;

import lombok.Data;


@Data
public class BreakdownOfflineConstantString {

    // region 定义默认的离线模块
    public static final String OFFLINE_ACTION_SOURCE = "with action_event as (\n" +
            "    -- select 出对应需要的字段\n" +
            "    SELECT\n" +
            "      user_id,\n" +
            "      action_target as note_id,\n" +
            "      clicked,\n" +
            "      liked\n" +
            "    FROM\n" +
            "      -- 找到对应需要的Hive 表：https://crux2.devops.xiaohongshu.com/home\n" +
            "      reddw.dw_log_feed_engagement_day_inc\n" +
            "    WHERE\n" +
            "      -- 限定天级别的数据分区\n" +
            "      1 = 1\n" +
            "      and dtm = '{{ds_nodash}}'\n" +
            "      and page_key = 'homefeed'\n" +
            "    limit\n" +
            "      1000\n" +
            "  )";
    // endregion

    // region 定义默认的离线模块 base_event
    public static final String OFFLINE_BREAKDOWN_LOGIC = "breakdown_base AS (\n" +
            "    SELECT\n" +
            "      note_id as id,\n" +
            "      'E' as refer,\n" +
            "      Map ('total', '') as bucket_map,\n" +
            "      Map (\n" +
            "        'impression',\n" +
            "        1,\n" +
            "        'click',\n" +
            "        if (clicked > 0, 1, 0),\n" +
            "        'like',\n" +
            "        if (liked > 0, 1, 0)\n" +
            "      ) as action_map\n" +
            "    FROM\n" +
            "      action_event\n" +
            "  )";
    // endregion

    // region 定义默认的离线模块 breakdown_Sink table
    public static final String OFFLINE_HIVE_TABLE_CREATE = "--hive表创建\n" +
            "CREATE TABLE\n" +
            "  if not exists shequ_engine.simple_note_breakdown_origin_di (\n" +
            "    `id` string,\n" +
            "    `refer` string,\n" +
            "    `bucket_key` string,\n" +
            "    `bucket_value` string,\n" +
            "    `action_type` string,\n" +
            "    `v` bigint\n" +
            "  ) COMMENT '笔记breakdown逻辑层数据' PARTITIONED BY (`dtm` string COMMENT 'dtm') stored as parquet TBLPROPERTIES (\n" +
            "    -- 数据保存的天数\n" +
            "    'lifecycle.days' = '60',\n" +
            "    'migrationSource' = 'dlf',\n" +
            "    'parquet.compression' = 'ZSTD',\n" +
            "    'primary.keys' = '',\n" +
            "    'transient_lastDdlTime' = '1708499824'\n" +
            "  );";
    // endregion

    // region 写入 breakdown_Sink table
    public static final String OFFLINE_HIVE_TABLE_SINK = "-- 写入上面创建的Hive 表中。\n" +
            "insert overwrite table shequ_engine.simple_note_breakdown_origin_di partition (dtm = '{{ds_nodash}}')\n" +
            "select\n" +
            "  id,\n" +
            "  refer,\n" +
            "  bucket_key,\n" +
            "  bucket_value,\n" +
            "  action_key,\n" +
            "  sum(action_value) as v\n" +
            "from\n" +
            "  (\n" +
            "    select\n" +
            "      id,\n" +
            "      refer,\n" +
            "      bucket_key,\n" +
            "      bucket_value,\n" +
            "      action_key,\n" +
            "      action_value\n" +
            "    from\n" +
            "      breakdown_base lateral view explode (bucket_map) buckets as bucket_key,\n" +
            "      bucket_value lateral view explode (action_map) buckets as action_key,\n" +
            "      action_value\n" +
            "  ) t\n" +
            "where\n" +
            "  action_value > 0\n" +
            "group by\n" +
            "  id,\n" +
            "  refer,\n" +
            "  bucket_key,\n" +
            "  bucket_value,\n" +
            "  action_key";

    // endregion

}
