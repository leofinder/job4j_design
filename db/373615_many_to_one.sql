create table categories(
	id serial primary key,
	name varchar(255)
);

create table products(
	id serial primary key,
	name varchar(255),
	price decimal(10, 2),
	category_id int references categories(id)
);
