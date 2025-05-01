create table item_venda(
	id serial not null,
	quant bigint not null,
	value_unid bigint not null,
	value_total decimal(10,2) not null,
	sales_id bigint not null,
    product_id bigint not null,
	constraint pk_item_venda primary key (id),
	constraint fk_sales_id foreign key (sales_id) references sales (id),
	constraint fk_product_id foreign key(product_id) references product (id)
);