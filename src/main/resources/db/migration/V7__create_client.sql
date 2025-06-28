create table client (
	id serial not null,
	type_person varchar(10) not null,
	cpf_cnpj varchar(15) not null,
    rg_ie varchar(15) not null,
    name varchar(100) not null,
    gender varchar(15) not null,
    date_birth date not null,
    telephone_first varchar(15) not null,
    telephone_second varchar(15) not null,
    email varchar(100) not null,
    date_register date not null,
    status varchar(10) not null,
    observation varchar(100),
    id_address bigint not null,
 constraint client_pk primary key (id),
 constraint client_fk foreign key (id_address) references address(id)
);