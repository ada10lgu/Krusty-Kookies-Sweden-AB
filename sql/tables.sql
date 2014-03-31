set foreign_key_checks = 0;
drop table if exists customer;
drop table if exists article;
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
	latest_delivery_ammount int not null default 0,
	latest_delivery_time date,
	primary key(id)
);




INSERT INTO customer (name, address) VALUES ("Lars","hemma hos lars");
INSERT INTO customer (name, address) VALUES ("Per","Inte här");

INSERT INTO article (name, ammount) VALUES ("smör",700);
INSERT INTO article (name, ammount) VALUES ("socker",800);
INSERT INTO article (name, ammount) VALUES ("choklad",650);
INSERT INTO article (name, ammount) VALUES ("salt",1000);
INSERT INTO article (name, ammount) VALUES ("bananer",0);


