DROP DATABASE IF EXISTS tiendapuzzles;
CREATE DATABASE IF NOT EXISTS tiendapuzzles;
USE tiendapuzzles;

-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 19-02-2022 a las 01:58:41
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tiendapuzzles`
--

DELIMITER $$
--
-- Funciones
--
CREATE DEFINER=`root`@`localhost` FUNCTION `existeIsbn` (`f_isbn` VARCHAR(40)) RETURNS BIT(1) begin
	declare i int;
    set i = 0;
    while ( i < (select max(idpuzzle) from puzzles)) do
    if  ((select isbn from puzzle where idpuzzle = (i + 1)) like f_isbn) then return 1;
    end if;
    set i = i + 1;
    end while;
    return 0;
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `existeNombreComprador` (`f_name` VARCHAR(202)) RETURNS BIT(1) begin
	declare i int;
    set i = 0;
    while ( i < (select max(idcomprador) from comprador)) do
    if  ((select concat(apellidos, ', ', nombre) from comprador where idcomprador = (i + 1)) like f_name) then return 1;
    end if;
    set i = i + 1;
    end while;
    return 0;
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `existeNombreEditorial` (`f_name` VARCHAR(50)) RETURNS BIT(1) begin
	declare i int;
    set i = 0;
    while ( i < (select max(ideditorial) from editoriales)) do
    if  ((select nombre from editorial where ideditorial = (i + 1)) like f_name) then return 1;
    end if;
    set i = i + 1;
    end while;
    return 0;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprador`
--

CREATE TABLE `comprador` (
  `idcomprador` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(150) NOT NULL,
  `dni` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `comprador`
--

INSERT INTO `comprador` (`idcomprador`, `nombre`, `apellidos`, `dni`) VALUES
(1, 'Marc', 'Gual', '535444201W'),
(2, 'Raul', 'Guti', '18568541J'),
(3, 'Josep', 'Pedrerol', '995487562D');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprador_puzzle`
--

CREATE TABLE `comprador_puzzle` (
  `idcompradorpuzzle` int(11) NOT NULL,
  `idcomprador` int(11) DEFAULT NULL,
  `idpuzzle` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprador_tienda`
--

CREATE TABLE `comprador_tienda` (
  `idcompradortienda` int(11) NOT NULL,
  `idcomprador` int(11) DEFAULT NULL,
  `idtienda` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `editorial`
--

CREATE TABLE `editorial` (
  `ideditorial` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `telefono` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `editorial`
--

INSERT INTO `editorial` (`ideditorial`, `nombre`, `telefono`) VALUES
(3, 'Santillana', '654321654'),
(5, 'Panamericana', '62421531'),
(6, 'Planeta', '632123451'),
(7, 'El Chiringuito', '601535486');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puzzle`
--

CREATE TABLE `puzzle` (
  `idpuzzle` int(11) NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `isbn` varchar(40) NOT NULL,
  `ideditorial` int(11) DEFAULT NULL,
  `idcomprador` int(11) DEFAULT NULL,
  `idtienda` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `puzzle`
--

INSERT INTO `puzzle` (`idpuzzle`, `titulo`, `isbn`, `ideditorial`, `idcomprador`, `idtienda`) VALUES
(10, 'Gabinete de crisis', '1', 7, NULL, NULL),
(11, 'La risa va por barrios', '2', 7, NULL, NULL),
(12, 'Wenger', '3', 7, NULL, NULL),
(15, 'Hazlo', '4', 7, NULL, NULL),
(16, 'Amanecer en Sierra Nevada', '5', 3, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puzzle_tienda`
--

CREATE TABLE `puzzle_tienda` (
  `idpuzzletienda` int(11) NOT NULL,
  `idpuzzle` int(11) DEFAULT NULL,
  `idtienda` int(11) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `puzzle_tienda`
--

INSERT INTO `puzzle_tienda` (`idpuzzletienda`, `idpuzzle`, `idtienda`, `precio`) VALUES
(10, 11, 5, 15),
(11, 12, 13, 14),
(12, 10, 5, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tienda`
--

CREATE TABLE `tienda` (
  `idtienda` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `telefono` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tienda`
--

INSERT INTO `tienda` (`idtienda`, `nombre`, `telefono`) VALUES
(5, 'Mercadona', '987654321'),
(13, 'MediaMarkt', '976542135');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comprador`
--
ALTER TABLE `comprador`
  ADD PRIMARY KEY (`idcomprador`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `comprador_puzzle`
--
ALTER TABLE `comprador_puzzle`
  ADD PRIMARY KEY (`idcompradorpuzzle`);

--
-- Indices de la tabla `comprador_tienda`
--
ALTER TABLE `comprador_tienda`
  ADD PRIMARY KEY (`idcompradortienda`);

--
-- Indices de la tabla `editorial`
--
ALTER TABLE `editorial`
  ADD PRIMARY KEY (`ideditorial`);

--
-- Indices de la tabla `puzzle`
--
ALTER TABLE `puzzle`
  ADD PRIMARY KEY (`idpuzzle`),
  ADD UNIQUE KEY `isbn` (`isbn`),
  ADD KEY `ideditorial` (`ideditorial`);

--
-- Indices de la tabla `puzzle_tienda`
--
ALTER TABLE `puzzle_tienda`
  ADD PRIMARY KEY (`idpuzzletienda`);

--
-- Indices de la tabla `tienda`
--
ALTER TABLE `tienda`
  ADD PRIMARY KEY (`idtienda`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comprador`
--
ALTER TABLE `comprador`
  MODIFY `idcomprador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `comprador_puzzle`
--
ALTER TABLE `comprador_puzzle`
  MODIFY `idcompradorpuzzle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `comprador_tienda`
--
ALTER TABLE `comprador_tienda`
  MODIFY `idcompradortienda` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `editorial`
--
ALTER TABLE `editorial`
  MODIFY `ideditorial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `puzzle`
--
ALTER TABLE `puzzle`
  MODIFY `idpuzzle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `puzzle_tienda`
--
ALTER TABLE `puzzle_tienda`
  MODIFY `idpuzzletienda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `tienda`
--
ALTER TABLE `tienda`
  MODIFY `idtienda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `puzzle`
--
ALTER TABLE `puzzle`
  ADD CONSTRAINT `puzzle_ibfk_1` FOREIGN KEY (`ideditorial`) REFERENCES `editorial` (`ideditorial`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
