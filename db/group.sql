create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices (name, price) values ('Laptop', 12000.00), ('Smartphone', 3000.00), ('Tablet', 2000.00), ('Smartwatch', 400.00), ('Desktop', 4000.00);
insert into people (name) values ('John Doe'), ('Jane Smith'), ('Alice Johnson'), ('Bob Brown'), ('Carol White');
insert into devices_people (device_id, people_id) values (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (1, 2), (3, 1), (2, 4);

select avg(d.price) as "Средняя цена"
from devices as d;

select p.name as ФИО, avg(d.price) as "Средняя цена"
from people as p 
	join devices_people as dp on p.id = dp.people_id
		join devices as d on dp.device_id = d.id
group by p.name;

select p.name as ФИО, avg(d.price) as "Средняя цена"
from people as p 
	join devices_people as dp on p.id = dp.people_id
		join devices as d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;
