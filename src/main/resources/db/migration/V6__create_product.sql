create table product (
   id serial not null,
   description varchar(150) not null,
   quant int8 not null,
   quant_min int8 not null,
   quant_max int8 not null,
   quant_stock int8 not null,
   quant_min_stock int8 not null,
   unit varchar(10) not null,
   weight decimal(10,2),
   value_buy decimal(10,2),
   value_sell decimal(10,2),
   supplier_id int8 not null,
   constraint product_pk primary key (id),
   constraint fk_supplier foreign key (supplier_id) references supplier(id)
);