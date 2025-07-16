create table product (
   id serial not null,
   description varchar(150) not null,
   quant bigint not null,
   quant_min bigint not null,
   quant_max bigint not null,
   quant_stock bigint not null,
   quant_min_stock bigint not null,
   unit varchar(10) not null,
   weight decimal(10,2),
   value_buy decimal(10,2),
   value_sell decimal(10,2),
   id_supplier bigint not null,
   constraint product_pk primary key (id),
   constraint fk_supplier foreign key (id_supplier) references supplier(id)
);