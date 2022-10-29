/* Populate tables */
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (1, 'Jose', 'Pepe', 'josepepe@gmail.com', 'Newcastle', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (2, 'Maria', 'Pepa', 'mariapepa@gmail.com', 'Queenstown', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (3,'Biron','Chastaing','bchastaing0@ocn.ne.jp','Volksrust', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (4,'Doug','Quig','dquig1@webnode.com','Stanerton', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (5,'Francyne','Terbrug','fterbrug2@angelfire.com','Hiddleburg', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (6,'Egan','Newlands','enewlands3@ftc.gov','Voslorus', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (7,'Willi','Squibe','wsquibe4@nsw.gov.au','Johannesburg', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (8,'Anatola','Roddam','aroddam5@home.pl','Pretoria', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (9,'Ramonda','Haylett','rhaylett6@unc.edu','morgezon', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (10,'Timothy','McMurty','tmcmurty7@opensource.org','Amersfort', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (11,'Micky','Gammie','mgammie8@mapy.cz','Bethal', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (12,'Dun','Gregersen','dgregersenb@tuttocitta.it','Kriel', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (13,'Forester','Jerwood','fjerwoodc@mayoclinic.com','Ogies', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (14,'Gertruda','Vernalls','gvernallsd@psu.edu','Wilge', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (15,'Lita','Jansen','ljansene@yellowbook.com','Osizweni', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (16,'Saw','Erley','serleyf@photobucket.com','Phola', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (17,'Dagny','Copelli','dcopellig@goodreads.com','Vryheied', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (18,'Izak','Gerssam','igerssamh@163.com', '', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (19,'Gawen','Stot','gstoti@jimdo.com', '','');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (20,'Jessee','Strowlger','jstrowlgerj@redcross.org', '','');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (21,'Bay','Pashler','bpashlerk@istockphoto.com', '','');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (22,'Verine','Keightley','vkeightleyl@tripadvisor.com','', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (23,'Gratia','Loble','globlem@joomla.org','Kempton Park', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (24,'Drake','Kingston','dkingstonn@altervista.org','Birchley', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (25,'Grayce','Cruddas','gcruddaso@marriott.com','Glen Marias', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (26,'Karry','Botten','kbottenp@sitemeter.com','Pretoria', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (27,'Mead','Jellings','mjellingsq@accuweather.com','Tembisa', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (28,'Elden','Wiltsher','ewiltsherr@theglobeandmail.com','Mambiza', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (29,'Caprice','Strase','cstrases@goo.gl','Soshanguve', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (30,'Dolly','Ringrose','dringroset@drupal.org','Sunny Side', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (31,'Stanley','Hovert','shovert9@rediff.com','Arcadia', '');
INSERT INTO clients (id, first_name, last_name, email, city, photo) VALUES (32,'Janelle','Quilleash','jquilleasha@slashdot.org', 'Maraba Straata','');

/* Populate table products */
INSERT INTO products (id, name, price, create_at) VALUES(1, 'Panasonic LCD', 25990, NOW());
INSERT INTO products (id, name, price, create_at) VALUES(2, 'Sony Camera DSC-W320B', 123490, NOW());
INSERT INTO products (id, name, price, create_at) VALUES(3, 'Apple iPod', 1490, NOW());
INSERT INTO products (id, name, price, create_at) VALUES(4, 'Sony Notebook Z110', 37990, NOW());
INSERT INTO products (id, name, price, create_at) VALUES(5, 'HP F2280 MultiF', 6990, NOW());
INSERT INTO products (id, name, price, create_at) VALUES(6, 'Bike 26 BMW', 269990, NOW());
INSERT INTO products (id, name, price, create_at) VALUES(7, 'Keyboard Razer CLGv', 2990, NOW());
INSERT INTO products (id, name, price, create_at) VALUES(8, 'Mouse Razer Deathadder', 650, NOW());

/* Invoices */
INSERT INTO invoices(id, description, observation, client_id, create_at) VALUES(1, 'Invoice office team', null, 1, NOW());
INSERT INTO items_invoices(amount, invoice_id, product_id) VALUES(1, 1, 1); 
INSERT INTO items_invoices(amount, invoice_id, product_id) VALUES(2, 1, 4); 
INSERT INTO items_invoices(amount, invoice_id, product_id) VALUES(1, 1, 5); 
INSERT INTO items_invoices(amount, invoice_id, product_id) VALUES(1, 1, 7); 

INSERT INTO invoices(id, description, observation, client_id, create_at) VALUES(2, 'Bike Invoice', 'IMPORTANT STUFF', 1, NOW());
INSERT INTO items_invoices(amount, invoice_id, product_id) VALUES(3, 2, 6); 

/* */
INSERT INTO users (id, username, password, enabled) VALUES (1, 'rodri', '$2a$10$bwhu5TxyJPuxwn6.g2bUC.8dUCV5vh9eq40RoFX4pEIDqrHlEUx3.', 1);
INSERT INTO users (id, username, password, enabled) VALUES (2, 'admin', '$2a$10$R244P6hZ4MUa9EBeAUEcne5B8Lb6mTzw5.2vzKH6S7q9u3pqrGfoW', 1);

INSERT INTO authorities (user_id, authority) VALUES ('1', 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES ('2', 'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES ('2', 'ROLE_USER');
