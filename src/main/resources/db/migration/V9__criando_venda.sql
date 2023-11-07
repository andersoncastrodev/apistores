create table venda(
   id serial not null,
   usuario_cod bigint not null,
   cliente_cod bigint not null,
   valor_total decimal(10,2) not null,
   constraint pk_venda primary key (id),
   constraint fk_usuario foreign key (usuario_cod) references usuario(id),
   constraint fk_cliente foreign key (cliente_cod) references cliente(id)
);