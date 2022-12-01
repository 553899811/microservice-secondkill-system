create table user_password
(
    id               bigint auto_increment
        primary key,
    user_id          bigint       not null comment '用户ID',
    encrypt_password varchar(256) not null comment '密文密码',
    constraint idx_user_id
        unique (user_id)
);

INSERT INTO secondkill.user_password (id, user_id, encrypt_password) VALUES (1, 1234, '56789');
INSERT INTO secondkill.user_password (id, user_id, encrypt_password) VALUES (1421749261254524930, 8714283135232, '4QrcOUm6Wau+VuBX8g+IPg==');
INSERT INTO secondkill.user_password (id, user_id, encrypt_password) VALUES (1421752335100657666, 8714319200128, '4QrcOUm6Wau+VuBX8g+IPg==');
INSERT INTO secondkill.user_password (id, user_id, encrypt_password) VALUES (1421752532560101377, 8714329615360, '4QrcOUm6Wau+VuBX8g+IPg==');
INSERT INTO secondkill.user_password (id, user_id, encrypt_password) VALUES (1421752969354919937, 8714324433920, '4QrcOUm6Wau+VuBX8g+IPg==');
INSERT INTO secondkill.user_password (id, user_id, encrypt_password) VALUES (1421757427388289025, 8714362028288, '4QrcOUm6Wau+VuBX8g+IPg==');