CREATE TABLE user (
  id bigint not null primary key auto_increment,
  full_name varchar(60) not null,
  email varchar(100) not null unique,
  username varchar(50) not null unique,
  password varchar(255) not null
);