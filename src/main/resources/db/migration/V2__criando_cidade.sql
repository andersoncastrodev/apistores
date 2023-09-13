create table cidade(
  id int8 not null,
  nome varchar(100) not null,
  id_estado int8 not null,

  constraint cidade_pk primary key (id),
  constraint estado_fk foreign key (id_estado) references estado(id)
);