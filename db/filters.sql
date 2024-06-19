create table type (
	id serial primary key,
	name text
);

create table product (
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type (name) values ('СЫР'), ('МОЛОКО'), ('МЯСО'), ('ОВОЩИ'), ('ФРУКТЫ'), ('ХЛЕБ'), ('НАПИТКИ');

insert into product (name, type_id, expired_date, price) values ('Сыр плавленный', 1, '2024-12-19', 150.00);
insert into product (name, type_id, expired_date, price) values ('Сыр моцарелла', 1, '2025-01-19', 200.00);
insert into product (name, type_id, expired_date, price) values ('Мороженое ванильное', 2, '2024-08-19', 50.00);
insert into product (name, type_id, expired_date, price) values ('Мороженое шоколадное', 2, '2024-09-19', 60.00);
insert into product (name, type_id, expired_date, price) values ('Мороженое клубничное', 2, '2024-10-19', 55.00);
insert into product (name, type_id, expired_date, price) values ('Говядина', 3, '2024-05-20', 500.00);
insert into product (name, type_id, expired_date, price) values ('Свинина', 3, '2024-07-25', 450.00);
insert into product (name, type_id, expired_date, price) values ('Курица', 3, '2024-07-30', 350.00);
insert into product (name, type_id, expired_date, price) values ('Морковь', 4, '2024-08-19', 30.00);
insert into product (name, type_id, expired_date, price) values ('Картофель', 4, '2024-08-20', 25.00);
insert into product (name, type_id, expired_date, price) values ('Яблоко', 5, '2024-06-10', 40.00);
insert into product (name, type_id, expired_date, price) values ('Банан', 5, '2024-09-05', 500.00);
insert into product (name, type_id, expired_date, price) values ('Хлеб белый', 6, '2024-06-20', 20.00);
insert into product (name, type_id, expired_date, price) values ('Хлеб черный', 6, '2024-06-20', 25.00);
insert into product (name, type_id, expired_date, price) values ('Апельсиновый сок', 7, '2024-12-19', 100.00);
insert into product (name, type_id, expired_date, price) values ('Яблочный сок', 7, '2024-12-19', 90.00);
insert into product (name, type_id, expired_date, price) values ('Молоко', 2, '2024-07-19', 60.00);
insert into product (name, type_id, expired_date, price) values ('Кефир', 2, '2024-07-19', 70.00);

-- 1
select p.name as Продукт, t.name as Тип 
from product as p 
	join type as t on p.type_id = t.id
where t.name = 'СЫР';

-- 2
select p.name as Продукт
from product as p
where p.name ilike '%мороженое%';

-- 3
select p.name as Продукт, p.expired_date as "Срок годности"
from product as p
where p.expired_date < current_date;

-- 4
select p.name as Продукт, p.price as Цена
from product as p
	join (select max(pr.price) as max_price from product as pr) as r on p.price = max_price;

-- 5
select t.name as Тип, count(p.name) as Количество
from type as t
	join product as p on t.id = p.type_id
group by t.name;

-- 6
select p.name as Продукт, t.name as Тип
from product as p
	join type as t on p.type_id = t.id
where t.name in ('СЫР', 'МОЛОКО');

-- 7
select t.name as Тип, count(p.name) as Количество
from type as t
	join product as p on t.id = p.type_id
group by t.name
having count(p.name) < 10;

-- 8
select p.name as Продукт, t.name as Тип
from product as p
	join type as t on p.type_id = t.id;