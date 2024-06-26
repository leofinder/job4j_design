CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers (first_name, last_name, age, country) VALUES ('John', 'Doe', 30, 'USA');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Jane', 'Smith', 25, 'Canada');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Carlos', 'Gonzalez', 40, 'Mexico');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Li', 'Wang', 25, 'China');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Olga', 'Petrova', 35, 'Russia');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Amina', 'Khan', 32, 'India');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Hans', 'Schneider', 45, 'Germany');

SELECT * FROM customers
WHERE age = (SELECT min(age) FROM customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders (amount, customer_id) VALUES (150, 1);
INSERT INTO orders (amount, customer_id) VALUES (200, 2);
INSERT INTO orders (amount, customer_id) VALUES (250, 4);
INSERT INTO orders (amount, customer_id) VALUES (100, 2);
INSERT INTO orders (amount, customer_id) VALUES (400, 7);

SELECT c.id, c.first_name, c.last_name
FROM customers as c
WHERE c.id NOT IN (SELECT DISTINCT customer_id FROM orders);
