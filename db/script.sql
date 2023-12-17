create table books(
	id serial primary key,
	title varchar(255),
	publication_year int,
	author varchar(255),
	available boolean
);

insert into books(title, publication_year, author, available) values ('Pet Cemetery', 1983, 'Stephen King', true);

update books set title = 'Pet Sematary';

delete from books;