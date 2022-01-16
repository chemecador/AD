CREATE DATABASE if not exists tiendapuzzles;
--
USE tiendapuzzles;
--
create table if not exists compradores(
idcomprador int auto_increment primary key,
nombre varchar(50) not null,
apellidos varchar(150) not null,
dni varchar(10),
fechacompra date,
pais varchar(50));
--
create table if not exists editoriales (
ideditorial int auto_increment primary key,
editorial varchar(50) not null,
email varchar(100) not null,
telefono varchar(9),
antiguedad int,
reputacion varchar(10),
web varchar(500));
--
create table if not exists puzzles(
idpuzzle int auto_increment primary key,
titulo varchar(50) not null,
isbn varchar(40) not null UNIQUE,
ideditorial int not null,
genero varchar(30),
idcomprador int not null,
precio float not null,
fechaedicion date);
--
alter table puzzles
	add foreign key (ideditorial) references editoriales(ideditorial),
    add foreign key (idcomprador) references compradores(idcomprador);
--
create function existeIsbn(f_isbn varchar(40))
returns bit
begin
	declare i int;
    set i = 0;
    while ( i < (select max(idpuzzle) from puzzles)) do
    if  ((select isbn from puzzles where idpuzzle = (i + 1)) like f_isbn) then return 1;
    end if;
    set i = i + 1;
    end while;
    return 0;
end;
--
create function existeNombreEditorial(f_name varchar(50))
returns bit
begin
	declare i int;
    set i = 0;
    while ( i < (select max(ideditorial) from editoriales)) do
    if  ((select editorial from editoriales where ideditorial = (i + 1)) like f_name) then return 1;
    end if;
    set i = i + 1;
    end while;
    return 0;
end;
--
create function existeNombreComprador(f_name varchar(202))
returns bit
begin
	declare i int;
    set i = 0;
    while ( i < (select max(idcomprador) from compradores)) do
    if  ((select concat(apellidos, ', ', nombre) from compradores where idcomprador = (i + 1)) like f_name) then return 1;
    end if;
    set i = i + 1;
    end while;
    return 0;
end;
