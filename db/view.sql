create table customers (
    id serial primary key,
    name text,
    email text,
    phone text
);

create table orders (
    id serial primary key,
    customer_id int references customers(id),
    date date,
    amount float
);

create table categories (
    id serial primary key,
    name text
);

create table products (
    id serial primary key,
    name text,
    category_id int references categories(id),
    price float,
    stock int
);

create table order_details (
    id serial primary key,
    order_id int references orders(id),
    product_id int references products(id),
    quantity int,
    price float
);


insert into customers (name, email, phone) values ('Иван Иванов', 'ivan.ivanov@example.com', '555-1234');
insert into customers (name, email, phone) values ('Анна Смирнова', 'anna.smirnova@example.com', '555-5678');
insert into customers (name, email, phone) values ('Ольга Петрова', 'olga.petrova@example.com', '555-9876');
insert into customers (name, email, phone) values ('Сергей Сидоров', 'sergey.sidorov@example.com', '555-6543');

insert into categories (name) values ('Электроника'),('Книги'),('Одежда'),('Дом и сад');

insert into products (name, category_id, price, stock) values ('Смартфон', 1, 299.99, 100);
insert into products (name, category_id, price, stock) values ('Ноутбук', 1, 999.99, 50);
insert into products (name, category_id, price, stock) values ('Роман', 2, 19.99, 200);
insert into products (name, category_id, price, stock) values ('Футболка', 3, 9.99, 150);
insert into products (name, category_id, price, stock) values ('Блендер', 4, 49.99, 75);

insert into orders (customer_id, date, amount) values (1, '2024-01-15', 319.98);
insert into orders (customer_id, date, amount) values (2, '2024-02-20', 19.99);
insert into orders (customer_id, date, amount) values (3, '2024-03-10', 1099.98);
insert into orders (customer_id, date, amount) values (4, '2024-04-05', 59.98);

insert into order_details (order_id, product_id, quantity, price) values (1, 1, 1, 299.99);
insert into order_details (order_id, product_id, quantity, price) values (1, 3, 1, 19.99);
insert into order_details (order_id, product_id, quantity, price) values (2, 3, 1, 19.99);
insert into order_details (order_id, product_id, quantity, price) values (3, 2, 1, 999.99);
insert into order_details (order_id, product_id, quantity, price) values (3, 1, 1, 299.99);
insert into order_details (order_id, product_id, quantity, price) values (4, 5, 1, 49.99);
insert into order_details (order_id, product_id, quantity, price) values (4, 4, 1, 9.99);

create view show_detail_info_with_sum_over_250
as
select c.name as customer, c.email as email, o.date as date, cat.name as category, 
	p.name as product, p.price as price, od.quantity as quantity, p.price*od.quantity as sum
from customers as c
	left join orders as o on c.id = o.customer_id
		left join order_details as od on o.id = od.order_id
			left join products as p on od.product_id = p.id
				left join categories as cat on p.category_id = cat.id
where p.price*od.quantity > 250;

select * from show_detail_info_with_sum_over_250;


