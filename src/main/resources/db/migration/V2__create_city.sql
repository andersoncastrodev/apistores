create table city (
  id serial not null,
  name varchar(100) not null,
  id_state int8 not null,
  constraint city_pk primary key (id),
  constraint state_fk foreign key (id_state) references state(id)
);
