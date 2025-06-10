
create table offline_platform_lastn_task
(
    id               int auto_increment comment '主键'
        primary key,
    origin_job_id    int       default 0                 null comment '原始任务ID',
    origin_config_id int       default 0                 null comment '原始配置ID',
    task_url         varchar(1000)                       null comment '任务地址。',
    task_name        varchar(300)                        null comment '子任务名称',
    task_type        varchar(300)                        null comment '子任务类型',
    task_description varchar(1000)                       null comment '子任务描述',
    task_status      varchar(300)                        null comment '子任务状态',
    task_content     text                                null comment '子任务内容',
    task_other_info  text                                null comment '子任务额外信息',
    task_version     int       default 0                 null comment '子任务版本',
    task_creator     varchar(300)                        null comment '子任务创建者',
    task_members     varchar(1000)                       null comment '子任务成员',
    is_delete        int       default 0                 null comment '是否删除',
    created_time     timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    modified_time    timestamp default CURRENT_TIMESTAMP not null comment '修改时间',
    latest_changer   varchar(300)                        null comment '最近修改人',
    work_flow_id     varchar(300)                        null comment 'workflow的id',
    task_finish      int                                 null comment 'task是否完结',
    stream_or_batch  varchar(300)                        null comment '流还是批'
)  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT = 'Lastn 子任务配置信息。';