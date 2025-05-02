create table state (
  id serial not null,
  name varchar(50),
  acronym varchar(2),
  constraint state_pk primary key (id)
);