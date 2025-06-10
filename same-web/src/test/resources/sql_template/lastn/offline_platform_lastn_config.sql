-- auto-generated definition
create table offline_platform_lastn_config
(
    id                 int auto_increment comment '主键'
        primary key,
    origin_job_id      int          default 0                 null comment '原始任务ID',
    config_name        varchar(300)                           null comment '配置名称',
    config_type        varchar(300)                           null comment '配置类型',
    config_description varchar(1000)                          null comment '配置描述',
    config_status      varchar(300) default '0'               null comment '配置状态',
    config_content     text                                   null comment '配置内容',
    config_other_info  text                                   null comment '任务额外信息',
    config_version     int          default 0                 null comment '配置版本',
    config_creator     varchar(300)                           null comment '配置创建者',
    config_members     varchar(1000)                          null comment '配置成员',
    is_delete          int          default 0                 null comment '是否删除',
    created_time       timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    modified_time      timestamp    default CURRENT_TIMESTAMP not null comment '修改时间',
    config_key         varchar(300)                           null comment '配置key',
    config_value       varchar(300)                           null comment '配置value',
    order_number       int                                    null comment '配置顺序',
    work_flow_id       varchar(300)                           null comment 'workflow的id',
    latest_changer     varchar(300)                           null comment '最近修改人',
    work_flow_version  varchar(300)                           null comment 'workflow的版本'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT = 'Lastn 任务配置信息。';

-- alter table offline_platform_lastn_config
--     modify config_status varchar(300) default 0 null comment '配置状态';