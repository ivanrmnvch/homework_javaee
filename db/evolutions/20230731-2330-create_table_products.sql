CREATE TABLE products (
   id SERIAL NOT NULL PRIMARY KEY,
   name varchar(200) NOT NULL,
   description text NOT NULL,
   price varchar(200) NOT NULL,
   imagePath varchar(200) NOT NULL,
   brand varchar(200) NOT NULL,
   category varchar(200) NOT NULL
);