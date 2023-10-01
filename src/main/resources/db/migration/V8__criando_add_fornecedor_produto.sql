alter table produto add fornecedor_id int8;
alter table produto add constraint fk_fornecedor foreign key (fornecedor_id) references fornecedor (id);