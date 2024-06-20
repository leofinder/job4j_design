create table departments (
	id serial primary key,
	name text
);

create table employees (
	id serial primary key,
	name text,
	departments_id int references departments(id)
);

insert into departments (name) values ('Отдел разработки');
insert into departments (name) values ('Отдел продаж');
insert into departments (name) values ('Отдел кадров');
insert into departments (name) values ('Бухгалтерия');
insert into departments (name) values ('IT отдел');
insert into departments (name) values ('Производство');

insert into employees (name, departments_id) values ('Алексей Иванов', 1);
insert into employees (name, departments_id) values ('Мария Петрова', 2);
insert into employees (name, departments_id) values ('Иван Сидоров', 3);
insert into employees (name, departments_id) values ('Елена Кузнецова', 4);
insert into employees (name, departments_id) values ('Дмитрий Смирнов', 5);
insert into employees (name, departments_id) values ('Ольга Сергеева', 1);
insert into employees (name, departments_id) values ('Павел Андреев', 2);
insert into employees (name, departments_id) values ('Наталья Михайлова', 3);
insert into employees (name, departments_id) values ('Сергей Павлов', 4);
insert into employees (name, departments_id) values ('Анна Васильева', 5);
insert into employees (name, departments_id) values ('Николай Теремко', null);

select e.name as employee, d.name as department 
from employees as e
	left join departments as d on e.departments_id = d.id;
	
select e.name as employee, d.name as department 
from departments as d
	right join employees as e on d.id = e.departments_id;

select e.name as employee, d.name as department
from employees as e
	cross join departments as d;
	
select d.name as department
from departments as d
	left join employees as e on d.id = e.departments_id
where e.departments_id is null;



create table teens (
	id serial primary key,
	name text,
	gender varchar(10)
);

insert into teens (name, gender) values ('Алексей', 'Мужской');
insert into teens (name, gender) values ('Мария', 'Женский');
insert into teens (name, gender) values ('Иван', 'Мужской');
insert into teens (name, gender) values ('Елена', 'Женский');
insert into teens (name, gender) values ('Дмитрий', 'Мужской');
insert into teens (name, gender) values ('Ольга', 'Женский');
insert into teens (name, gender) values ('Павел', 'Мужской');
insert into teens (name, gender) values ('Наталья', 'Женский');
insert into teens (name, gender) values ('Сергей', 'Мужской');
insert into teens (name, gender) values ('Анна', 'Женский');


select m.name as Мужчины, w.name as Женщины
from teens as m
	cross join teens as w
where m.gender = 'Мужской' and w.gender = 'Женский';