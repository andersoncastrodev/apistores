create table users_roles(
	id serial not null,
	id_users bigint not null,
	id_roles bigint not null,
	constraint pk_users_roles primary key (id),
	constraint fk_id_users foreign key (id_users) references users (id),
	constraint fk_id_roles foreign key(id_roles) references roles (id)
);