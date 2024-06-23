create table items
(
    id       serial primary key,
    name     varchar(50),
    count    integer default 0,
    price    integer
);

insert into items (name, count, price) VALUES ('item_1', 3, 50);
insert into items (name, count, price) VALUES ('item_2', 15, 32);
insert into items (name, count, price) VALUES ('item_3', 8, 115);

set session characteristics as transaction isolation level serializable;

begin transaction;

select * from items;

update items set price = 12;

savepoint first_savepoint;

delete from items where name = 'item_1';

savepoint second_savepoint;

insert into items (name, count, price) VALUES ('item_4', 4, 120);

select * from items;

savepoint third_savepoint;

rollback to first_savepoint;

commit;

select * from items;