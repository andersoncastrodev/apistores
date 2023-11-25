create table iten_venda(
	id serial not null,
	venda_id bigint not null,
	quant bigint not null,
	valor_unid bigint not null,
	valor_total decimal(10,2) not null,
	constraint pk_iten_venda primary key (id),
	constraint fk_venda_id foreign key (venda_id) references venda(id)
);