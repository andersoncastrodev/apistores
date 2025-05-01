create table sales (
   id serial not null,
   value_total decimal(10,2) not null,
   data_sales date not null,
   observation text,
   status_sales varchar(10) not null,
   type_payment varchar(10) not null,
   users_cod bigint not null,
   client_cod bigint not null,
   constraint pk_sales primary key (id),
   constraint fk_users foreign key (users_cod) references users_cod(id),
   constraint fk_client foreign key (client_cod) references client(id)
);