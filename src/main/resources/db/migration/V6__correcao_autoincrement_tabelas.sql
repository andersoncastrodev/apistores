create table estado(
  id serial not null,
  nome varchar(50),
  sigla varchar(2),
  constraint estado_pk primary key (id)
);

create table cidade(
  id serial not null,
  nome varchar(100) not null,
  id_estado int8 not null,

  constraint cidade_pk primary key (id),
  constraint estado_fk foreign key (id_estado) references estado(id)
);

create table endereco(
  id serial not null,
  rua varchar(100) not null,
  cep varchar (10),
  id_cidade int8 not null,

  constraint endereco_pk primary key (id),
  constraint cidade_fk foreign key (id_cidade) references cidade (id)
);

create table pessoa(
	id serial not null,
	nome varchar(100) not null,
	telefone varchar(50),
	constraint pessoa_pk primary key (id)
);

create table produto(
   id serial not null,
   descricao varchar(150) not null,
   valor_compra decimal(10,2),
   valor_venda decimal(10,2),
   constraint produto_pk primary key (id)
);