create table clients (
    id serial primary key,
    name text not null,
    address text,
    phone text,
    email text
);

create table contracts (
    id serial primary key,
    contract_number text not null,
    start_date date not null,
    end_date date,
    amount numeric(15, 2),
    client_id int references clients(id)
);

insert into clients (name, address, phone, email) values ('ООО "Ромашка"', 'г. Москва, ул. Ленина, д. 1', '+7-495-123-45-67', 'info@romashka.ru');
insert into clients (name, address, phone, email) values ('ЗАО "Березка"', 'г. Санкт-Петербург, Невский пр., д. 50', '+7-812-765-43-21', 'contact@berezka.ru');
insert into clients (name, address, phone, email) values ('ИП Иванов И.И.', 'г. Новосибирск, ул. Советская, д. 10', '+7-383-987-65-43', 'ivanov@novosibirsk.ru');

insert into contracts (contract_number, start_date, end_date, amount, client_id) values ('C-1001', '2023-01-01', '2024-01-01', 100000.00, 1);
insert into contracts (contract_number, start_date, end_date, amount, client_id) values ('C-1002', '2023-02-01', '2024-02-01', 200000.00, 2);
insert into contracts (contract_number, start_date, end_date, amount, client_id) values ('C-1003', '2023-03-01', '2024-03-01', 150000.00, 3);
insert into contracts (contract_number, start_date, end_date, amount, client_id) values ('C-1004', '2023-04-01', null, 300000.00, 1);
insert into contracts (contract_number, start_date, end_date, amount, client_id) values ('C-1005', '2023-05-01', '2024-05-01', 250000.00, 2);

select cl.name Контрагент, con.contract_number as "Номер договора"
from contracts con join clients cl on con.client_id = cl.id;

select con.contract_number as "Номер договора", con.start_date "Дата начала", con.end_date "Дата окончания"
from contracts con join clients cl on con.client_id = cl.id where cl.address like '%Москва%';

select cl.name Контрагент, con.contract_number as "Номер договора", con.start_date "Дата начала", con.end_date "Дата окончания"
from contracts con join clients cl on con.client_id = cl.id where con.amount > 180000;
