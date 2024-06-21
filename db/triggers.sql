create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

-- 1. Триггер должен срабатывать после вставки данных

create
or replace function tax_after()
	returns trigger as
$$
	BEGIN
		update products
		set price = price * 1.2
		where id = (select id from inserted);
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_after_trigger
	after insert
	on products
	referencing new table as inserted
	for each statement
	execute procedure tax_after();

insert into products (name, producer, count, price)
VALUES ('product_5', 'producer_5', 2, 50);
insert into products (name, producer, count, price)
VALUES ('product_5', 'producer_6', 5, 580);
insert into products (name, producer, count, price)
VALUES ('product_6', 'producer_6', 10, 18);

select * from products;

alter table products disable trigger tax_after_trigger;

-- 2 Триггер должен срабатывать до вставки данных

create
or replace function tax_before()
	returns trigger as
$$
	BEGIN
		new.price = new.price * 1.2;
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_before_trigger
	before insert
	on products
	for each row
	execute procedure tax_before();

insert into products (name, producer, count, price)
VALUES ('product_7', 'producer_7', 7, 501);
insert into products (name, producer, count, price)
VALUES ('product_8', 'producer_8', 3, 224);
insert into products (name, producer, count, price)
VALUES ('product_9', 'producer_9', 12, 54);

select * from products;

-- 3 Заносить имя, цену и текущую дату в таблицу history_of_price

create table history_of_price (
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create
or replace function update_history()
	returns trigger as
$$
	BEGIN
		insert into history_of_price (name, price, date) values (new.name, new.price, now());
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger update_history_trigger
	after insert
	on products
	for each row
	execute procedure update_history();
	
insert into products (name, producer, count, price)
VALUES ('product_10', 'producer_10', 9, 65);
insert into products (name, producer, count, price)
VALUES ('product_11', 'producer_11', 33, 12);
insert into products (name, producer, count, price)
VALUES ('product_12', 'producer_12', 4, 2);	

select * from history_of_price;
select * from products;