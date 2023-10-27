create table pessoa(
	id serial not null,
	nome varchar(100) not null,
	telefone varchar(50),
	constraint pessoa_pk primary key (id)
);