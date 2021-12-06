CREATE DATABASE vehiculos;

use vehiculos;


CREATE TABLE coches(
id int primary key auto_increment,

matricula varchar(30) unique,

marca varchar (40),

modelo varchar (40),

fecha_matriculacion timestamp
);





INSERT INTO coches(matricula, marca, modelo, fecha_matriculacion)
 
VALUES('FWD-1234', 'Peugeot', '507','2017-05-06');
 
 

select * from coches;