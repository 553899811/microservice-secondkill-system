create table sequence
(
    id            bigint        not null comment '主键ID',
    name          varchar(255)  not null
        primary key,
    current_value bigint        not null,
    step          int default 0 not null
);

