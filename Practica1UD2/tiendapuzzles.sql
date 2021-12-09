CREATE DATABASE tiendapuzzles;
USE tiendapuzzles;
CREATE TABLE productos(
id int primary key auto_increment,
precio double,
marca varchar (40),
fecha_produccion timestamp);

delete from productos;