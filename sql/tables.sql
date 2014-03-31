set foreign_key_checks = 0;
drop table if exists customer;
drop table if exists article;
drop table if exists product;
drop table if exists ingridient;
set foreign_key_checks = 1;


CREATE TABLE customer (
	id int auto_increment,
	name varchar(255) not null,
	address varchar(255) not null,
	primary key(id)
);

CREATE TABLE article (
	id int auto_increment,
	name varchar(255) not null,
	ammount int not null,
	prefix varchar(255) not null,
	latest_delivery_ammount int not null default 0,
	latest_delivery_time date,
	primary key(id)
);

CREATE TABLE product (
	name varchar(255) not null,
	primary key(name)
);

CREATE TABLE ingridient (
	id int auto_increment,
	product varchar(255) not null,
	article int not null,
	ammount double not null,
	primary key(id),
	foreign key(product) REFERENCES  product(name),
	foreign key(article) REFERENCES  article(id)	

);

INSERT INTO customer (name, address) VALUES ("Finkakor AB","Helsingborg");
INSERT INTO customer (name, address) VALUES ("Smabröod AB","Malmö");
INSERT INTO customer (name, address) VALUES ("Kaffebrod AB","Landskrona");
INSERT INTO customer (name, address) VALUES ("Bjudkakor AB","Ystad");
INSERT INTO customer (name, address) VALUES ("Kalaskakor AB","Trelleborg");
INSERT INTO customer (name, address) VALUES ("Partykakor AB","Kristianstad");
INSERT INTO customer (name, address) VALUES ("Gastkakor AB","Häassleholm");
INSERT INTO customer (name, address) VALUES ("Skanekakor AB","Perstorp");


INSERT INTO article (name, ammount,prefix) VALUES ("Flour",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Butter",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Icing sugar",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Roasted, chopped nuts",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Fine-ground nuts",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Ground, roasted nuts",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Bread crumbs",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Sugar",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Egg whites",1000000000,"dl");
INSERT INTO article (name, ammount,prefix) VALUES ("Marzipan",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Eggs",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Potato starch",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Wheat flour",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Sodium bicarbonate",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Vanilla",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Chopped almonds",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Cinnamon",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Vanilla sugar",1000000000,"g");
INSERT INTO article (name, ammount,prefix) VALUES ("Chocolate",1000000000,"g");


INSERT INTO product(name) VALUES ("Nut ring");
INSERT INTO product(name) VALUES ("Nut cookie");
INSERT INTO product(name) VALUES ("Amneris");
INSERT INTO product(name) VALUES ("Tango");
INSERT INTO product(name) VALUES ("Almond delight ");
INSERT INTO product(name) VALUES ("Berliner");

INSERT INTO ingridient(product,article,ammount) VALUES ("Nut ring",1,450.0);
INSERT INTO ingridient(product,article,ammount) VALUES ("Nut ring",2,450.0);
INSERT INTO ingridient(product,article,ammount) VALUES ("Nut ring",3,190.0);
INSERT INTO ingridient(product,article,ammount) VALUES ("Nut ring",4,225.0);

INSERT INTO ingridient(product,article,ammount) VALUES ("Nut cookie",5,750.0);
INSERT INTO ingridient(product,article,ammount) VALUES ("Nut cookie",6,625.0);
INSERT INTO ingridient(product,article,ammount) VALUES ("Nut cookie",7,125.0);
INSERT INTO ingridient(product,article,ammount) VALUES ("Nut cookie",8,375.0);
INSERT INTO ingridient(product,article,ammount) VALUES ("Nut cookie",9,3.5);
INSERT INTO ingridient(product,article,ammount) VALUES ("Nut cookie",19,50.0);

INSERT INTO ingridient(product,article,ammount) VALUES ("Amneris",10,750.0);
INSERT INTO ingridient(product,article,ammount) VALUES ("Amneris",2,250.0);
INSERT INTO ingridient(product,article,ammount) VALUES ("Amneris",11,250.0);
INSERT INTO ingridient(product,article,ammount) VALUES ("Amneris",12,25.0);
INSERT INTO ingridient(product,article,ammount) VALUES ("Amneris",13,25.0);

INSERT INTO ingridient(product,article,ammount) VALUES ("Tango",2,200);
INSERT INTO ingridient(product,article,ammount) VALUES ("Tango",8,250);
INSERT INTO ingridient(product,article,ammount) VALUES ("Tango",1,300);
INSERT INTO ingridient(product,article,ammount) VALUES ("Tango",14,4);
INSERT INTO ingridient(product,article,ammount) VALUES ("Tango",15,2);

INSERT INTO ingridient(product,article,ammount) VALUES ("Almond delight",2,400);
INSERT INTO ingridient(product,article,ammount) VALUES ("Almond delight",8,270);
INSERT INTO ingridient(product,article,ammount) VALUES ("Almond delight",16,279);
INSERT INTO ingridient(product,article,ammount) VALUES ("Almond delight",1,400);
INSERT INTO ingridient(product,article,ammount) VALUES ("Almond delight",17,10);

INSERT INTO ingridient(product,article,ammount) VALUES ("Berliner",1,350);
INSERT INTO ingridient(product,article,ammount) VALUES ("Berliner",2,250);
INSERT INTO ingridient(product,article,ammount) VALUES ("Berliner",3,100);
INSERT INTO ingridient(product,article,ammount) VALUES ("Berliner",11,50);
INSERT INTO ingridient(product,article,ammount) VALUES ("Berliner",18,5);
INSERT INTO ingridient(product,article,ammount) VALUES ("Berliner",19,50);