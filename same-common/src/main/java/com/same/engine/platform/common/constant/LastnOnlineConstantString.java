package com.same.engine.platform.common.constant;

import lombok.Data;


@Data
public class LastnOnlineConstantString {

    // region 定义默认的SOURCE模块
    public static final String ONLINE_ACTION_SOURCE = "CREATE TABLE `source` (\n" +
            "  `user` ROW < user_id STRING,\n" +
            "  hash_user_id string >,\n" +
            "  page ROW < page_instance string,\n" +
            "  duration_ms int,\n" +
            "  source_str string >,\n" +
            "  event ROW < action string,\n" +
            "  bind_id int,\n" +
            "  dvce_micro_ts bigint >,\n" +
            "  note_target ROW < note_id string, author_id string>,\n" +
            "  user_target ROW < user_id string >,\n" +
            "  row_time bigint,\n" +
            "  event_time TIMESTAMP(3),\n" +
            "  proc_time AS PROCTIME(),\n" +
            "  WATERMARK FOR event_time AS event_time - INTERVAL '10' SECOND\n" +
            ") WITH (\n" +
            "  'connector' = 'kafka',\n" +
            "  'topic' = 'dwd_soc_feed_lastn',\n" +
            "  'scan.startup.mode' = 'latest-offset',\n" +
            "  'properties.kafka.cluster.name' = 'kafka_qcsh4_rlmubt1',\n" +
            "  'format' = 'raw-tracker',\n" +
            "  'raw-tracker.pb.type' = 'FRONTEND'\n" +
            ");";
    // endregion

    // region 定义默认的逻辑模块
    public static final String ONLINE_BREAKDOWN_LOGIC = "create view `lastn_common_source` as\n" +
            "select\n" +
            "  *\n" +
            "from\n" +
            "  (\n" +
            "    SELECT\n" +
            "      floor(`event`.dvce_micro_ts / 1000000) AS ts,\n" +
            "      `user`.user_id AS uid,\n" +
            "      `event`.bind_id AS bind_id,\n" +
            "      case when `event`.bind_id in (70586) then `page`.page_instance\n" +
            "           else 'others' \n" +
            "      end as source_str,\n" +
            "      case when `event`.bind_id in (70586) then 'PT' \n" +
            "           else 'others' \n" +
            "      end as action,\n" +
            "      page.duration_ms AS duration,\n" +
            "\n" +
            "      proc_time\n" +
            "    FROM\n" +
            "      source\n" +
            "    where\n" +
            "       `event`.bind_id in (70586)\n" +
            "       and page.duration_ms > 0\n" +
            "  ) tmp;";
    // endregion


    // region 定义默认的Online模块 Sink
    public static final String ONLINE_BREAKDOWN_SINK = "insert into\n" +
            "  redkv_sink\n" +
            "select\n" +
            "  CONCAT('{USER:', uid,  ':' , action ,':SE}'),\n" +
            "  CONCAT_WS(\n" +
            "    ':',\n" +
            "    cast(ts as string),\n" +
            "    cast(duration as string),\n" +
            "    -- author_id,\n" +
            "    to_rec_lastn(source_str, 0)\n" +
            "  )\n" +
            "from\n" +
            "  lastn_common_source;";
    // endregion

    // region 定义默认的Online模块 REKV。
    public static final String ONLINE_BREAKDOWN_REDKV = "" +
            "CREATE TABLE redkv_sink (`key` VARCHAR, `value` VARCHAR) with (\n" +
            "    'connector.type' = 'redis',\n" +
            "    'connector.redis_prefix' = '',\n" +
            "    'connector.sink.catch_null_key_error'='true',\n" +
            "    --业务前缀，防止和其他业务key冲突\n" +
            "    'connector.password' = '',\n" +
            "    --密码\n" +
            "    'connector.port' = '26459',\n" +
            "    --端口\n" +
            "    'connector.host' = '127.0.0.1',\n" +
            "    --host\n" +
            "    --ttl时间，单位是秒\n" +
            "    'connector.timeout' = '2000',\n" +
            "    'connector.output_format_type' = 'LIST',\n" +
            "    'connector.output_batch_size' = '100'\n" +
            "  );";
    // endregion

}
