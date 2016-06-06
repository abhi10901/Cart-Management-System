
--Insert into account table--
INSERT INTO account(balance) VALUES(100.50);
INSERT INTO account(balance) VALUES(25.73);

--Insert into coustomer table by doing mapping between account and customer--
INSERT INTO customer(customer_number, first_name, last_name, account_id) VALUES('GA4500', 'Bob', 'Watson', 1);
INSERT INTO customer(customer_number, first_name, last_name, account_id) VALUES('ZF2481', 'Sue', 'Gao', 2);

--Insert into customer _order table and doing mapping between customer and customer_order--
INSERT INTO customer_order(order_number, amount, customer_id) VALUES('Z53A411', 100.71, 2);
INSERT INTO customer_order(order_number, amount, customer_id) VALUES('X70B223', 50.72, 2);
INSERT INTO customer_order(order_number, amount, customer_id) VALUES('G44C321', 434.58, 1);

--Insert into product table--
INSERT INTO product(name, description, price) VALUES ('Microwave','Microwave made in Thailand', 150.75);
INSERT INTO product(name, description, price) VALUES ('Toaster','Toaster made in Japan', 35.55);
INSERT INTO product(name, description, price) VALUES ('Toaster Oven','Toaster Oven made in Japan', 248.28);

--Insert into product delivery table--
INSERT INTO delivery(product_id, product_quantity, date) VALUES(1, 2, '2014-12-05');
INSERT INTO delivery(product_id, product_quantity, date) VALUES(2, 10, '2014-11-28');
INSERT INTO delivery(product_id, product_quantity, date) VALUES(2, 15, '2015-10-21');
INSERT INTO delivery(product_id, product_quantity, date) VALUES(2, 5, '2015-11-16');

--Insert into producy order table--
INSERT INTO product_order(order_id, product_id) VALUES(3, 1);
INSERT INTO product_order(order_id, product_id) VALUES(3, 2);
INSERT INTO product_order(order_id, product_id) VALUES(2, 1);

--Insert into category table--
INSERT INTO category(name) VALUES('Cooking');
INSERT INTO category(name) VALUES('Gardening');
INSERT INTO category(name) VALUES('Electronics');

--Insert into product_category table--
INSERT INTO product_category(product_id, category_id) values(1, 1);
INSERT INTO product_category(product_id, category_id) values(2, 1);
INSERT INTO product_category(product_id, category_id) values(2, 3);