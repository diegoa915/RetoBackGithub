create database products;


use products;
select * from products;
insert into products(id,producto,precio) values (1,'secadora',75000);
insert into products(producto,precio) values ('Pulidora',270000);
insert into products(producto,precio) values ('Juego de Ollas',85000);
insert into products(producto,precio) values ('Tapete',86000);
insert into products(producto,precio) values ('Cubiertos',72000);
insert into products(producto,precio) values ('Plancha',105000);

truncate table products