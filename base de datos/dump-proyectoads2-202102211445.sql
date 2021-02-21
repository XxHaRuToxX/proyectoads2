-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: proyectoads2
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.38-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `boleta`
--

DROP TABLE IF EXISTS `boleta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boleta` (
  `id_boleta` varchar(7) NOT NULL,
  `fecha` date DEFAULT NULL,
  `precio_total` double DEFAULT NULL,
  `id_trabajador` int(11) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_boleta`),
  KEY `id_trabajador` (`id_trabajador`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `boleta_ibfk_1` FOREIGN KEY (`id_trabajador`) REFERENCES `trabajador` (`id_trabajador`),
  CONSTRAINT `boleta_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boleta`
--

LOCK TABLES `boleta` WRITE;
/*!40000 ALTER TABLE `boleta` DISABLE KEYS */;
/*!40000 ALTER TABLE `boleta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boleta_compra`
--

DROP TABLE IF EXISTS `boleta_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boleta_compra` (
  `id_boleta_compra` varchar(7) NOT NULL,
  `fecha` date DEFAULT NULL,
  `precio_total_compra` double DEFAULT NULL,
  `id_proveedor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_boleta_compra`),
  KEY `id_proveedor` (`id_proveedor`),
  CONSTRAINT `boleta_compra_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boleta_compra`
--

LOCK TABLES `boleta_compra` WRITE;
/*!40000 ALTER TABLE `boleta_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `boleta_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `id_persona` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  KEY `id_persona` (`id_persona`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_boleta`
--

DROP TABLE IF EXISTS `detalle_boleta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_boleta` (
  `id_detalle_boleta` varchar(5) NOT NULL,
  `cantidad_producto` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_boleta` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_boleta`),
  KEY `id_producto` (`id_producto`),
  KEY `id_boleta` (`id_boleta`),
  CONSTRAINT `detalle_boleta_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  CONSTRAINT `detalle_boleta_ibfk_2` FOREIGN KEY (`id_boleta`) REFERENCES `boleta` (`id_boleta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_boleta`
--

LOCK TABLES `detalle_boleta` WRITE;
/*!40000 ALTER TABLE `detalle_boleta` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_boleta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_compra`
--

DROP TABLE IF EXISTS `detalle_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_compra` (
  `id_detalle_compra` varchar(5) NOT NULL,
  `cantidad` double DEFAULT NULL,
  `id_boleta_compra` varchar(7) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_compra`),
  KEY `id_boleta_compra` (`id_boleta_compra`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `detalle_compra_ibfk_1` FOREIGN KEY (`id_boleta_compra`) REFERENCES `boleta_compra` (`id_boleta_compra`),
  CONSTRAINT `detalle_compra_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_compra`
--

LOCK TABLES `detalle_compra` WRITE;
/*!40000 ALTER TABLE `detalle_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `id_persona` int(11) NOT NULL,
  `nombres` varchar(30) DEFAULT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  `dni` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Javier Ernesto','Moreno LLoclle','62020554'),(2,'Elvis Haruto','Sanchéz jiménez','52826559'),(3,'kevin','cahuantico','73937385');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `precio_producto` double DEFAULT NULL,
  `nombre_producto` varchar(30) DEFAULT NULL,
  `desc_producto` varchar(200) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,9.7,'Forastero Amazónico','Esta es la variedad más común, pero también la más robusta y la que da más cantidad de frutos. Produce el grano menos aromático.',1000),(2,19.4,'Criollo','Se obtiene el cacao de mayor calidad, pero su producción representa menos de un 10% del total mundial. ',1000),(3,15.8,'Trinitario','Es un híbrido de las plantas de criollo y forastero, combinando las características gustativas y olfativas de ambos. Este contiene un amplio rango de sabores.',1000);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `id_proveedor` int(11) NOT NULL,
  `direccion_proveedor` varchar(150) DEFAULT NULL,
  `razon_social` varchar(50) DEFAULT NULL,
  `ruc_proveedor` varchar(50) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`id_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'Jron atahuallpa mz f lote 1','las conejitas','01245719841','940029541'),(2,'Jron atahuallpa mz f lote 1','las conejitas','01245719841','940029541');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajador`
--

DROP TABLE IF EXISTS `trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trabajador` (
  `id_trabajador` int(11) NOT NULL,
  `cargo` varchar(20) DEFAULT NULL,
  `direccion_trabajador` varchar(150) DEFAULT NULL,
  `genero` varchar(30) DEFAULT NULL,
  `contraseña` varchar(20) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `id_persona` int(11) DEFAULT NULL,
  `usuario` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_trabajador`),
  KEY `id_persona` (`id_persona`),
  CONSTRAINT `trabajador_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajador`
--

LOCK TABLES `trabajador` WRITE;
/*!40000 ALTER TABLE `trabajador` DISABLE KEYS */;
INSERT INTO `trabajador` VALUES (1,'administrador','jron cusco','hombre','123456','940029541',1,'20javierem'),(2,'almacenero','jron cusco con alameda','hombre','123456','934567609',2,'30javierem'),(3,'contador','JRON puno','mascuiino','123456','959202854',3,'rat');
/*!40000 ALTER TABLE `trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'proyectoads2'
--
/*!50003 DROP PROCEDURE IF EXISTS `gestionarProducto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `gestionarProducto`(
in op char,
in idp int,
in preciop double,
in nomp varchar(30),
in descp varchar(200),
in cantidad int)
begin
	declare varid int;
	if op='i'
		then 
			select max(id_producto)+1 into varid from producto;
			insert into producto values (varid,preciop,nomp,descp,cantidad);		
	else 
	if op='a' 
		then update producto set nombre_producto=nomp,stock=cantidad,desc_producto =descp,precio_producto=preciop where id_producto=idp;
	else if op='e' then
		delete from producto where id_producto=idp;
	end if;
	end if;
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `gestionarProveedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `gestionarProveedor`(
in op char,
in idp int,
in dirp varchar(150),
in razp varchar(150),
in rucp varchar(150),
in telp varchar(9))
begin 
declare varid int;
	if op='i'
		then 
			select max(id_proveedor)+1 into varid from proveedor;
			insert into proveedor values(varid,dirp,razp,rucp,telp);
	else
	if op='a'
		then
			update proveedor set direccion_proveedor=dirp,razon_social=razp,ruc_proveedor=rucp,telefono=telp where id_proveedor=idp;
		else
			delete from proveedor where id_proveedor=idp;
	end if;
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `gestionartrabajador` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `gestionartrabajador`(
in op char,
in pid int,
in tcontraseña varchar(30),
in pnombres varchar(30),
in papellidos varchar(30),
in pdni varchar(30),
in pgenero varchar(30),
in ttelefono varchar(30),
in tcargo varchar(30),
in tdireccion varchar(30),
in tusuario varchar(30))
begin
	declare varid int; 
	if op='i'
		then 
			 select max(id_persona)+1 into varid from persona;
			 insert into persona values (varid,pnombres,papellidos,pdni);
			 insert into trabajador values(varid,tcargo,tdireccion,pgenero,tcontraseña,ttelefono,varid,tusuario);
			
	else 
	if op='a' 
		then update trabajador set telefono=ttelefono,cargo =tcargo, direccion_trabajador=tdireccion, contraseña=tcontraseña, usuario=tusuario where id_persona =pid;
					update persona set nombres=pnombres, apellidos=papellidos, dni=pdni where id_persona=pid;
	else if op='e'
			then
		delete from trabajador where id_persona=pid;
		delete from persona where id_persona =pid;
	end if;
	end if;
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-21 14:45:52
