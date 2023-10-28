create table usuario(
   id serial not null,
   login varchar(50) not null,
   senha varchar(50) not null,
   pessoa_id bigint,

   constraint usuario_pk primary key (id),
   constraint pessoa_fk foreign key (pessoa_id) references pessoa(id)
);