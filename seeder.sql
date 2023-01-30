-- Script to insert dummy dev data into the database.

-- You first need to register two users into the system before running this script. (Register users using Postman or manually input them)

DELETE FROM order_details;
DELETE FROM order_product_details;
DELETE FROM product_inventory;
DELETE FROM products;
DELETE FROM address;

-- Change value of "SET foreign_key_checks" to 1 after running seeder file for the first time and execute that line only, again.
SET foreign_key_checks = 0;

INSERT INTO products (id, product_name, product_desc, product_sku, product_price, inventory_id) VALUES (1, 'Product #1', 'Product number one description.', 'PRD1TS2023', 5.50, 1);
INSERT INTO products (id, product_name, product_desc, product_sku, product_price, inventory_id) VALUES (2, 'Product #2', 'Product number two description.', 'PRD2TS2023', 10.56, 2);
INSERT INTO products (id, product_name, product_desc, product_sku, product_price, inventory_id) VALUES (3, 'Product #3', 'Product number three description.', 'PRD3TS2023', 2.74, 3);
INSERT INTO products (id, product_name, product_desc, product_sku, product_price, inventory_id) VALUES (4, 'Product #4', 'Product number four description.', 'PRD4TS2023', 15.69, 4);
INSERT INTO products (id, product_name, product_desc, product_sku, product_price, inventory_id) VALUES (5, 'Product #5', 'Product number five description.', 'PRD5TS2023', 42.81, 5);

INSERT INTO product_inventory (id, product_id, quantity) VALUES (1, 1, 5);
INSERT INTO product_inventory (id, product_id, quantity) VALUES (2, 2, 8);
INSERT INTO product_inventory (id, product_id, quantity) VALUES (3, 3, 12);
INSERT INTO product_inventory (id, product_id, quantity) VALUES (4, 4, 73);
INSERT INTO product_inventory (id, product_id, quantity) VALUES (5, 5, 2);

INSERT INTO payment (id, cardholder_name, card_no, cvc_no, expir_date, payment_type, provider, user_id) VALUES (2, 'Dave Grills', '5555444433332222', '001', '1230', 'Debit Card', 'PayPal', 1);
INSERT INTO payment (id, cardholder_name, card_no, cvc_no, expir_date, payment_type, provider, user_id) VALUES (1, 'John Smith', '5555555555554444', '000', '0125', 'Debit Card', 'Stripe', 2);

INSERT INTO address (id, address_line1, city, country, postal_code, user_id) VALUES (1, '123 Tester Hill', 'Testerton', 'United States', 75137, 1);
INSERT INTO address (id, address_line1, city, country, postal_code, user_id) VALUES (2, '321 Spring Boot', 'Dallas', 'United States',75220, 2);

INSERT INTO order_details (id, address_id, payment_id, user_id) VALUES (1, 1, 1, 1);
INSERT INTO order_details (id, address_id, payment_id, user_id) VALUES (2, 1, 1, 1);
INSERT INTO order_details (id, address_id, payment_id, user_id) VALUES (3, 1, 1, 1);
INSERT INTO order_details (id, address_id, payment_id, user_id) VALUES (4, 2, 2, 2);
INSERT INTO order_details (id, address_id, payment_id, user_id) VALUES (5, 2, 2, 2);


INSERT INTO order_product_details (id, order_details_id, product_id, quantity) VALUES (1, 1, 1, 5);
INSERT INTO order_product_details (id, order_details_id, product_id, quantity) VALUES (2, 1, 2, 5);
INSERT INTO order_product_details (id, order_details_id, product_id, quantity) VALUES (3, 2, 3, 5);
INSERT INTO order_product_details (id, order_details_id, product_id, quantity) VALUES (4, 2, 2, 5);
INSERT INTO order_product_details (id, order_details_id, product_id, quantity) VALUES (5, 2, 5, 5);
INSERT INTO order_product_details (id, order_details_id, product_id, quantity) VALUES (6, 3, 3, 5);
INSERT INTO order_product_details (id, order_details_id, product_id, quantity) VALUES (7, 4, 4, 5);
INSERT INTO order_product_details (id, order_details_id, product_id, quantity) VALUES (8, 4, 2, 5);
INSERT INTO order_product_details (id, order_details_id, product_id, quantity) VALUES (9, 5, 3, 5);
INSERT INTO order_product_details (id, order_details_id, product_id, quantity) VALUES (10, 5, 1, 5);
