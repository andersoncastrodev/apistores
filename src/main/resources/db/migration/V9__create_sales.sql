create table sales (
   id serial not null,
   value_total decimal(10,2) not null,
   data_sales date not null,
   observation varchar(100),
   status_sales varchar(10) not null,
   type_payment varchar(10) not null,
   id_users bigint not null,
   id_customer bigint not null,
   constraint pk_sales primary key (id),
   constraint fk_users foreign key (id_users) references users(id),
   constraint fk_customer foreign key (id_customer) references customer(id)
);