create table same_platform_project
(
    id                       int auto_increment comment '主键'
        primary key,
    job_name                 varchar(300)                        null comment '任务名称。',
    job_detail               text                                null comment '任务明细信息',
    job_other_info           text                                null comment '任务额外信息',
    job_type                 varchar(300)                        null comment '任务类型信息',
    job_description          varchar(1000)                       null comment '任务描述信息。',
    job_config_id            int       default 0                 null comment '任务的版本。',
    job_biz_line             varchar(1000)                       null comment '任务业务线。',
    job_scene                varchar(1000)                       null comment '任务场景。',
    job_dimension            varchar(300)                        null comment '任务维度',
    job_status               varchar(300)                        null comment '任务状态',
    online_job_url           varchar(1000)                       null comment '「实时」任务地址。',
    offline_job_url          varchar(1000)                       null comment '「离线」任务地址。',
    online_job_status        varchar(300)                        null comment '「实时」任务状态',
    offline_job_status       varchar(300)                        null comment '「离线」任务状态',
    creator                  varchar(300)                        null comment '任务创建着',
    owner                    varchar(300)                        null comment '任务所有者',
    members                  varchar(1000)                       null comment '任务成员',
    is_delete                int       default 0                 null comment '该任务是否被伪删除。',
    created_time             timestamp default CURRENT_TIMESTAMP not null comment '任务的创建时间。',
    modified_time            timestamp default CURRENT_TIMESTAMP not null comment '任务的修改时间。',
    job_version              int       default 0                 null comment '任务的版本。',
    deployment_type          varchar(300)                        null comment '部署类型。',
    work_flow_id             varchar(300)                        null comment 'workflow id信息',
    all_task_status_workflow int                                 null comment 'workflow 的结果信息',
    all_task_status          varchar(300)                        null comment '所有task的状态信息',
    all_task_status_success  int                                 null comment '所有task的成功信息。',
    all_task_status_number   int                                 null comment '本任务的 task数量。',
    latest_changer           varchar(300)                        null comment '最近修改人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT = 'Breakdown任务列表, 用于管理任务列表信息。';


alter table offline_platform_breakdown
    add feature_level varchar(100)  null comment '特征等级。';

