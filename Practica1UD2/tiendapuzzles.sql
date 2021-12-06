CREATE DATABASE tiendapuzzles;
USE tiendapuzzles;
CREATE TABLE productos(
id int primary key auto_increment,
precio double,
marca varchar (40),
fecha_produccion timestamp);

select * from productos;
insert into productos values (1,15,'santillana',null);