CREATE TABLE course (
  id bigint not null primary key auto_increment,
  title varchar(200) not null,
  description varchar(255),
  is_active boolean,
  price numeric(10, 2) not null
);