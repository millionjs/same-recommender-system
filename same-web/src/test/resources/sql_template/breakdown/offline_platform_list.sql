create table offline_platform_list
(
    id                           int auto_increment comment '主键'
        primary key,
    name               varchar(300)                              null comment '类型名称',
    type               varchar(300)                         null comment '具体类型。',
    created_time            timestamp default CURRENT_TIMESTAMP not null comment '任务的创建时间。',
    modified_time           timestamp default CURRENT_TIMESTAMP not null comment '任务的修改时间。'

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT = '类型枚举列表保存处。';