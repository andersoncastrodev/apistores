create table pessoa(
	id int8 not null,
	nome varchar(100) not null,
	telefone varchar(50),
	constraint pessoa_pk primary key (id)
);