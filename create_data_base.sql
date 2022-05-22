DROP database IF EXISTS paymybuddyp6;
create database paymybuddyp6;
use paymybuddyp6;

CREATE TABLE users(
user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
email VARCHAR(100) NOT NULL UNIQUE,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
pass_word VARCHAR(100) NOT NULL,
enabled  TINYINT NOT NULL DEFAULT 1,
amount_app_account FLOAT
);

INSERT INTO users (email, first_name, last_name, pass_word, amount_app_account,enabled)
values ('spring@user1.fr','Frere','Jacques','$2y$10$KLoAmi6sYA.6ImhD3.8Hpu/o87EUYpOTLGcRyMUL34tJhBOu.dq7C',100.0,1);
INSERT INTO users (email, first_name, last_name, pass_word, amount_app_account,enabled)
values ('spring@user2.fr','Julien','Poulpe','$2y$10$n9ZzPsvqsMX3Ie4fXlUn7e9drrMAtLqFKTyxpnNOXjzdoZCbYRwoO',150.0,1);
INSERT INTO users (email, first_name, last_name, pass_word, amount_app_account,enabled)
values ('spring@user3.fr','Pierre','Poulpe','$2y$10$IxRmEnsgdqKzzKyfjPnY3.TQ6UZYXczdgR7U/Rf4x3V98O/BKu7wC',150.0,1);
INSERT INTO users (email, first_name, last_name, pass_word, amount_app_account,enabled)
values ('spring@user4.fr','Sacha','Poulpe','$2y$10$dQMp8JkWYKQNp/tRZG1xw.I7pq1uM33fGcLpsD3sELVk3a6Wh/CtG',150.0,1);
INSERT INTO users (email, first_name, last_name, pass_word, amount_app_account,enabled)
values ('spring@user5.fr','Ondine','Poulpe','$2y$10$iR1G21/dZ2d144Nwn5.EauRdz/22vFFZmHwFCgad4k4GA/.nPrOye',150.0,1);
INSERT INTO users (email, first_name, last_name, pass_word, amount_app_account,enabled)
values ('spring@user6.fr','Pikachu','Poulpe','$2y$10$M0dwDhoZBTDtyRw6v8Tyz.0BnvjvKWk0h/J7Vnp4QTQmampMeFu8S',150.0,1);
INSERT INTO users (email, first_name, last_name, pass_word, amount_app_account,enabled)
values ('spring@admin1.fr','Paul','Poulpe','$2y$10$9qtkP2dKgSthPyCV/32weOhijCbYmhKHtU/G6LXd2Wnu9XhXdUhoy',150.0,1);

CREATE TABLE authorities
(
authority_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
email VARCHAR(100) NOT NULL UNIQUE,
authority VARCHAR(50) NOT NULL,
FOREIGN KEY (email) 
REFERENCES users (email)
);

INSERT INTO authorities (email,authority)
values ('spring@user1.fr','USER');
INSERT INTO authorities (email,authority)
values ('spring@user2.fr','USER');
INSERT INTO authorities (email,authority)
values ('spring@user3.fr','USER');
INSERT INTO authorities (email,authority)
values ('spring@user4.fr','USER');
INSERT INTO authorities (email,authority)
values ('spring@user5.fr','USER');
INSERT INTO authorities (email,authority)
values ('spring@user6.fr','USER');
INSERT INTO authorities (email,authority)
values ('spring@admin1.fr','ADMIN');

CREATE TABLE bank_accounts(
bank_account_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
user_id BIGINT NOT NULL,
FOREIGN KEY (user_id)
REFERENCES users (user_id)
);

INSERT INTO bank_accounts(bank_account_id,user_id)
VALUES
	(1,1),
    (2,2),
    (3,3),
    (4,4),
    (5,5),
    (6,6),
    (7,7);
    
CREATE TABLE transactions_bank(
transaction_bank_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
date DATE NOT NULL,
amount_transfered FLOAT,
bank_account_id BIGINT NOT NULL,
FOREIGN KEY (bank_account_id)
REFERENCES bank_accounts (bank_account_id)
);

INSERT INTO transactions_bank(date,amount_transfered,bank_account_id)
VALUES
	('2022-05-21',100.0,1);

CREATE TABLE assoc_users_users(
user_live_id BIGINT NOT NULL,
user_ressource_id BIGINT NOT NULL,
FOREIGN KEY (user_live_id)
REFERENCES users (user_id),
FOREIGN KEY (user_ressource_id)
REFERENCES users (user_id),
PRIMARY KEY(user_live_id,user_ressource_id)
);

INSERT INTO assoc_users_users(user_live_id,user_ressource_id)
VALUES
	(1,2),
    (1,3),
    (1,4),
    (1,5),
    (1,6);

CREATE TABLE transactions_app(
transaction_app_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
date DATE NOT NULL,
user_sender_id BIGINT NOT NULL,
user_receiver_id BIGINT NOT NULL,
FOREIGN KEY (user_sender_id)
REFERENCES users (user_id),
FOREIGN KEY (user_receiver_id)
REFERENCES users (user_id),
transfered_amount FLOAT,
description_expense VARCHAR(100)
);

INSERT INTO transactions_app(date,user_sender_id,user_receiver_id,transfered_amount,description_expense)
VALUES
	('2022-05-21',1,2,10.0,'pique-nique parc'),
    ('2022-05-19',1,4,15.0,'cadeaux'),
    ('2022-05-18',1,3,20.0,'voyage'),
    ('2022-05-17',1,5,25.0,'location');




