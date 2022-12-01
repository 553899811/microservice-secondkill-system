create table orders
(
    id                bigint auto_increment
        primary key,
    order_id          bigint            not null comment '订单号',
    user_id           bigint            not null comment '用户ID',
    order_price       bigint            not null comment '订单实付金额',
    payment_trade_num varchar(64)       null comment '第三方交易流水号',
    yn                tinyint default 1 not null,
    created           datetime          not null comment '创建时间',
    modified          datetime          not null comment '更新时间'
);

create index idx_order_id_user_id_created
    on orders (id, user_id, created);

INSERT INTO secondkill.orders (id, order_id, user_id, order_price, payment_trade_num, yn, created, modified) VALUES (1452536688767557634, 9022162152578248, 1278, 1, null, 1, '2021-10-25 07:25:08', '2021-10-25 07:25:08');
INSERT INTO secondkill.orders (id, order_id, user_id, order_price, payment_trade_num, yn, created, modified) VALUES (1452548868917309442, 9022283953866174, 1278, 1, null, 1, '2021-10-25 08:13:32', '2021-10-25 08:13:32');
INSERT INTO secondkill.orders (id, order_id, user_id, order_price, payment_trade_num, yn, created, modified) VALUES (1452549063478489089, 9022285899687686, 1278, 1, null, 1, '2021-10-25 08:14:18', '2021-10-25 08:14:18');
INSERT INTO secondkill.orders (id, order_id, user_id, order_price, payment_trade_num, yn, created, modified) VALUES (1452550271706484737, 9022297981799788, 1278, 1, null, 1, '2021-10-25 08:19:07', '2021-10-25 08:19:07');
INSERT INTO secondkill.orders (id, order_id, user_id, order_price, payment_trade_num, yn, created, modified) VALUES (1452571878575509506, 9022514049944285, 1278, 1, null, 1, '2021-10-25 09:44:58', '2021-10-25 09:44:58');
INSERT INTO secondkill.orders (id, order_id, user_id, order_price, payment_trade_num, yn, created, modified) VALUES (1452573668159164418, 9022531946326421, 1278, 1, null, 1, '2021-10-25 09:52:05', '2021-10-25 09:52:05');