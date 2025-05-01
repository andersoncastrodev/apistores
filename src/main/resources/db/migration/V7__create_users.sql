create table users (
   id serial not null,
   name varchar(50) not null,
   date_birth date not null,
   email varchar(50) not null,
   phone varchar(50) not null,
   cpf varchar(50) not null,
   login varchar(50) not null,
   senha varchar(50) not null,
   id_address bigint not null,
   constraint users_pk primary key (id),
   constraint address_fk foreign key (id_address) references address(id)
);