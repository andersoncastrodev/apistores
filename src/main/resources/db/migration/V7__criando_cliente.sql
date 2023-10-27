create table cliente(
	id serial not null,
	tipo varchar(50) not null,
	pessoa_id int8 not null,
 constraint cliente_pk primary key (id),
 constraint pessoa_fk foreign key (pessoa_id) references pessoa (id)
);