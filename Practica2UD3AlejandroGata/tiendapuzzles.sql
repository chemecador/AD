DROP DATABASE if exists tiendapuzzles;
CREATE DATABASE if not exists tiendapuzzles;
--
USE tiendapuzzles;
--
create table if not exists editorial (
ideditorial int auto_increment primary key,
nombre varchar(50) not null,
telefono varchar(9));
--
create table if not exists tienda(
idtienda int auto_increment primary key,
nombre varchar(50) not null,
telefono varchar(9));
--
create table if not exists comprador(
idcomprador int auto_increment primary key,
nombre varchar(50) not null,
apellidos varchar(150) not null,
dni varchar(10) not null UNIQUE);
--
create table if not exists puzzle(
idpuzzle int auto_increment primary key,
titulo varchar(50) not null,
isbn varchar(40) not null UNIQUE,
precio float not null,
ideditorial int,
idcomprador int,
idtienda int,
foreign key(ideditorial) references editorial(ideditorial));
--
create table if not exists comprador_puzzle(
idcompradorpuzzle int auto_increment primary key,
idcomprador int,
idpuzzle int);
-- 
create table if not exists puzzle_tienda(
idpuzzletienda int auto_increment primary key,
idpuzzle int,
idtienda int,
precio int);
-- 
create table if not exists comprador_tienda(
idcompradortienda int auto_increment primary key,
idcomprador int,
idtienda int);
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
delimiter ;comprador_tienda