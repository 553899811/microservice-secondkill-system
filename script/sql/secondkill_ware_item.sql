create table ware_item
(
    id             bigint auto_increment,
    ware_item_id   bigint        not null comment '商品明细ID',
    sku_id         bigint        not null comment '商品SKU',
    price          bigint        not null comment '商品售价',
    sales          int           not null,
    description    varchar(1024) null comment '商品描述',
    img_url        varchar(2048) not null,
    ware_item_name varchar(1024) not null,
    constraint order_item_id_uindex
        unique (id)
)
    comment '商品明细表';

alter table ware_item
    add primary key (id);

INSERT INTO secondkill.ware_item (id, ware_item_id, sku_id, price, sales, description, img_url, ware_item_name) VALUES (1, 0, 1122, 2333, 10000000, '好吃的香肠', 'https://img.dmallcdn.com/20210604/2f47abb6-1947-4a67-ab1c-c269dbad8478', '哈尔滨香肠');
INSERT INTO secondkill.ware_item (id, ware_item_id, sku_id, price, sales, description, img_url, ware_item_name) VALUES (2, 1, 12233, 2333, 105, '好看的鲜花', 'https://img.dmallcdn.com/20210803/860d68cc-e9cd-4488-8693-e3bd90fd004f_100x100', '情人节玫瑰');