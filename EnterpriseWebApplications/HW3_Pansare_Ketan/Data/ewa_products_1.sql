CREATE DATABASE  IF NOT EXISTS `ewa` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ewa`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ewa
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `p_id` varchar(45) NOT NULL,
  `p_name` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `manu` varchar(45) DEFAULT NULL,
  `p_condition` varchar(45) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `p_type` varchar(20) DEFAULT NULL,
  `p_count` int(11) DEFAULT '0',
  `rebate` int(11) DEFAULT '0',
  `p_sold` int(11) DEFAULT '0',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('lp1','Lennovo Slim',899.99,'l1.jpg','Lennovo','New',10,'laptop',12,30,0),('lp11','Lennovo Gamer',1099.99,'l2.jpg','Lennovo','New',10,'laptop',11,0,0),('lp2','Macbook Air',1049.99,'a1.jpg','Apple','New',10,'laptop',5,25,0),('lp22','Macbook Pro',1499.99,'a2.jpg','Apple','New',10,'laptop',6,0,0),('lp3','Sony VAIO',449.99,'s1.jpg','Sony','New',10,'laptop',4,25,0),('p1','Iphone 11 Pro',999.99,'ip11pro.jpg','Apple','New',15,'phone',4,0,0),('p11','Iphone 11',699.99,'ip11.jpg','Apple','New',15,'phone',3,10,1),('p2','OnePlus 7 Pro',699.99,'op7pro.jpg','OnePlus','New',15,'phone',2,0,1),('p22','OnePlus 7T',499.99,'op7t.jpg','OnePlus','New',15,'phone',1,0,0),('p3','Moto G5S',299.99,'moto.jpg','Moto','New',15,'phone',1,0,0),('tv1','Toshiba 51 4K',399.99,'toshiba-51-4k.jpg','Toshiba','New',10,'tv',12,0,0),('tv2','Samsung 40 4K',499.99,'samsung-40-4k.jpg','Samsung','New',10,'tv',12,0,0),('tv3','Samsung Curved 4K',599.99,'samsung-curve.jpg','Samsung','New',20,'tv',14,0,3),('tv4','Sony HDR 4k 60',599.99,'sony-4k-60.jpg','Sony','New',15,'tv',6,0,0),('v1','Alexa Eco',149.99,'a1.jpg','Amazon','New',40,'va',7,0,0),('v11','Alexa Eco Dot',189.99,'a2.jpg','Amazon','New',40,'va',7,0,0),('v2','Google Now',189.99,'g1.jpg','Google','New',40,'va',7,0,0),('v3','Apple Home Pod',289.99,'ap1.jpg','Apple','New',40,'va',6,0,0),('W1','Fitbit 3',399.99,'fb1.jpg','Fitbit','New',10,'wear',8,0,0),('W11','Fitbit 4',499.99,'fb2.jpg','Fitbit','New',10,'wear',8,0,0),('W2','Apple Watch 3',399.99,'a1.jpg','Apple','New',10,'wear',9,0,0),('W22','Apple Watch 4',499.99,'a2.jpg','Apple','New',10,'wear',7,0,0),('W3','MI Band',399.99,'mi.jpg','Mi','New',10,'wear',8,0,0),('wr1','Basic',9.99,'basic.jpg','Basic','New',40,'wireless',7,0,0),('wr2','Premium',19.99,'premium.jpg','Premium','New',40,'wireless',9,0,0),('wr3','Ultimate',39.99,'ultimate.jpg','Ultimate','New',40,'wireless',9,0,0);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-29 18:30:12
