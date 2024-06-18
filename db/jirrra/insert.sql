insert into roles (name) values ('Admin');
insert into roles (name) values ('Editor');
insert into roles (name) values ('Viewer');

insert into users (name, email, roles_id) values ('Саша', 'sas@example.com', 1);
insert into users (name, email, roles_id) values ('Маша', 'mks@example.com', 2);
insert into users (name, email, roles_id) values ('Вася', 'vfd@example.com', 3);

insert into rules (name) values ('Create Content');
insert into rules (name) values ('Edit Content');
insert into rules (name) values ('Delete Content');
insert into rules (name) values ('View Content');

insert into roles_rules (roles_id, rules_id) values (1, 1);
insert into roles_rules (roles_id, rules_id) values (1, 2);
insert into roles_rules (roles_id, rules_id) values (1, 3);
insert into roles_rules (roles_id, rules_id) values (1, 4);
insert into roles_rules (roles_id, rules_id) values (2, 2);
insert into roles_rules (roles_id, rules_id) values (2, 4);
insert into roles_rules (roles_id, rules_id) values (3, 4);

insert into categories (name) values ('Технологии'); 
insert into categories (name) values ('Наука');
insert into categories (name) values ('Искусство');

insert into states (name) values ('New');
insert into states (name) values ('In Progress');
insert into states (name) values ('Completed');

insert into items (name, description, user_id, categories_id, states_id) values ('Item 1', 'Описание для предмета 1', 1, 1, 1);
insert into items (name, description, user_id, categories_id, states_id) values ('Item 2', 'Описание для предмета 2', 2, 2, 2);
insert into items (name, description, user_id, categories_id, states_id) values ('Item 3', 'Описание для предмета 3', 3, 3, 3);

insert into comments (name, items_id) values ('Отличный предмет!', 1);
insert into comments (name, items_id) values ('Нуждается в доработке.', 2);
insert into comments (name, items_id) values ('Превосходная работа!', 3);

insert into attachs (name, file_path, items_id) values ('Attachment 1', '/path/to/file1', 1);
insert into attachs (name, file_path, items_id) values ('Attachment 2', '/path/to/file2', 2);
insert into attachs (name, file_path, items_id) values ('Attachment 3', '/path/to/file3', 3);

