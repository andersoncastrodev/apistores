create table sales (
   id serial not null,
   value_total decimal(10,2) not null,
   data_sales date not null,
   observation text,
   status_sales varchar(10) not null,
   type_payment varchar(10) not null,
   id_users bigint not null,
   id_client bigint not null,
   constraint pk_sales primary key (id),
   constraint fk_users foreign key (id_users) references users(id),
   constraint fk_client foreign key (id_client) references client(id)
);