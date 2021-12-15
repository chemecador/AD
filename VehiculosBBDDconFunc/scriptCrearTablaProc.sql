DELIMITER //
CREATE PROCEDURE crearTablaCoches()
BEGIN
    CREATE TABLE coches(
                           id int primary key auto_increment,
                           matricula varchar(30) unique,
                           marca varchar (40),
                           modelo varchar (40),
                           fecha_matriculacion timestamp);
END //
