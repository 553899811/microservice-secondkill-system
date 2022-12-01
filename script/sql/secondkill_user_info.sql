create table user_info
(
    id               bigint auto_increment comment '用户ID'
        primary key,
    user_id          bigint                 not null comment '用户ID',
    user_name        varchar(32) default '' not null comment '用户姓名',
    gender           tinyint     default 1  not null comment '// 1 代表男性 ;2 代表女性',
    age              int                    not null comment '年龄',
    tel_phone        varchar(20)            not null,
    register_mode    tinyint                not null comment '注册方式',
    third_party_id   varchar(9999)          null comment '第三方ID',
    third_party_type int                    null comment '第三方平台类型',
    email            varchar(256)           not null,
    constraint telphone_unique_index
        unique (tel_phone)
)
    comment '用户人信息';

INSERT INTO secondkill.user_info (id, user_id, user_name, gender, age, tel_phone, register_mode, third_party_id, third_party_type, email) VALUES (11, 1278, 'aaaa', 1, 2, '130', 1, '3333', 0, '');
INSERT INTO secondkill.user_info (id, user_id, user_name, gender, age, tel_phone, register_mode, third_party_id, third_party_type, email) VALUES (1421757427287625730, 8714362028288, 'Echizen', 1, 12, '13051792262', 1, null, null, '553899811@qq.com');