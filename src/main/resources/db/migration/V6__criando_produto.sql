create table produto(
   id serial not null,
   descricao varchar(150) not null,
   valor_compra decimal(10,2),
   valor_venda decimal(10,2),
   fornecedor_id int8 not null,
   constraint produto_pk primary key (id),
   constraint fk_fornecedor foreign key (fornecedor_id) references fornecedor (id)
);