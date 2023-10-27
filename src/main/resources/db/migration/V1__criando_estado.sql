create table estado(
  id serial not null,
  nome varchar(50),
  sigla varchar(2),
  constraint estado_pk primary key (id)
);