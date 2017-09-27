--CREATE DATABASE grocery_store;

CREATE TABLE products (
	product_id		SERIAL  	PRIMARY KEY NOT NULL,
	product_name 	TEXT 		NOT NULL,
	description		TEXT,
	expiration_date	DATE,
	quantity 		INT 			CHECK(quantity > 0),
	unit_price 		MONEY,	
	tax_rate		NUMERIC(5,5) 	DEFAULT 0.08000 					-- a number between 0.00000 and 0.99999 corresponding to a tax rate of 0 to 99.999%
);


CREATE TABLE orders (
	order_id 			SERIAL 	PRIMARY KEY	NOT NULL,
	order_date			DATE,
	order_time			TIME(2),
	total_price			MONEY
);

-- table used for the products bought in an order
CREATE TABLE products_in_order (
	pio_id		SERIAL PRIMARY KEY NOT NULL,
	order_id 	INT REFERENCES orders(order_id),
	product_id	INT REFERENCES products(product_id),
	quantity 	INT CHECK(quantity > 0)
);


-- SAMPLE DATA 
INSERT INTO products (product_name, description, expiration_date, quantity, unit_price, tax_rate) VALUES
	('Apple', 	'Red Delicious', 		'2017-10-13', 50, 1.75, 	0.06000),
	('Banana', 	'Contains 10 Bananas', 	'2017-10-15', 30, 2.00, 	0.06000),
	('Eggs', 	'One Dozen Eggs', 		'2017-10-17', 40, 2.75, 	0.08000),
	('Milk', 	'Whole Milk', 			'2017-10-05', 70, 2.50, 	0.08000),
	('Bread', 	'Whole Grain', 			'2017-06-10', 45, 3.00, 	0.06000),
	('Cheese', 	'Shredded Cheddar', 	'2017-11-27', 60, 4.00, 	0.08000),
	('Steak', 	'Two Pack T-Bones', 	'2017-10-04', 35, 10.00,	0.05000);

INSERT INTO orders (order_date, order_time, total_price) VALUES
	('2017-9-20', '08:00:00', 	13.25),
	('2017-9-20', '10:00:00', 	31.75),
	('2017-9-21', '15:00:00', 	14.50),
	('2017-9-22', '11:00:00', 	21.50),
	('2017-9-22', '14:30:00', 	21.00);

INSERT INTO products_in_order (order_id, product_id, quantity) VALUES
	(1, 1, 3),
	(1, 3, 2),
	(1, 4, 1),
	(2, 3, 1),
	(2, 4, 2),
	(2, 6, 1),
	(2, 7, 2),
	(3, 2, 2),
	(3, 4, 1),
	(3, 6, 2),
	(4, 1, 2),
	(4, 4, 2),
	(4, 5, 1),
	(4, 7, 1),
	(5, 2, 1),
	(5, 4, 2),
	(5, 6, 1);






