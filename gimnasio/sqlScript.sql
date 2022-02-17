-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.8-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para gymhibernate
CREATE DATABASE IF NOT EXISTS `gymhibernate` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `gymhibernate`;

-- Volcando estructura para tabla gymhibernate.actividades
CREATE TABLE IF NOT EXISTS `actividades` (
  `idactividad` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) NOT NULL,
  `instalacion` varchar(50) NOT NULL,
  `horasSemanales` int(11) NOT NULL,
  `precio` float NOT NULL,
  PRIMARY KEY (`idactividad`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla gymhibernate.actividades: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `actividades` DISABLE KEYS */;
INSERT IGNORE INTO `actividades` (`idactividad`, `titulo`, `instalacion`, `horasSemanales`, `precio`) VALUES
	(2, 'Spinning', 'Gimnasio', 23, 35),
	(3, 'Relajación', 'SPA', 20, 12.29);
/*!40000 ALTER TABLE `actividades` ENABLE KEYS */;

-- Volcando estructura para tabla gymhibernate.actividades_materiales
CREATE TABLE IF NOT EXISTS `actividades_materiales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_actividad` int(11) NOT NULL,
  `id_material` int(11) DEFAULT NULL,
  `cantidad` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_actividad` (`id_actividad`),
  UNIQUE KEY `id_material` (`id_material`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla gymhibernate.actividades_materiales: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `actividades_materiales` DISABLE KEYS */;
/*!40000 ALTER TABLE `actividades_materiales` ENABLE KEYS */;

-- Volcando estructura para función gymhibernate.existeCodigoInstructor
DELIMITER //
CREATE FUNCTION `existeCodigoInstructor`(f_codigo varchar(40)) RETURNS bit(1)
begin
	declare i int;
    set i = 0;
    while ( i < (select max(idinstructor) from instructores)) do
    if  ((select codigoInstructor from instructores where idinstructor = (i+1)) like f_codigo) then return 1;
    end if;
    set i = i + 1;
    end while;
    return 0;
END//
DELIMITER ;

-- Volcando estructura para función gymhibernate.existeDniSocio
DELIMITER //
CREATE FUNCTION `existeDniSocio`(f_dni varchar(50)) RETURNS bit(1)
begin
	declare i int;
    set i = 0;
    while ( i < (select max(idsocio) from socios)) do
    if  ((select dni FROM socios where idsocio = (i+1)) like f_dni) then return 1;
    end if;
    set i = i + 1;
    end while;
    return 0;
end//
DELIMITER ;

-- Volcando estructura para tabla gymhibernate.gerentes
CREATE TABLE IF NOT EXISTS `gerentes` (
  `idgerente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `CodigoGerente` varchar(10) NOT NULL,
  `Direccion` varchar(50) NOT NULL,
  PRIMARY KEY (`idgerente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla gymhibernate.gerentes: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `gerentes` DISABLE KEYS */;
INSERT IGNORE INTO `gerentes` (`idgerente`, `nombre`, `apellidos`, `CodigoGerente`, `Direccion`) VALUES
	(1, 'Antonio', 'Labordeta', 'EUW20', 'Mi casa');
/*!40000 ALTER TABLE `gerentes` ENABLE KEYS */;

-- Volcando estructura para tabla gymhibernate.instructores
CREATE TABLE IF NOT EXISTS `instructores` (
  `idinstructor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(150) NOT NULL,
  `fechanacimiento` date DEFAULT NULL,
  `codigoInstructor` varchar(50) NOT NULL,
  PRIMARY KEY (`idinstructor`),
  UNIQUE KEY `codigoInstructor` (`codigoInstructor`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla gymhibernate.instructores: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `instructores` DISABLE KEYS */;
INSERT IGNORE INTO `instructores` (`idinstructor`, `nombre`, `apellidos`, `fechanacimiento`, `codigoInstructor`) VALUES
	(1, 'Jorge', 'De Haro', '2021-02-09', 'AWUU'),
	(2, 'Antonio', 'Rodriguez', '2021-02-10', 'PERR2');
/*!40000 ALTER TABLE `instructores` ENABLE KEYS */;

-- Volcando estructura para tabla gymhibernate.instructores_actividades
CREATE TABLE IF NOT EXISTS `instructores_actividades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_instructor` int(11) DEFAULT NULL,
  `id_actividad` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_instructor` (`id_instructor`),
  UNIQUE KEY `id_actividad` (`id_actividad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla gymhibernate.instructores_actividades: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `instructores_actividades` DISABLE KEYS */;
/*!40000 ALTER TABLE `instructores_actividades` ENABLE KEYS */;

-- Volcando estructura para tabla gymhibernate.material
CREATE TABLE IF NOT EXISTS `material` (
  `idmaterial` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `disponible` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idmaterial`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla gymhibernate.material: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT IGNORE INTO `material` (`idmaterial`, `nombre`, `disponible`) VALUES
	(1, 'Pesas', 1),
	(2, 'Donnut', 0),
	(5, 'Mancuerna', 1),
	(6, 'Jabón', 0);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;

-- Volcando estructura para tabla gymhibernate.material_proveedor
CREATE TABLE IF NOT EXISTS `material_proveedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_material` int(11) NOT NULL,
  `id_proveedor` int(11) NOT NULL,
  `fecha_pedido` date NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla gymhibernate.material_proveedor: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `material_proveedor` DISABLE KEYS */;
INSERT IGNORE INTO `material_proveedor` (`id`, `id_material`, `id_proveedor`, `fecha_pedido`, `cantidad`) VALUES
	(1, 1, 1, '2021-02-13', 10),
	(3, 5, 1, '2021-02-18', 2),
	(4, 6, 2, '2021-02-13', 23);
/*!40000 ALTER TABLE `material_proveedor` ENABLE KEYS */;

-- Volcando estructura para tabla gymhibernate.proveedores
CREATE TABLE IF NOT EXISTS `proveedores` (
  `idproveedor` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(50) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` int(10) NOT NULL,
  `fax` varchar(50) NOT NULL,
  PRIMARY KEY (`idproveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla gymhibernate.proveedores: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT IGNORE INTO `proveedores` (`idproveedor`, `tipo`, `direccion`, `telefono`, `fax`) VALUES
	(1, 'Elencos SA', 'Avenida Goya', 33223342, 'Fax Prueba'),
	(2, 'Musculitos Sanos SL', 'Parque Goya', 987676765, 'FAX DE PRUEBA');
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;

-- Volcando estructura para tabla gymhibernate.proveedores_gerentes
CREATE TABLE IF NOT EXISTS `proveedores_gerentes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_proveedor` int(11) DEFAULT NULL,
  `id_gerente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_proveedor` (`id_proveedor`),
  UNIQUE KEY `id_gerente` (`id_gerente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla gymhibernate.proveedores_gerentes: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `proveedores_gerentes` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedores_gerentes` ENABLE KEYS */;

-- Volcando estructura para tabla gymhibernate.registro
CREATE TABLE IF NOT EXISTS `registro` (
  `idregistro` int(11) NOT NULL AUTO_INCREMENT,
  `tablaCambiada` varchar(60) NOT NULL,
  `fechaCambio` date NOT NULL,
  `action` varchar(50) NOT NULL,
  PRIMARY KEY (`idregistro`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla gymhibernate.registro: ~29 rows (aproximadamente)
/*!40000 ALTER TABLE `registro` DISABLE KEYS */;
INSERT IGNORE INTO `registro` (`idregistro`, `tablaCambiada`, `fechaCambio`, `action`) VALUES
	(1, 'Socios', '2021-02-09', 'insert'),
	(2, 'Instructores', '2021-02-09', 'insert'),
	(4, 'Socios', '2021-02-09', 'insert'),
	(5, 'Socios', '2021-02-09', 'insert'),
	(6, 'Socios', '2021-02-09', 'delete'),
	(7, 'Socios', '2021-02-09', 'update'),
	(8, 'Socios', '2021-02-09', 'update'),
	(9, 'Socios', '2021-02-09', 'insert'),
	(10, 'Socios', '2021-02-09', 'update'),
	(11, 'Socios', '2021-02-09', 'delete'),
	(12, 'Socios', '2021-02-09', 'insert'),
	(13, 'Socios', '2021-02-09', 'delete'),
	(14, 'Socios', '2021-02-09', 'insert'),
	(15, 'Socios', '2021-02-09', 'delete'),
	(16, 'Socios', '2021-02-09', 'update'),
	(17, 'Socios', '2021-02-09', 'update'),
	(18, 'Socios', '2021-02-09', 'delete'),
	(19, 'Socios', '2021-02-09', 'insert'),
	(20, 'Instructores', '2021-02-10', 'insert'),
	(21, 'Instructores', '2021-02-10', 'insert'),
	(22, 'Socios', '2021-02-10', 'insert'),
	(23, 'Instructores', '2021-02-10', 'delete'),
	(24, 'Socios', '2021-02-10', 'insert'),
	(25, 'Socios', '2021-02-10', 'update'),
	(26, 'Socios', '2021-02-11', 'delete'),
	(27, 'Socios', '2021-02-11', 'update'),
	(28, 'Socios', '2021-02-13', 'update'),
	(29, 'Socios', '2021-02-13', 'update'),
	(30, 'Socios', '2021-02-15', 'update');
/*!40000 ALTER TABLE `registro` ENABLE KEYS */;

-- Volcando estructura para tabla gymhibernate.socios
CREATE TABLE IF NOT EXISTS `socios` (
  `idsocio` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(150) NOT NULL,
  `dni` varchar(11) NOT NULL,
  `fechanacimiento` date DEFAULT NULL,
  `tarifa` varchar(50) DEFAULT NULL,
  `idActividad` int(11) DEFAULT NULL,
  `idInstructor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idsocio`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla gymhibernate.socios: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `socios` DISABLE KEYS */;
INSERT IGNORE INTO `socios` (`idsocio`, `nombre`, `apellidos`, `dni`, `fechanacimiento`, `tarifa`, `idActividad`, `idInstructor`) VALUES
	(1, 'Nacho', 'De Haro', '77135722R', '2021-02-09', 'Anual', 1, 1),
	(7, 'Antonio', 'Perez', '221132', '2021-02-06', 'Trimestral', 1, 2),
	(8, 'Juan', 'Jose', '992332U', '2021-02-09', 'Socio de honor', 1, 1);
/*!40000 ALTER TABLE `socios` ENABLE KEYS */;

-- Volcando estructura para tabla gymhibernate.socios_gerentes
CREATE TABLE IF NOT EXISTS `socios_gerentes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_socio` int(11) DEFAULT NULL,
  `id_gerente` int(11) DEFAULT NULL,
  `fecha_alta` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_socio` (`id_socio`),
  UNIQUE KEY `id_gerente` (`id_gerente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla gymhibernate.socios_gerentes: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `socios_gerentes` DISABLE KEYS */;
/*!40000 ALTER TABLE `socios_gerentes` ENABLE KEYS */;

-- Volcando estructura para tabla gymhibernate.socios_instructores
CREATE TABLE IF NOT EXISTS `socios_instructores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_socio` int(11) DEFAULT NULL,
  `id_instructor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_socio` (`id_socio`),
  UNIQUE KEY `id_instructor` (`id_instructor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla gymhibernate.socios_instructores: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `socios_instructores` DISABLE KEYS */;
/*!40000 ALTER TABLE `socios_instructores` ENABLE KEYS */;

-- Volcando estructura para disparador gymhibernate.actualizar_registro_socios
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER actualizar_registro_socios
    BEFORE UPDATE ON socios
    FOR EACH ROW 
 INSERT INTO registro
 SET action = 'update',
     tablaCambiada = 'Socios',
     fechaCambio = NOW()//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador gymhibernate.delete_registro_instructores
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER delete_registro_instructores
    BEFORE DELETE ON instructores
    FOR EACH ROW 
 INSERT INTO registro
 SET action = 'delete',
     tablaCambiada = 'Instructores',
     fechaCambio = NOW()//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador gymhibernate.delete_registro_socios
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER delete_registro_socios
    BEFORE DELETE ON socios
    FOR EACH ROW 
 INSERT INTO registro
 SET action = 'delete',
     tablaCambiada = 'Socios',
     fechaCambio = NOW()//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador gymhibernate.insertar_registro_socios
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER insertar_registro_socios
    BEFORE INSERT ON socios
    FOR EACH ROW 
 INSERT INTO registro
 SET action = 'insert',
     tablaCambiada = 'Socios',
     fechaCambio = NOW()//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador gymhibernate.insert_registro_instructores
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER insert_registro_instructores
    BEFORE INSERT ON instructores
    FOR EACH ROW 
 INSERT INTO registro
 SET action = 'insert',
     tablaCambiada = 'Instructores',
     fechaCambio = NOW()//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador gymhibernate.update_registro_instructores
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER update_registro_instructores
    BEFORE UPDATE ON instructores
    FOR EACH ROW 
 INSERT INTO registro
 SET action = 'update',
     tablaCambiada = 'Instructores',
     fechaCambio = NOW()//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
