create table forma_pagamento(
	id serial not null,
	descricao varchar(50) not null,
	tipo_pagamento bigint not null,
	constraint pk_forma_pagamento primary key (id)
);