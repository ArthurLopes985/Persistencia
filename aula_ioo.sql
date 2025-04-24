create database aula_ioo;

use aula_ioo;

create table produto(
id serial,
descricao character varying(50),
preco double precision
);

insert into produto (id, descricao, preco) values(
1, "mouse", 52.00),
(2, "teclado", 200.00);

select * from produto;

