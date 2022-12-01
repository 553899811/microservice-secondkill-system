create table ware_item_stock
(
    id           int auto_increment
        primary key,
    ware_item_id int    default 0 not null,
    stock        int(4) default 0 not null,
    constraint item_id_index
        unique (ware_item_id)
);

INSERT INTO secondkill.ware_item_stock (id, ware_item_id, stock) VALUES (1, 0, 7);
INSERT INTO secondkill.ware_item_stock (id, ware_item_id, stock) VALUES (9, 1, 95);