DROP database IF EXISTS paymybuddyp6;
create database paymybuddyp6;
use paymybuddyp6;

CREATE TABLE users(
user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
email VARCHAR(100) NOT NULL UNIQUE,
pass_word VARCHAR(100) NOT NULL,
enabled  TINYINT NOT NULL DEFAULT 1,
amount_app_account FLOAT
);

INSERT INTO users (email, pass_word, amount_app_account,enabled)
values ('spring@user.fr','$2y$10$IG1wF5QJOqgQDtBIu5gvw.PFmUyXC5anuV540DVbjvoAD3mNMiWpG',100.0,1);
INSERT INTO users (email, pass_word, amount_app_account,enabled)
values ('spring@admin.fr','$2y$10$D7n1YG3ioOx0cXNRUMMG2Owf8JTei0zDuAf2qzbditZsmGuE0O5s2',150.0,1);

CREATE TABLE authorities
(
authority_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
email VARCHAR(100) NOT NULL UNIQUE,
authority VARCHAR(50) NOT NULL,
FOREIGN KEY (email) 
REFERENCES users (email)
);

INSERT INTO authorities (email,authority)
values ('spring@user.fr','USER');
INSERT INTO authorities (email,authority)
values ('spring@admin.fr','ADMIN');




CREATE TABLE bank_accounts(
bank_account_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
user_id BIGINT NOT NULL,
FOREIGN KEY (user_id)
REFERENCES users (user_id)
);

CREATE TABLE transactions_bank(
transaction_bank_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
date DATE NOT NULL,
amount_transfered FLOAT,
bank_account_id BIGINT NOT NULL,
FOREIGN KEY (bank_account_id)
REFERENCES bank_accounts (bank_account_id)
);

CREATE TABLE assoc_users_users(
user_live_id BIGINT NOT NULL,
user_ressource_id BIGINT NOT NULL,
FOREIGN KEY (user_live_id)
REFERENCES users (user_id),
FOREIGN KEY (user_ressource_id)
REFERENCES users (user_id),
PRIMARY KEY(user_live_id,user_ressource_id)
);

CREATE TABLE transactions_app(
transaction_app_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
date DATE NOT NULL,
user_sender_id BIGINT NOT NULL,
user_receiver_id BIGINT NOT NULL,
FOREIGN KEY (user_sender_id)
REFERENCES users (user_id),
FOREIGN KEY (user_receiver_id)
REFERENCES users (user_id),
transfered_amount FLOAT
);




