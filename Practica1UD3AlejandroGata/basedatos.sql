DROP DATABASE if exists tiendapuzzles;
CREATE DATABASE if not exists tiendapuzzles;
--
USE tiendapuzzles;
--
create table if not exists coleccion(
idcoleccion int auto_increment primary key,
cantidad int not null,
valor float not null);
--
create table if not exists editorial (
ideditorial int auto_increment primary key,
editorial varchar(50) not null,
email varchar(100) not null,
telefono varchar(9),
antiguedad int,
reputacion varchar(10),
web varchar(500));
--
create table if not exists dependiente(
iddependiente int auto_increment primary key,
nombre varchar(50),
apellidos varchar(50));
--
create table if not exists tienda(
idtienda int auto_increment primary key,
nombre varchar(50) not null, 
direccion varchar(100) not null,
telefono varchar(9),
iddependiente int,
foreign key (iddependiente) references dependiente(iddependiente));
--
create table if not exists comprador(
idcomprador int auto_increment primary key,
nombre varchar(50) not null,
apellidos varchar(150) not null,
dni varchar(10),
fechacompra date, 
idcoleccion int,
idtienda int,
foreign key (idcoleccion) references coleccion(idcoleccion),
foreign key (idtienda) references tienda(idtienda));
--
create table if not exists puzzle(
idpuzzle int auto_increment primary key,
titulo varchar(50) not null,
isbn varchar(40) not null UNIQUE,
genero varchar(30),
precio float not null,
fechaedicion date,
ideditorial int,
idcomprador int,
idtienda int,
foreign key(ideditorial) references editorial(ideditorial),
foreign key(idcomprador) references comprador(idcomprador),
foreign key(idtienda) references tienda(idtienda));
--
create table if not exists comprador_puzzle(
idcompradorpuzzle int auto_increment primary key,
idcomprador int,
idpuzzle int,
preciopuzzle int,
fechacompra date);
-- 
create table if not exists puzzle_tienda(
idpuzzletienda int auto_increment primary key,
idpuzzle int,
idtienda int,
precio int,
fechaventa date);
-- 
create table if not exists comprador_tienda(
idcompradortienda int auto_increment primary key,
idcomprador int,
idtienda int,
preciototal int,
fechavisita date);
--
delimiter ||
create function existeIsbn(f_isbn varchar(40))
returns bit
begin
	declare i int;
    set i = 0;
    while ( i < (select max(idpuzzle) from puzzles)) do
    if  ((select isbn from puzzle where idpuzzle = (i + 1)) like f_isbn) then return 1;
    end if;
    set i = i + 1;
    end while;
    return 0;
end; ||
delimiter ;
--
delimiter ||
create function existeNombreEditorial(f_name varchar(50))
returns bit
begin
	declare i int;
    set i = 0;
    while ( i < (select max(ideditorial) from editoriales)) do
    if  ((select nombre from editorial where ideditorial = (i + 1)) like f_name) then return 1;
    end if;
    set i = i + 1;
    end while;
    return 0;
end; ||
delimiter ;
--
delimiter ||
create function existeNombreComprador(f_name varchar(202))
returns bit
begin
	declare i int;
    set i = 0;
    while ( i < (select max(idcomprador) from comprador)) do
    if  ((select concat(apellidos, ', ', nombre) from comprador where idcomprador = (i + 1)) like f_name) then return 1;
    end if;
    set i = i + 1;
    end while;
    return 0;
end; ||
delimiter ;
