create table item_venda(
	id serial not null,
	venda_id bigint not null,
	produto_id bigint not null,
	quant bigint not null,
	valor_unid bigint not null,
	valor_total decimal(10,2) not null,
	constraint pk_item_venda primary key (id),
	constraint fk_produto_id foreign key(produto_id) references produto(id),
	constraint fk_venda_id foreign key (venda_id) references venda(id)
);