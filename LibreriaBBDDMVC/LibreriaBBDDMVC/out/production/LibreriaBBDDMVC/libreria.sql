CREATE DATABASE libreria;
USE libreria;

CREATE TABLE libros(
id int auto_increment primary key,
isbn VARCHAR(20) not null UNIQUE,
titulo VARCHAR(40) not null,
autor VARCHAR(50),
fecha_publicacion TIMESTAMP
);
