drop database gymbbdd;
CREATE DATABASE if not exists gymbbdd;
--
USE gymbbdd;
--
CREATE TABLE if NOT EXISTS socios(
idsocio int auto_increment primary key,
nombre varchar(50) NOT NULL,
apellidos varchar(150) NOT NULL,
dni VARCHAR(11) NOT NULL,
fechanacimiento date,
tarifa varchar(50),
idActividad INT NOT NULL,
idInstructor INT NOT null);
--
CREATE TABLE if NOT EXISTS instructores (
idinstructor int auto_increment primary key,
nombre varchar(50) NOT NULL,
apellidos varchar(150) NOT NULL,
fechanacimiento date,
codigoInstructor varchar(50) NOT NULL UNIQUE);
--
CREATE TABLE if NOT EXISTS actividades(
idactividad int auto_increment primary key,
titulo varchar(50) NOT NULL,
instalacion VARCHAR(50) NOT NULL,
horasSemanales int NOT NULL,
precio float NOT NULL);
--
CREATE TABLE if NOT EXISTS registro(
idregistro INT AUTO_INCREMENT PRIMARY KEY,
tablaCambiada VARCHAR(60) NOT NULL,
fechaCambio DATE NOT NULL,
action VARCHAR(50) NOT NULL);
--
alter table socios
	add foreign key (idActividad) references actividades(idactividad);
alter table socios    
    add foreign key (idInstructor) references instructores(idinstructor);
--
delimiter ||
create function existeCodigoInstructor(f_codigo varchar(40))
returns bit
begin
	declare i int;
    set i = 0;
    while ( i < (select max(idinstructor) from instructores)) do
    if  ((select codigoInstructor from instructores where idinstructor = (i+1)) like f_codigo) then return 1;
    end if;
    set i = i + 1;
    end while;
    return 0;
END; 
||
--
delimiter ||
create function existeDniSocio(f_dni varchar(50))
returns bit
begin
	declare i int;
    set i = 0;
    while ( i < (select max(idsocio) from socios)) do
    if  ((select dni FROM socios where idsocio = (i+1)) like f_dni) then return 1;
    end if;
    set i = i + 1;
    end while;
    return 0;
end; 
||
--
delimiter ||
CREATE TRIGGER actualizar_registro_socios
    BEFORE UPDATE ON socios
    FOR EACH ROW 
 INSERT INTO registro
 SET action = 'update',
     tablaCambiada = 'Socios',
     fechaCambio = NOW();
||
--
delimiter ||
CREATE TRIGGER insertar_registro_socios
    BEFORE INSERT ON socios
    FOR EACH ROW 
 INSERT INTO registro
 SET action = 'insert',
     tablaCambiada = 'Socios',
     fechaCambio = NOW();
||
--
delimiter ||
CREATE TRIGGER delete_registro_socios
    BEFORE DELETE ON socios
    FOR EACH ROW 
 INSERT INTO registro
 SET action = 'delete',
     tablaCambiada = 'Socios',
     fechaCambio = NOW();
||
--
delimiter ||
CREATE TRIGGER update_registro_instructores
    BEFORE UPDATE ON instructores
    FOR EACH ROW 
 INSERT INTO registro
 SET action = 'update',
     tablaCambiada = 'Instructores',
     fechaCambio = NOW();
||
--
delimiter ||
CREATE TRIGGER insert_registro_instructores
    BEFORE INSERT ON instructores
    FOR EACH ROW 
 INSERT INTO registro
 SET action = 'insert',
     tablaCambiada = 'Instructores',
     fechaCambio = NOW();
||
--
delimiter ||
CREATE TRIGGER delete_registro_instructores
    BEFORE DELETE ON instructores
    FOR EACH ROW 
 INSERT INTO registro
 SET action = 'delete',
     tablaCambiada = 'Instructores',
     fechaCambio = NOW();
||
