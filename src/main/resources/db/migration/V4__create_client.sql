create table client (
	id serial not null,
	type_person varchar(10) not null,
	cpf_cnpj varchar(15) not null,
    rg_ie varchar(15) not null,
    name varchar(100) not null,
    name_fantasy varchar(100) not null,
    date_birth date not null,
    phone_number varchar(15) not null,
    email varchar(100) not null,
    address_id int8 not null,
 constraint client_pk primary key (id),
 constraint client_fk foreign key (address_id) references address(id)
);