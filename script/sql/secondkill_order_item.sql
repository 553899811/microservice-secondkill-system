create table order_item
(
    id               bigint auto_increment
        primary key,
    order_id         bigint   not null comment '订单ID',
    order_item_id    bigint   not null comment '订单明细主键,订单维度唯一',
    ware_item_id     bigint   not null comment '商品明细ID',
    sku_id           bigint   not null comment '商品SKU',
    order_item_price bigint   not null comment '商品单价',
    quantity         int      not null comment '商品购买数量',
    yn               int      not null comment '数据是否有效',
    created          datetime not null comment '创建时间',
    modified         datetime not null comment '更新时间'
)
    comment '订单明细';

INSERT INTO secondkill.order_item (id, order_id, order_item_id, ware_item_id, sku_id, order_item_price, quantity, yn, created, modified) VALUES (1452548868950863873, 9022283953866174, 3866174, 1, 12233, 1, 1, 1, '2021-10-25 08:13:32', '2021-10-25 08:13:32');
INSERT INTO secondkill.order_item (id, order_id, order_item_id, ware_item_id, sku_id, order_item_price, quantity, yn, created, modified) VALUES (1452549063478489090, 9022285899687686, 9687686, 1, 12233, 1, 1, 1, '2021-10-25 08:14:18', '2021-10-25 08:14:18');
INSERT INTO secondkill.order_item (id, order_id, order_item_id, ware_item_id, sku_id, order_item_price, quantity, yn, created, modified) VALUES (1452550271748427778, 9022297981799788, 1799788, 1, 12233, 1, 1, 1, '2021-10-25 08:19:07', '2021-10-25 08:19:07');
INSERT INTO secondkill.order_item (id, order_id, order_item_id, ware_item_id, sku_id, order_item_price, quantity, yn, created, modified) VALUES (1452571878688755713, 9022514049944285, 9944285, 1, 12233, 1, 1, 1, '2021-10-25 09:44:58', '2021-10-25 09:44:58');
INSERT INTO secondkill.order_item (id, order_id, order_item_id, ware_item_id, sku_id, order_item_price, quantity, yn, created, modified) VALUES (1452573668205301762, 9022531946326421, 6326421, 1, 12233, 1, 1, 1, '2021-10-25 09:52:05', '2021-10-25 09:52:05');