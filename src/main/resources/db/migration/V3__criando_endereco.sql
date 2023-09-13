create table endereco(
  id int8 not null,
  rua varchar(100) not null,
  cep varchar (10),
  id_cidade int8 not null,

  constraint endereco_pk primary key (id),
  constraint cidade_fk foreign key (id_cidade) references cidade (id)
);