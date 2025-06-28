create table users (
   id serial not null,
   name varchar(50) not null,
   gender varchar(15) not null,
   date_birth date not null,
   email varchar(50) not null,
   telephone_first varchar(15) not null,
   telephone_second varchar(15) not null,
   cpf varchar(50) not null,
   login varchar(80) not null,
   password varchar(100) not null,
   date_register date not null,
   status varchar(10) not null,
   observation varchar(100),
   id_address bigint not null,
   constraint users_pk primary key (id),
   constraint address_fk foreign key (id_address) references address(id)
);