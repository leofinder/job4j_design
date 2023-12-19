create table products(
	id serial primary key,
	name varchar(255),
	price decimal(10, 2)
);

create table customers(
	id serial primary key,
	name varchar(255)
);

create table orders(
	id serial primary key,
	customer_id int references customers(id),
	product_id int references products(id)
);