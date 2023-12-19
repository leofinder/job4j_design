create table employees(
	id serial primary key,
	name varchar(255)
);

create table accounts(
	id serial primary key,
	username varchar(255),
	password varchar(255),
	employe_id int references employees(id) unique
);
