CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name) VALUES (1, 'Компания А');
INSERT INTO company (id, name) VALUES (2, 'Компания Б');
INSERT INTO company (id, name) VALUES (3, 'Компания В');
INSERT INTO company (id, name) VALUES (4, 'ТехноПарк');
INSERT INTO company (id, name) VALUES (5, 'Инновации ХХI');
INSERT INTO company (id, name) VALUES (6, 'РобоТех');
INSERT INTO company (id, name) VALUES (7, 'БиоМедТех');
INSERT INTO company (id, name) VALUES (8, 'АгроТех');

INSERT INTO person (id, name, company_id) VALUES (1, 'Иван Иванов', 1);
INSERT INTO person (id, name, company_id) VALUES (2, 'Петр Петров', 2);
INSERT INTO person (id, name, company_id) VALUES (3, 'Сергей Сергеев', 3);
INSERT INTO person (id, name, company_id) VALUES (4, 'Алексей Алексеев', 1);
INSERT INTO person (id, name, company_id) VALUES (5, 'Михаил Михайлов', 2);
INSERT INTO person (id, name, company_id) VALUES (6, 'Дмитрий Дмитриев', 4);
INSERT INTO person (id, name, company_id) VALUES (7, 'Ольга Ольгина', 4);
INSERT INTO person (id, name, company_id) VALUES (8, 'Екатерина Екатеринина', 5);
INSERT INTO person (id, name, company_id) VALUES (9, 'Анна Анникова', 6);
INSERT INTO person (id, name, company_id) VALUES (10, 'Максим Максимов', 7);
INSERT INTO person (id, name, company_id) VALUES (11, 'Мария Мариева', 8);
INSERT INTO person (id, name, company_id) VALUES (12, 'Андрей Андреев', 5);
INSERT INTO person (id, name, company_id) VALUES (13, 'Юлия Юлина', 6);
INSERT INTO person (id, name, company_id) VALUES (14, 'Наталья Наталиева', 7);
INSERT INTO person (id, name, company_id) VALUES (15, 'Игорь Игорев', 8);
INSERT INTO person (id, name, company_id) VALUES (16, 'Владимир Владимиров', 1);
INSERT INTO person (id, name, company_id) VALUES (17, 'Светлана Светлова', 2);
INSERT INTO person (id, name, company_id) VALUES (18, 'Виктор Викторов', 3);
INSERT INTO person (id, name, company_id) VALUES (19, 'Татьяна Татьянова', 4);
INSERT INTO person (id, name, company_id) VALUES (20, 'Роман Романов', 5);

/*
В одном запросе получить
- имена всех person, которые не состоят в компании с id = 5;
- название компании для каждого человека.
*/
SELECT p.name as person_name, c.name as company_name
FROM person as p
	JOIN company as c ON p.company_id = c.id
WHERE p.company_id != 5;

/*
Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании.
Нужно учесть, что таких компаний может быть несколько.
*/

SELECT c.name as company_name, count(p.name) as count_person
FROM person as p
	JOIN company as c ON p.company_id = c.id
GROUP BY company_name
HAVING count(p.name) = (SELECT count(p.name) as count
						FROM person as p
						GROUP BY p.company_id
						ORDER BY count DESC
						LIMIT 1);