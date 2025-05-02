create table address (
  id serial not null,
  street varchar(100) not null,
  cep varchar (10),
  id_city bigint not null,
  constraint address_pk primary key (id),
  constraint city_fk foreign key (id_city) references city(id)
);