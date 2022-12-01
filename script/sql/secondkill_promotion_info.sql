create table promotion_info
(
    id                   int auto_increment
        primary key,
    ware_item_id         bigint       not null comment '商品明细ID',
    promotion_name       varchar(255) not null comment '促销名称',
    start_date           datetime     not null,
    end_date             datetime     not null,
    promotion_type       tinyint      not null comment '促销类型',
    promotion_status     tinyint      not null comment '促销状态',
    promotion_item_price bigint       not null comment '促销商品单价'
);

INSERT INTO secondkill.promotion_info (id, ware_item_id, promotion_name, start_date, end_date, promotion_type, promotion_status, promotion_item_price) VALUES (1, 1, '玫瑰花秒杀', '2021-08-03 14:39:34', '2021-08-04 17:08:34', 1, 2, 1);