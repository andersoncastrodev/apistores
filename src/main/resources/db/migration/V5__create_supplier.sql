create table supplier (
	id serial not null,
	type_person varchar(10) not null,
    cpf_cnpj varchar(15) not null,
    rg_ie varchar(15) not null,
    name varchar(100) not null,
    name_fantasy varchar(100) not null,
    phone_number varchar(15) not null,
    email varchar(100) not null,
    id_address bigint not null,
	constraint supplier_pk primary key (id),
    constraint supplier_fk foreign key (id_address) references address(id)
);
