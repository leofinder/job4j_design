create table car_bodies (
	id serial primary key,
	name text
);

create table car_engines (
	id serial primary key,
	name text
);

create table car_transmissions (
	id serial primary key,
	name text
);

create table cars (
	id serial primary key,
	name text,
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies (name) values ('Седан');
insert into car_bodies (name) values ('Хэтчбек');
insert into car_bodies (name) values ('Пикап');
insert into car_bodies (name) values ('Купе');

insert into car_engines (name) values ('V8');
insert into car_engines (name) values ('V6');
insert into car_engines (name) values ('I4');
insert into car_engines (name) values ('Электрический');

insert into car_transmissions (name) values ('Автоматическая');
insert into car_transmissions (name) values ('Механическая');
insert into car_transmissions (name) values ('Вариатор');

insert into cars (name, body_id, engine_id, transmission_id) values ('Машина 1', 1, 3, 1);
insert into cars (name, body_id, engine_id, transmission_id) values ('Машина 2', 2, 2, 2);
insert into cars (name, body_id, engine_id, transmission_id) values ('Машина 3', 1, null, 2);
insert into cars (name, body_id, engine_id, transmission_id) values ('Машина 4', null, 3, null);
insert into cars (name, body_id, engine_id, transmission_id) values ('Машина 5', 4, 4, 1);

select c.id as id, c.name as car_name, b.name as body_name, e.name as engine_name, t.name as transmission_name
from cars as c
	left join car_bodies as b on c.body_id = b.id
	left join car_engines as e on c.engine_id = e.id
	left join car_transmissions as t on c.transmission_id = t.id;
	
select b.name as name
from car_bodies as b
	left join cars as c on b.id = c.body_id
where c.name is null;

select e.name as name
from car_engines as e
	left join cars as c on e.id = c.engine_id
where c.name is null;

select t.name as name
from car_transmissions as t
	left join cars as c on t.id = c.transmission_id
where c.name is null;
	