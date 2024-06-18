create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values ('Elephant', 60, '1750-05-01');
insert into fauna (name, avg_age, discovery_date) values ('Tiger', 15, '1765-10-12');
insert into fauna (name, avg_age, discovery_date) values ('Koala', 13, '1810-07-23');
insert into fauna (name, avg_age, discovery_date) values ('Ancient Tortoise', 15000, '1950-01-01');
insert into fauna (name, avg_age, discovery_date) values ('Panda', 20, '1869-03-11');
insert into fauna (name, avg_age, discovery_date) values ('Crocodile', 70, '1739-08-19');
insert into fauna (name, avg_age, discovery_date) values ('Eagle', 25, '1825-11-15');
insert into fauna (name, avg_age, discovery_date) values ('Shark', 30, '1748-02-21');
insert into fauna (name, avg_age, discovery_date) values ('Eternal Phoenix', 20000, '1800-01-01');
insert into fauna (name, avg_age, discovery_date) values ('Penguin', 20, '1830-12-25');
insert into fauna (name, avg_age, discovery_date) values ('Kangaroo', 23, '1770-04-29');
insert into fauna (name, avg_age, discovery_date) values ('Flamingo', 30, '1782-06-05');
insert into fauna (name, avg_age, discovery_date) values ('Giraffe', 25, '1834-09-17');
insert into fauna (name, avg_age, discovery_date) values ('Goldfish', 10, '1901-04-01');
insert into fauna (name, avg_age, discovery_date) values ('Catfish', 15, '1925-08-15');
insert into fauna (name, avg_age, discovery_date) values ('Starfish', 35, '1888-11-11');
insert into fauna (name, avg_age, discovery_date) values ('Immortal Jellyfish', 12000, '2000-01-01');
insert into fauna (name, avg_age, discovery_date) values ('Whale', 90, null);
insert into fauna (name, avg_age, discovery_date) values ('Dragonfish', 12, '1890-07-01');
insert into fauna (name, avg_age, discovery_date) values ('Clownfish', 6, '1930-03-25');

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';