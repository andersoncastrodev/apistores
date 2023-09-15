create table fornecedor(
	id serial not null,
	nome varchar(100) not null,
	nome_fantasia varchar(100),
	cpfcnpj varchar(15) not null,

	constraint fornecedor_fk primary key (id)
);
