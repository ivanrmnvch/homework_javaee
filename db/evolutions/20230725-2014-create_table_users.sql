CREATE TABLE users (
   id SERIAL NOT NULL PRIMARY KEY,
   login varchar(200) NOT NULL,
   pass TEXT NOT NULL,
   email varchar(200) NOT NULL,
   "fName" varchar(32) NOT NULL,
   "lName" varchar(32),
   blocked boolean,
   role varchar(30) DEFAULT 'user' NOT NULL,
   updated timestamp with time zone NOT NULL,
   created timestamp with time zone NOT NULL
);