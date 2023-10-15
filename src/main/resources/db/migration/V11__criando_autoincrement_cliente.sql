create table cliente(
	id serial not null,
	tipo varchar(50),
	pessoa_id bigint,
 constraint cliente_pk primary key (id),
 constraint pessoa_fk foreign key (pessoa_id) references pessoa(id)
);

