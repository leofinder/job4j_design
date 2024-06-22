create table products (
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace procedure delete_data(prod varchar, i_price integer)
language 'plpgsql'
as $$
	BEGIN
		delete from products
		where producer = prod and price < i_price;
	END
$$;

call delete_data('producer_2', 101);

create
or replace function f_delete_data(prod varchar, i_count integer)
returns integer
language 'plpgsql'
as $$
	declare
		result integer;
	BEGIN
		select count(name) into result from products
		where producer = prod and count < i_count; 
		delete from products
		where producer = prod and count < i_count;
		return result;
	END;
$$;

select f_delete_data('producer_3', 8);
