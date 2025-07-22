create table items_sale (
	id serial not null,
	quant bigint not null,
	value_unid bigint not null,
	value_total decimal(10,2) not null,
	id_sales bigint not null,
    id_product bigint not null,
	constraint pk_item_venda primary key (id),
	constraint fk_sales_id foreign key (id_sales) references sales (id),
	constraint fk_product_id foreign key(id_product) references product (id)
);