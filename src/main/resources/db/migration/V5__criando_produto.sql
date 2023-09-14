create table produto(
   id int8 not null,
   descricao varchar(150) not null,
   valor_compra decimal(10,2),
   valor_venda decimal(10,2),
   constraint produto_pk primary key (id)
);