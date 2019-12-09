-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: liga1920
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `arbitro`
--

DROP TABLE IF EXISTS `arbitro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arbitro` (
  `id_arbitro` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_arbitro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arbitro`
--

LOCK TABLES `arbitro` WRITE;
/*!40000 ALTER TABLE `arbitro` DISABLE KEYS */;
/*!40000 ALTER TABLE `arbitro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cambio`
--

DROP TABLE IF EXISTS `cambio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cambio` (
  `id_cambio` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `minuto` tinyint(3) unsigned DEFAULT NULL,
  `id_partido` smallint(5) unsigned DEFAULT NULL,
  `id_jugador_sale` mediumint(8) unsigned DEFAULT NULL,
  `id_jugador_entra` mediumint(8) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_cambio`),
  KEY `cambio_fk` (`id_partido`),
  KEY `cambio_fk_1` (`id_jugador_sale`),
  KEY `cambio_fk_2` (`id_jugador_entra`),
  CONSTRAINT `cambio_fk` FOREIGN KEY (`id_partido`) REFERENCES `partido` (`id_partido`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cambio_fk_1` FOREIGN KEY (`id_jugador_sale`) REFERENCES `jugador` (`id_jugador`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `cambio_fk_2` FOREIGN KEY (`id_jugador_entra`) REFERENCES `jugador` (`id_jugador`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cambio`
--

LOCK TABLES `cambio` WRITE;
/*!40000 ALTER TABLE `cambio` DISABLE KEYS */;
/*!40000 ALTER TABLE `cambio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipo` (
  `id_equipo` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `nombre_completo` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `fundacion` smallint(4) unsigned DEFAULT NULL,
  `presidente` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `escudo` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `entrenador` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_estadio` tinyint(3) unsigned DEFAULT NULL,
  `telefono` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `direccion` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `web` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_equipo`),
  KEY `Equipo_FK` (`id_estadio`),
  CONSTRAINT `Equipo_FK` FOREIGN KEY (`id_estadio`) REFERENCES `estadio` (`id_estadio`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (1,'Athletic','Athletic Club',1898,'','Club_Athletic_Bilbao_logo.png','',1,'+34 (944) 240877','Ibaigane, Alameda Mazarredo, 23 Bilbao 48009','http://www.athletic-club.eus'),(2,'Atlético','Club Atlético de Madrid',1903,'','Atletico_Madrid_logo.png','',2,'+34 (913) 669048','Paseo Virgen del Puerto, 67 Madrid 28005','http://www.clubatleticodemadrid.com'),(3,'Osasuna','CA Osasuna',1920,'','Atletico_Osasuna.png','',3,'+34 (948) 152636','Calle del Sadar, s/n Pamplona 31006','http://www.osasuna.es'),(4,'Espanyol','RCD Espanyol de Barcelona',1900,'','RCD_Espanyol_De_Barcelona.png','',4,'+34 (932) 927700','Avenida Baix Llobregat, 100 Cornellà de Llobregat 08940','http://www.rcdespanyol.com'),(5,'Barcelona','FC Barcelona',1899,'','Fc_barcelona.png','',5,'+34 (902) 189900','Avenida Arístides Maillol s/n Barcelona 08028','http://www.fcbarcelona.com'),(6,'Getafe','Getafe CF',1946,'','Getafe_logo.png','',6,'+34 (916) 959643','Avenida Teresa de Calcuta, s/n Getafe 28903','http://www.getafecf.com'),(7,'Granada','Granada CF',1931,'','Granada_CF.png','',7,'+34 (958) 253300','Calle del Pintor Manuel Maldonado, s/n Granada 18007','http://www.granadacf.es'),(8,'Real Madrid','Real Madrid CF',1902,'','Real_Madrid_Logo.png','',8,'+34 (913) 984300','Avenida Concha Espina, 1 Madrid 28036','http://www.realmadrid.com'),(9,'Levante','Levante UD',1909,'','Levante_ud.png','',9,'+34 (902) 220304','Calle San Vicente de Paúl, 44 Valencia 46019','http://www.levanteud.com'),(10,'Mallorca','RCD Mallorca',1916,'','Rcd_mallorca.png','',10,'+34 (971) 221221','Son Moix, Calle Camí dels Reis, s/n Palma de Mallorca 07011','http://www.rcdmallorca.es'),(11,'Betis','Real Betis Balompié',1907,'','Real_Betis.png','',11,'+34 (902) 191907','Avenida de Heliópolis, s/n Sevilla 41012','http://www.realbetisbalompie.es'),(12,'Real Sociedad','Real Sociedad de Fútbol',1903,'','Real_Sociedad_San_Sebasti%C3%A1n.png','',12,'+34 (943) 462833','Anoeta Pasalekua, 1 San Sebastián 20014','http://www.realsociedad.com'),(13,'Villarreal','Villarreal CF',1923,'','Villarreal_CF_logo.png','',13,'+34 (964) 500250','Camino Miralcamp, s/n Villarreal 12540','http://www.villarrealcf.es'),(14,'Valencia','Valencia CF',1919,'','FC_Valencia.png','',14,'+34 (902) 011919','Plaza del Valencia Club de Fútbol, 2 Valencia 46010','http://www.valenciacf.com'),(15,'Valladolid','Real Valladolid CF',1928,'','Real_Valladolid_Logo.png','',15,'+34 (983) 360342','Avenida del Mundial, 82 Valladolid 47014','http://www.realvalladolid.es'),(16,'Alavés','Deportivo Alavés',1921,'','Deportivo_Alaves_logo.png','',16,'+34 (945) 131018','Mendizorroza, Paseo Cervantes, s/n Vitoria 01007','http://www.alaves.com'),(17,'Eibar','SD Eibar',1940,'','SD_Eibar_logo.png','',17,'+34 (943) 201831','Ipurua Kalea, 2 Eibar 20600','http://www.sdeibar.com'),(18,'Celta','RC Celta de Vigo',1923,'','Celta_Vigo.png','',18,'+34 (986) 110900','Calle del Conde de Gondomar, 1 Vigo 36203','http://www.celtavigo.net'),(19,'Sevilla','Sevilla FC',1905,'','FC_Sevilla.png','',19,'+34 (902) 510011','Calle Sevilla Fútbol Club, s/n Sevilla 41005','http://www.sevillafc.es'),(20,'Leganés','CD Leganés',1928,'','leganes.png','',20,'+34 (916) 888629','Calle Arquitectura, s/n Leganés 28914','http://www.deportivoleganes.com');
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadio`
--

DROP TABLE IF EXISTS `estadio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estadio` (
  `id_estadio` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `capacidad` int(10) unsigned DEFAULT NULL,
  `dimensiones` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `construccion` smallint(5) unsigned DEFAULT NULL,
  `imagen` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_estadio`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadio`
--

LOCK TABLES `estadio` WRITE;
/*!40000 ALTER TABLE `estadio` DISABLE KEYS */;
INSERT INTO `estadio` VALUES (1,'San Mamés',0,'',0,'f020571f7c260516dfab3b9dc5df6348.png'),(2,'Estadio Wanda Metropolitano',0,'',0,'935f92c15c4d3442ef3f6d843beba0e1.png'),(3,'Estadio El Sadar',0,'',0,'d10f766b1612a516ea623116f0c1f9ce.png'),(4,'RCDE Stadium',0,'',0,'306cc02af120e092849ddf38954bcb3e.png'),(5,'Camp Nou',0,'',0,'0623ce55ec3ee4e439ed6d24fe2ba77b.png'),(6,'Coliseum Alfonso Pérez',0,'',0,'b881ba5a6846e7ccdbc3a6a75a2c5e69.png'),(7,'Estadio Nuevo Los Cármenes',0,'',0,'4de53714b029bf44d86c53bd0d6835d3.png'),(8,'Estadio Santiago Bernabéu',0,'',0,'e6695a8184f41deea3ad87dd9c12ab1f.png'),(9,'Estadio Ciudad de Valencia',0,'',0,'24140d1bf09080fd77078189f24d1b3d.png'),(10,'Estadio de Son Moix',0,'',0,'6f67f604b5025b10ef0dc14d700586ae.png'),(11,'Estadio Benito Villamarín',0,'',0,'f211dabccff68741a75d175307d78f59.png'),(12,'Reale Arena',0,'',0,'b20727f80d7b4c9d5ad18acec9b663c5.png'),(13,'Estadio de la Cerámica',0,'',0,'ac940b8f6681d614e337d20d5bf7f0a8.png'),(14,'Estadio de Mestalla',0,'',0,'984682326cdbb55f5034778b7690a795.png'),(15,'Estadio Municipal José Zorrilla',0,'',0,'5dfdde2dab7cf9814c6f008591a3a4b5.png'),(16,'Estadio de Mendizorroza',0,'',0,'6b906780ba96aa6689192fb9afcaac6b.png'),(17,'Estadio Municipal de Ipurúa',0,'',0,'3da691cda91766963efc021a8cf5dd77.png'),(18,'Estadio de Balaídos',0,'',0,'0aef263ef366bd0b81369408adb27cd6.png'),(19,'Estadio Ramón Sánchez Pizjuán',0,'',0,'11217561bbfa63ef6fa59c360394a9fc.png'),(20,'Estadio Municipal de Butarque',0,'',0,'2b7303ff6a9beaf1b4dc88599116df55.png');
/*!40000 ALTER TABLE `estadio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gol`
--

DROP TABLE IF EXISTS `gol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gol` (
  `id_gol` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `minuto` smallint(5) unsigned NOT NULL,
  `penalti` bit(1) DEFAULT b'0',
  `propia_meta` bit(1) DEFAULT b'0',
  `id_jugador` mediumint(8) unsigned DEFAULT NULL,
  `id_partido` smallint(5) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_gol`),
  KEY `gol_fk` (`id_jugador`),
  KEY `gol_fk_1` (`id_partido`),
  CONSTRAINT `gol_fk` FOREIGN KEY (`id_jugador`) REFERENCES `jugador` (`id_jugador`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `gol_fk_1` FOREIGN KEY (`id_partido`) REFERENCES `partido` (`id_partido`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gol`
--

LOCK TABLES `gol` WRITE;
/*!40000 ALTER TABLE `gol` DISABLE KEYS */;
/*!40000 ALTER TABLE `gol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jornada`
--

DROP TABLE IF EXISTS `jornada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jornada` (
  `id_jornada` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  PRIMARY KEY (`id_jornada`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jornada`
--

LOCK TABLES `jornada` WRITE;
/*!40000 ALTER TABLE `jornada` DISABLE KEYS */;
INSERT INTO `jornada` VALUES (1,'2019-08-15','2019-08-18'),(2,NULL,NULL),(3,NULL,NULL),(4,NULL,NULL),(5,NULL,NULL),(6,NULL,NULL),(7,NULL,NULL),(8,NULL,NULL),(9,NULL,NULL),(10,NULL,NULL),(11,NULL,NULL),(12,NULL,NULL),(13,NULL,NULL),(14,NULL,NULL),(15,NULL,NULL),(16,NULL,NULL),(17,NULL,NULL),(18,NULL,NULL),(19,NULL,NULL),(20,NULL,NULL),(21,NULL,NULL),(22,NULL,NULL),(23,NULL,NULL),(24,NULL,NULL),(25,NULL,NULL),(26,NULL,NULL),(27,NULL,NULL),(28,NULL,NULL),(29,NULL,NULL),(30,NULL,NULL),(31,NULL,NULL),(32,NULL,NULL),(33,NULL,NULL),(34,NULL,NULL),(35,NULL,NULL),(36,NULL,NULL),(37,NULL,NULL),(38,NULL,NULL);
/*!40000 ALTER TABLE `jornada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugador`
--

DROP TABLE IF EXISTS `jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugador` (
  `id_jugador` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `dorsal` tinyint(3) unsigned DEFAULT '0',
  `posicion` enum('POR','DEF','MED','DEL') CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `peso` tinyint(3) unsigned DEFAULT '0',
  `altura` tinyint(3) unsigned DEFAULT '0',
  `apodo` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `nombre_comuniazo` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `nacionalidad` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_equipo` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_jugador`),
  KEY `jugador_FK` (`id_equipo`),
  CONSTRAINT `jugador_FK` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id_equipo`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador`
--

LOCK TABLES `jugador` WRITE;
/*!40000 ALTER TABLE `jugador` DISABLE KEYS */;
/*!40000 ALTER TABLE `jugador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partido` (
  `id_partido` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `goles_local` tinyint(3) unsigned DEFAULT NULL,
  `goles_visitante` tinyint(3) unsigned DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `disputado` tinyint(1) DEFAULT '0',
  `id_equipo_local` tinyint(3) unsigned DEFAULT NULL,
  `id_equipo_visitante` tinyint(3) unsigned DEFAULT NULL,
  `id_jornada` tinyint(3) unsigned DEFAULT NULL,
  `id_arbitro` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_partido`),
  KEY `partido_FK` (`id_jornada`),
  KEY `partido_FK_1` (`id_equipo_local`),
  KEY `partido_FK_2` (`id_equipo_visitante`),
  KEY `partido_fk_3` (`id_arbitro`),
  CONSTRAINT `partido_FK` FOREIGN KEY (`id_jornada`) REFERENCES `jornada` (`id_jornada`) ON DELETE SET NULL,
  CONSTRAINT `partido_FK_1` FOREIGN KEY (`id_equipo_local`) REFERENCES `equipo` (`id_equipo`),
  CONSTRAINT `partido_FK_2` FOREIGN KEY (`id_equipo_visitante`) REFERENCES `equipo` (`id_equipo`),
  CONSTRAINT `partido_fk_3` FOREIGN KEY (`id_arbitro`) REFERENCES `arbitro` (`id_arbitro`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=382 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido`
--

LOCK TABLES `partido` WRITE;
/*!40000 ALTER TABLE `partido` DISABLE KEYS */;
INSERT INTO `partido` VALUES (2,1,0,'2019-08-16 21:00:00',1,1,5,1,NULL),(3,NULL,NULL,NULL,0,18,8,1,NULL),(4,NULL,NULL,NULL,0,14,12,1,NULL),(5,NULL,NULL,NULL,0,10,17,1,NULL),(6,NULL,NULL,NULL,0,13,7,1,NULL),(7,NULL,NULL,NULL,0,20,3,1,NULL),(8,NULL,NULL,NULL,0,16,9,1,NULL),(9,NULL,NULL,NULL,0,4,19,1,NULL),(10,NULL,NULL,NULL,0,11,15,1,NULL),(11,NULL,NULL,NULL,0,2,6,1,NULL),(12,NULL,NULL,NULL,0,7,19,2,NULL),(13,NULL,NULL,NULL,0,9,13,2,NULL),(14,NULL,NULL,NULL,0,3,17,2,NULL),(15,NULL,NULL,NULL,0,8,15,2,NULL),(16,NULL,NULL,NULL,0,6,1,2,NULL),(17,NULL,NULL,NULL,0,18,14,2,NULL),(18,NULL,NULL,NULL,0,10,12,2,NULL),(19,NULL,NULL,NULL,0,16,4,2,NULL),(20,NULL,NULL,NULL,0,20,2,2,NULL),(21,NULL,NULL,NULL,0,5,11,2,NULL),(22,NULL,NULL,NULL,0,19,18,3,NULL),(23,NULL,NULL,NULL,0,1,12,3,NULL),(24,NULL,NULL,NULL,0,3,5,3,NULL),(25,NULL,NULL,NULL,0,9,15,3,NULL),(26,NULL,NULL,NULL,0,6,16,3,NULL),(27,NULL,NULL,NULL,0,11,20,3,NULL),(28,NULL,NULL,NULL,0,14,10,3,NULL),(29,NULL,NULL,NULL,0,2,17,3,NULL),(30,NULL,NULL,NULL,0,4,7,3,NULL),(31,NULL,NULL,NULL,0,13,8,3,NULL),(32,NULL,NULL,NULL,0,10,1,4,NULL),(33,NULL,NULL,NULL,0,8,9,4,NULL),(34,NULL,NULL,NULL,0,20,13,4,NULL),(35,NULL,NULL,NULL,0,12,2,4,NULL),(36,NULL,NULL,NULL,0,5,14,4,NULL),(37,NULL,NULL,NULL,0,17,4,4,NULL),(38,NULL,NULL,NULL,0,16,19,4,NULL),(39,NULL,NULL,NULL,0,18,7,4,NULL),(40,NULL,NULL,NULL,0,15,3,4,NULL),(41,NULL,NULL,NULL,0,11,6,4,NULL),(42,NULL,NULL,NULL,0,14,20,5,NULL),(43,NULL,NULL,NULL,0,2,18,5,NULL),(44,NULL,NULL,NULL,0,9,17,5,NULL),(45,NULL,NULL,NULL,0,3,11,5,NULL),(46,NULL,NULL,NULL,0,19,8,5,NULL),(47,NULL,NULL,NULL,0,1,16,5,NULL),(48,NULL,NULL,NULL,0,6,10,5,NULL),(49,NULL,NULL,NULL,0,4,12,5,NULL),(50,NULL,NULL,NULL,0,7,5,5,NULL),(51,NULL,NULL,NULL,0,13,15,5,NULL),(52,NULL,NULL,NULL,0,8,3,6,NULL),(53,NULL,NULL,NULL,0,5,13,6,NULL),(54,NULL,NULL,NULL,0,14,6,6,NULL),(55,NULL,NULL,NULL,0,10,2,6,NULL),(56,NULL,NULL,NULL,0,12,16,6,NULL),(57,NULL,NULL,NULL,0,11,9,6,NULL),(58,NULL,NULL,NULL,0,18,4,6,NULL),(59,NULL,NULL,NULL,0,15,7,6,NULL),(60,NULL,NULL,NULL,0,20,1,6,NULL),(61,NULL,NULL,NULL,0,17,19,6,NULL),(62,NULL,NULL,NULL,0,2,8,7,NULL),(63,NULL,NULL,NULL,0,9,3,7,NULL),(64,NULL,NULL,NULL,0,19,12,7,NULL),(65,NULL,NULL,NULL,0,1,14,7,NULL),(66,NULL,NULL,NULL,0,6,5,7,NULL),(67,NULL,NULL,NULL,0,4,15,7,NULL),(68,NULL,NULL,NULL,0,7,20,7,NULL),(69,NULL,NULL,NULL,0,13,11,7,NULL),(70,NULL,NULL,NULL,0,16,10,7,NULL),(71,NULL,NULL,NULL,0,17,18,7,NULL),(72,NULL,NULL,NULL,0,8,7,8,NULL),(73,NULL,NULL,NULL,0,5,19,8,NULL),(74,NULL,NULL,NULL,0,14,16,8,NULL),(75,NULL,NULL,NULL,0,3,13,8,NULL),(76,NULL,NULL,NULL,0,10,4,8,NULL),(77,NULL,NULL,NULL,0,12,6,8,NULL),(78,NULL,NULL,NULL,0,11,17,8,NULL),(79,NULL,NULL,NULL,0,18,1,8,NULL),(80,NULL,NULL,NULL,0,15,2,8,NULL),(81,NULL,NULL,NULL,0,20,9,8,NULL),(82,NULL,NULL,NULL,0,2,14,9,NULL),(83,NULL,NULL,NULL,0,10,8,9,NULL),(84,NULL,NULL,NULL,0,19,9,9,NULL),(85,NULL,NULL,NULL,0,1,15,9,NULL),(86,NULL,NULL,NULL,0,6,20,9,NULL),(87,NULL,NULL,NULL,0,12,11,9,NULL),(88,NULL,NULL,NULL,0,4,13,9,NULL),(89,NULL,NULL,NULL,0,7,3,9,NULL),(90,NULL,NULL,NULL,0,16,18,9,NULL),(91,NULL,NULL,NULL,0,17,5,9,NULL),(92,NULL,NULL,NULL,0,5,8,10,NULL),(93,NULL,NULL,NULL,0,2,1,10,NULL),(94,NULL,NULL,NULL,0,9,4,10,NULL),(95,NULL,NULL,NULL,0,3,14,10,NULL),(96,NULL,NULL,NULL,0,19,6,10,NULL),(97,NULL,NULL,NULL,0,7,11,10,NULL),(98,NULL,NULL,NULL,0,18,12,10,NULL),(99,NULL,NULL,NULL,0,15,17,10,NULL),(100,NULL,NULL,NULL,0,13,16,10,NULL),(101,NULL,NULL,NULL,0,20,10,10,NULL),(102,NULL,NULL,NULL,0,8,20,11,NULL),(103,NULL,NULL,NULL,0,5,15,11,NULL),(104,NULL,NULL,NULL,0,14,19,11,NULL),(105,NULL,NULL,NULL,0,10,3,11,NULL),(106,NULL,NULL,NULL,0,1,4,11,NULL),(107,NULL,NULL,NULL,0,6,7,11,NULL),(108,NULL,NULL,NULL,0,12,9,11,NULL),(109,NULL,NULL,NULL,0,11,18,11,NULL),(110,NULL,NULL,NULL,0,16,2,11,NULL),(111,NULL,NULL,NULL,0,17,13,11,NULL),(112,NULL,NULL,NULL,0,8,11,12,NULL),(113,NULL,NULL,NULL,0,9,5,12,NULL),(114,NULL,NULL,NULL,0,3,16,12,NULL),(115,NULL,NULL,NULL,0,19,2,12,NULL),(116,NULL,NULL,NULL,0,4,14,12,NULL),(117,NULL,NULL,NULL,0,7,12,12,NULL),(118,NULL,NULL,NULL,0,18,6,12,NULL),(119,NULL,NULL,NULL,0,15,10,12,NULL),(120,NULL,NULL,NULL,0,13,1,12,NULL),(121,NULL,NULL,NULL,0,20,17,12,NULL),(122,NULL,NULL,NULL,0,5,18,13,NULL),(123,NULL,NULL,NULL,0,14,7,13,NULL),(124,NULL,NULL,NULL,0,2,4,13,NULL),(125,NULL,NULL,NULL,0,10,13,13,NULL),(126,NULL,NULL,NULL,0,1,9,13,NULL),(127,NULL,NULL,NULL,0,6,3,13,NULL),(128,NULL,NULL,NULL,0,12,20,13,NULL),(129,NULL,NULL,NULL,0,11,19,13,NULL),(130,NULL,NULL,NULL,0,16,15,13,NULL),(131,NULL,NULL,NULL,0,17,8,13,NULL),(132,NULL,NULL,NULL,0,8,12,14,NULL),(133,NULL,NULL,NULL,0,9,10,14,NULL),(134,NULL,NULL,NULL,0,3,1,14,NULL),(135,NULL,NULL,NULL,0,11,14,14,NULL),(136,NULL,NULL,NULL,0,4,6,14,NULL),(137,NULL,NULL,NULL,0,7,2,14,NULL),(138,NULL,NULL,NULL,0,15,19,14,NULL),(139,NULL,NULL,NULL,0,13,18,14,NULL),(140,NULL,NULL,NULL,0,20,5,14,NULL),(141,NULL,NULL,NULL,0,17,16,14,NULL),(142,NULL,NULL,NULL,0,14,13,15,NULL),(143,NULL,NULL,NULL,0,2,5,15,NULL),(144,NULL,NULL,NULL,0,10,11,15,NULL),(145,NULL,NULL,NULL,0,19,20,15,NULL),(146,NULL,NULL,NULL,0,1,7,15,NULL),(147,NULL,NULL,NULL,0,6,9,15,NULL),(148,NULL,NULL,NULL,0,12,17,15,NULL),(149,NULL,NULL,NULL,0,4,3,15,NULL),(150,NULL,NULL,NULL,0,18,15,15,NULL),(151,NULL,NULL,NULL,0,16,8,15,NULL),(152,NULL,NULL,NULL,0,8,4,16,NULL),(153,NULL,NULL,NULL,0,5,10,16,NULL),(154,NULL,NULL,NULL,0,9,14,16,NULL),(155,NULL,NULL,NULL,0,3,19,16,NULL),(156,NULL,NULL,NULL,0,11,1,16,NULL),(157,NULL,NULL,NULL,0,7,16,16,NULL),(158,NULL,NULL,NULL,0,15,12,16,NULL),(159,NULL,NULL,NULL,0,13,2,16,NULL),(160,NULL,NULL,NULL,0,20,18,16,NULL),(161,NULL,NULL,NULL,0,17,6,16,NULL),(162,NULL,NULL,NULL,0,14,8,17,NULL),(163,NULL,NULL,NULL,0,2,3,17,NULL),(164,NULL,NULL,NULL,0,19,13,17,NULL),(165,NULL,NULL,NULL,0,1,17,17,NULL),(166,NULL,NULL,NULL,0,6,15,17,NULL),(167,NULL,NULL,NULL,0,12,5,17,NULL),(168,NULL,NULL,NULL,0,4,11,17,NULL),(169,NULL,NULL,NULL,0,7,9,17,NULL),(170,NULL,NULL,NULL,0,18,10,17,NULL),(171,NULL,NULL,NULL,0,16,20,17,NULL),(172,NULL,NULL,NULL,0,8,1,18,NULL),(173,NULL,NULL,NULL,0,5,16,18,NULL),(174,NULL,NULL,NULL,0,9,18,18,NULL),(175,NULL,NULL,NULL,0,3,12,18,NULL),(176,NULL,NULL,NULL,0,10,19,18,NULL),(177,NULL,NULL,NULL,0,11,2,18,NULL),(178,NULL,NULL,NULL,0,15,14,18,NULL),(179,NULL,NULL,NULL,0,13,6,18,NULL),(180,NULL,NULL,NULL,0,20,4,18,NULL),(181,NULL,NULL,NULL,0,17,7,18,NULL),(182,NULL,NULL,NULL,0,14,17,19,NULL),(183,NULL,NULL,NULL,0,2,9,19,NULL),(184,NULL,NULL,NULL,0,19,1,19,NULL),(185,NULL,NULL,NULL,0,6,8,19,NULL),(186,NULL,NULL,NULL,0,12,13,19,NULL),(187,NULL,NULL,NULL,0,4,5,19,NULL),(188,NULL,NULL,NULL,0,7,10,19,NULL),(189,NULL,NULL,NULL,0,18,3,19,NULL),(190,NULL,NULL,NULL,0,15,20,19,NULL),(191,NULL,NULL,NULL,0,16,11,19,NULL),(192,NULL,NULL,NULL,0,8,19,20,NULL),(193,NULL,NULL,NULL,0,5,7,20,NULL),(194,NULL,NULL,NULL,0,9,16,20,NULL),(195,NULL,NULL,NULL,0,3,15,20,NULL),(196,NULL,NULL,NULL,0,10,14,20,NULL),(197,NULL,NULL,NULL,0,1,18,20,NULL),(198,NULL,NULL,NULL,0,11,12,20,NULL),(199,NULL,NULL,NULL,0,13,4,20,NULL),(200,NULL,NULL,NULL,0,20,6,20,NULL),(201,NULL,NULL,NULL,0,17,2,20,NULL),(202,NULL,NULL,NULL,0,14,5,21,NULL),(203,NULL,NULL,NULL,0,2,20,21,NULL),(204,NULL,NULL,NULL,0,3,9,21,NULL),(205,NULL,NULL,NULL,0,19,7,21,NULL),(206,NULL,NULL,NULL,0,6,11,21,NULL),(207,NULL,NULL,NULL,0,12,10,21,NULL),(208,NULL,NULL,NULL,0,4,1,21,NULL),(209,NULL,NULL,NULL,0,18,17,21,NULL),(210,NULL,NULL,NULL,0,15,8,21,NULL),(211,NULL,NULL,NULL,0,16,13,21,NULL),(212,NULL,NULL,NULL,0,8,2,22,NULL),(213,NULL,NULL,NULL,0,5,9,22,NULL),(214,NULL,NULL,NULL,0,14,18,22,NULL),(215,NULL,NULL,NULL,0,10,15,22,NULL),(216,NULL,NULL,NULL,0,19,16,22,NULL),(217,NULL,NULL,NULL,0,1,6,22,NULL),(218,NULL,NULL,NULL,0,7,4,22,NULL),(219,NULL,NULL,NULL,0,13,3,22,NULL),(220,NULL,NULL,NULL,0,20,12,22,NULL),(221,NULL,NULL,NULL,0,17,11,22,NULL),(222,NULL,NULL,NULL,0,2,7,23,NULL),(223,NULL,NULL,NULL,0,9,20,23,NULL),(224,NULL,NULL,NULL,0,3,8,23,NULL),(225,NULL,NULL,NULL,0,6,14,23,NULL),(226,NULL,NULL,NULL,0,12,1,23,NULL),(227,NULL,NULL,NULL,0,11,5,23,NULL),(228,NULL,NULL,NULL,0,4,10,23,NULL),(229,NULL,NULL,NULL,0,18,19,23,NULL),(230,NULL,NULL,NULL,0,15,13,23,NULL),(231,NULL,NULL,NULL,0,16,17,23,NULL),(232,NULL,NULL,NULL,0,8,18,24,NULL),(233,NULL,NULL,NULL,0,5,6,24,NULL),(234,NULL,NULL,NULL,0,14,2,24,NULL),(235,NULL,NULL,NULL,0,10,16,24,NULL),(236,NULL,NULL,NULL,0,19,4,24,NULL),(237,NULL,NULL,NULL,0,1,3,24,NULL),(238,NULL,NULL,NULL,0,7,15,24,NULL),(239,NULL,NULL,NULL,0,13,9,24,NULL),(240,NULL,NULL,NULL,0,20,11,24,NULL),(241,NULL,NULL,NULL,0,17,12,24,NULL),(242,NULL,NULL,NULL,0,8,5,26,NULL),(243,NULL,NULL,NULL,0,14,11,26,NULL),(244,NULL,NULL,NULL,0,10,6,26,NULL),(245,NULL,NULL,NULL,0,19,3,26,NULL),(246,NULL,NULL,NULL,0,1,13,26,NULL),(247,NULL,NULL,NULL,0,12,15,26,NULL),(248,NULL,NULL,NULL,0,4,2,26,NULL),(249,NULL,NULL,NULL,0,7,18,26,NULL),(250,NULL,NULL,NULL,0,20,16,26,NULL),(251,NULL,NULL,NULL,0,17,9,26,NULL),(252,NULL,NULL,NULL,0,5,12,27,NULL),(253,NULL,NULL,NULL,0,2,19,27,NULL),(254,NULL,NULL,NULL,0,9,7,27,NULL),(255,NULL,NULL,NULL,0,3,4,27,NULL),(256,NULL,NULL,NULL,0,6,18,27,NULL),(257,NULL,NULL,NULL,0,11,8,27,NULL),(258,NULL,NULL,NULL,0,15,1,27,NULL),(259,NULL,NULL,NULL,0,13,20,27,NULL),(260,NULL,NULL,NULL,0,16,14,27,NULL),(261,NULL,NULL,NULL,0,17,10,27,NULL),(262,NULL,NULL,NULL,0,8,17,28,NULL),(263,NULL,NULL,NULL,0,14,9,28,NULL),(264,NULL,NULL,NULL,0,10,5,28,NULL),(265,NULL,NULL,NULL,0,19,11,28,NULL),(266,NULL,NULL,NULL,0,1,2,28,NULL),(267,NULL,NULL,NULL,0,12,3,28,NULL),(268,NULL,NULL,NULL,0,4,16,28,NULL),(269,NULL,NULL,NULL,0,7,6,28,NULL),(270,NULL,NULL,NULL,0,18,13,28,NULL),(271,NULL,NULL,NULL,0,20,15,28,NULL),(272,NULL,NULL,NULL,0,8,14,29,NULL),(273,NULL,NULL,NULL,0,5,20,29,NULL),(274,NULL,NULL,NULL,0,9,19,29,NULL),(275,NULL,NULL,NULL,0,3,2,29,NULL),(276,NULL,NULL,NULL,0,6,4,29,NULL),(277,NULL,NULL,NULL,0,11,7,29,NULL),(278,NULL,NULL,NULL,0,15,18,29,NULL),(279,NULL,NULL,NULL,0,13,10,29,NULL),(280,NULL,NULL,NULL,0,16,12,29,NULL),(281,NULL,NULL,NULL,0,17,1,29,NULL),(282,NULL,NULL,NULL,0,5,17,25,NULL),(283,NULL,NULL,NULL,0,2,13,25,NULL),(284,NULL,NULL,NULL,0,9,8,25,NULL),(285,NULL,NULL,NULL,0,3,7,25,NULL),(286,NULL,NULL,NULL,0,6,19,25,NULL),(287,NULL,NULL,NULL,0,12,14,25,NULL),(288,NULL,NULL,NULL,0,11,10,25,NULL),(289,NULL,NULL,NULL,0,18,20,25,NULL),(290,NULL,NULL,NULL,0,15,4,25,NULL),(291,NULL,NULL,NULL,0,16,1,25,NULL),(292,NULL,NULL,NULL,0,14,3,30,NULL),(293,NULL,NULL,NULL,0,2,15,30,NULL),(294,NULL,NULL,NULL,0,10,20,30,NULL),(295,NULL,NULL,NULL,0,19,5,30,NULL),(296,NULL,NULL,NULL,0,1,11,30,NULL),(297,NULL,NULL,NULL,0,6,17,30,NULL),(298,NULL,NULL,NULL,0,12,8,30,NULL),(299,NULL,NULL,NULL,0,4,9,30,NULL),(300,NULL,NULL,NULL,0,7,13,30,NULL),(301,NULL,NULL,NULL,0,18,16,30,NULL),(302,NULL,NULL,NULL,0,8,10,31,NULL),(303,NULL,NULL,NULL,0,5,1,31,NULL),(304,NULL,NULL,NULL,0,9,2,31,NULL),(305,NULL,NULL,NULL,0,12,18,31,NULL),(306,NULL,NULL,NULL,0,11,4,31,NULL),(307,NULL,NULL,NULL,0,15,6,31,NULL),(308,NULL,NULL,NULL,0,13,19,31,NULL),(309,NULL,NULL,NULL,0,16,3,31,NULL),(310,NULL,NULL,NULL,0,20,7,31,NULL),(311,NULL,NULL,NULL,0,17,14,31,NULL),(312,NULL,NULL,NULL,0,2,16,32,NULL),(313,NULL,NULL,NULL,0,9,11,32,NULL),(314,NULL,NULL,NULL,0,3,20,32,NULL),(315,NULL,NULL,NULL,0,19,15,32,NULL),(316,NULL,NULL,NULL,0,1,10,32,NULL),(317,NULL,NULL,NULL,0,6,12,32,NULL),(318,NULL,NULL,NULL,0,4,8,32,NULL),(319,NULL,NULL,NULL,0,7,17,32,NULL),(320,NULL,NULL,NULL,0,18,5,32,NULL),(321,NULL,NULL,NULL,0,13,14,32,NULL),(322,NULL,NULL,NULL,0,8,6,33,NULL),(323,NULL,NULL,NULL,0,5,2,33,NULL),(324,NULL,NULL,NULL,0,14,1,33,NULL),(325,NULL,NULL,NULL,0,10,18,33,NULL),(326,NULL,NULL,NULL,0,12,4,33,NULL),(327,NULL,NULL,NULL,0,11,13,33,NULL),(328,NULL,NULL,NULL,0,15,9,33,NULL),(329,NULL,NULL,NULL,0,16,7,33,NULL),(330,NULL,NULL,NULL,0,20,19,33,NULL),(331,NULL,NULL,NULL,0,17,3,33,NULL),(332,NULL,NULL,NULL,0,2,10,34,NULL),(333,NULL,NULL,NULL,0,9,12,34,NULL),(334,NULL,NULL,NULL,0,3,6,34,NULL),(335,NULL,NULL,NULL,0,19,17,34,NULL),(336,NULL,NULL,NULL,0,1,8,34,NULL),(337,NULL,NULL,NULL,0,4,20,34,NULL),(338,NULL,NULL,NULL,0,7,14,34,NULL),(339,NULL,NULL,NULL,0,18,11,34,NULL),(340,NULL,NULL,NULL,0,15,16,34,NULL),(341,NULL,NULL,NULL,0,13,5,34,NULL),(342,NULL,NULL,NULL,0,8,16,35,NULL),(343,NULL,NULL,NULL,0,5,4,35,NULL),(344,NULL,NULL,NULL,0,14,15,35,NULL),(345,NULL,NULL,NULL,0,10,9,35,NULL),(346,NULL,NULL,NULL,0,1,19,35,NULL),(347,NULL,NULL,NULL,0,6,13,35,NULL),(348,NULL,NULL,NULL,0,12,7,35,NULL),(349,NULL,NULL,NULL,0,11,3,35,NULL),(350,NULL,NULL,NULL,0,18,2,35,NULL),(351,NULL,NULL,NULL,0,17,20,35,NULL),(352,NULL,NULL,NULL,0,15,5,36,NULL),(353,NULL,NULL,NULL,0,13,12,36,NULL),(354,NULL,NULL,NULL,0,16,6,36,NULL),(355,NULL,NULL,NULL,0,20,14,36,NULL),(356,NULL,NULL,NULL,0,2,11,36,NULL),(357,NULL,NULL,NULL,0,9,1,36,NULL),(358,NULL,NULL,NULL,0,3,18,36,NULL),(359,NULL,NULL,NULL,0,19,10,36,NULL),(360,NULL,NULL,NULL,0,4,17,36,NULL),(361,NULL,NULL,NULL,0,7,8,36,NULL),(362,NULL,NULL,NULL,0,8,13,37,NULL),(363,NULL,NULL,NULL,0,5,3,37,NULL),(364,NULL,NULL,NULL,0,14,4,37,NULL),(365,NULL,NULL,NULL,0,10,7,37,NULL),(366,NULL,NULL,NULL,0,1,20,37,NULL),(367,NULL,NULL,NULL,0,6,2,37,NULL),(368,NULL,NULL,NULL,0,12,19,37,NULL),(369,NULL,NULL,NULL,0,11,16,37,NULL),(370,NULL,NULL,NULL,0,18,9,37,NULL),(371,NULL,NULL,NULL,0,17,15,37,NULL),(372,NULL,NULL,NULL,0,2,12,38,NULL),(373,NULL,NULL,NULL,0,9,6,38,NULL),(374,NULL,NULL,NULL,0,3,10,38,NULL),(375,NULL,NULL,NULL,0,19,14,38,NULL),(376,NULL,NULL,NULL,0,4,18,38,NULL),(377,NULL,NULL,NULL,0,7,1,38,NULL),(378,NULL,NULL,NULL,0,15,11,38,NULL),(379,NULL,NULL,NULL,0,13,17,38,NULL),(380,NULL,NULL,NULL,0,16,5,38,NULL),(381,NULL,NULL,NULL,0,20,8,38,NULL);
/*!40000 ALTER TABLE `partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarjeta`
--

DROP TABLE IF EXISTS `tarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarjeta` (
  `id_tarjeta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `minuto` tinyint(3) unsigned NOT NULL,
  `tipo` tinyint(1) unsigned DEFAULT NULL,
  `id_jugador` mediumint(8) unsigned DEFAULT NULL,
  `id_partido` smallint(5) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_tarjeta`),
  KEY `tarjeta_fk` (`id_partido`),
  KEY `tarjeta_fk_1` (`id_jugador`),
  CONSTRAINT `tarjeta_fk` FOREIGN KEY (`id_partido`) REFERENCES `partido` (`id_partido`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tarjeta_fk_1` FOREIGN KEY (`id_jugador`) REFERENCES `jugador` (`id_jugador`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarjeta`
--

LOCK TABLES `tarjeta` WRITE;
/*!40000 ALTER TABLE `tarjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'liga1920'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-09 17:16:06
