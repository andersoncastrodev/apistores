create table address (
  id serial not null,
  street varchar(100) not null,
  numbers varchar(20) not null,
  neighborhood varchar(100) not null,
  city varchar(100) not null,
  state varchar(2) not null,
  cep varchar(10) not null,
  complement varchar(100),
  type_address varchar(20) not null,
  constraint address_pk primary key (id)
);