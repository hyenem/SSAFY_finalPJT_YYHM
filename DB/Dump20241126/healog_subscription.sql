-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: healog
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscription` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `trainer_id` varchar(45) NOT NULL,
  `category` int NOT NULL,
  `start_date` varchar(45) NOT NULL,
  `end_date` varchar(45) NOT NULL,
  `valid` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_onlinesubscription_user1_idx` (`user_id`),
  KEY `fk_onlinesubscription_trainer1_idx` (`trainer_id`),
  CONSTRAINT `fk_onlinesubscription_trainer1` FOREIGN KEY (`trainer_id`) REFERENCES `trainer` (`id`),
  CONSTRAINT `fk_onlinesubscription_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
INSERT INTO `subscription` VALUES (57,'53fa4908-c7f2-4e91-a091-ccea1022e69d','0d3b4d13-0c3d-464e-b4eb-bb681142daf1',0,'2024-11-26','2024-12-26',0),(58,'53fa4908-c7f2-4e91-a091-ccea1022e69d','f1e38be8-9fc1-432c-abe0-abd12e0da340',0,'2024-11-26','2024-12-26',1),(59,'8c3aa93d-6727-4968-9130-59815a5fff6e','f1e38be8-9fc1-432c-abe0-abd12e0da340',0,'2024-11-26','2024-12-26',1),(60,'ed18e396-ca5b-42aa-b662-3526d267d556','f1e38be8-9fc1-432c-abe0-abd12e0da340',0,'2024-11-26','2024-12-26',1),(61,'efdc2060-f955-44e9-9a2c-0bdf386c2cc5','1fa7f01b-8707-4f12-a606-c1dae59858a3',0,'2024-11-26','2024-12-26',1),(62,'02a17c2b-0b7e-433a-9b7c-e39b6ae8ecd4','1fa7f01b-8707-4f12-a606-c1dae59858a3',0,'2024-11-26','2024-12-26',1),(63,'f33a28a7-df1c-42c9-abd5-cd8c6054e63e','1fa7f01b-8707-4f12-a606-c1dae59858a3',0,'2024-11-26','2024-12-26',1),(64,'b1886632-d28e-4371-8d73-67e197abce4d','5d9f81fe-d03d-4662-9a59-2f049b3f8a03',0,'2024-11-26','2024-12-26',1),(65,'15a48a7c-bbed-445e-8f9e-b990470a1970','5d9f81fe-d03d-4662-9a59-2f049b3f8a03',0,'2024-11-26','2024-12-26',1),(66,'7914fb95-275a-4dd8-811d-c645ea60b1ec','1323e55f-d35a-4593-bc77-7c09bc27719e',0,'2024-11-26','2024-12-26',1),(67,'8480ed8f-f2b5-4ddb-9fef-725583cbe9fa','1323e55f-d35a-4593-bc77-7c09bc27719e',0,'2024-11-26','2024-12-26',1),(68,'77c980f9-f7ab-431f-931b-2199075dde79','5d9f81fe-d03d-4662-9a59-2f049b3f8a03',0,'2024-11-26','2024-12-26',1),(69,'95392136-ce24-4686-adad-602ae2fa0156','5d9f81fe-d03d-4662-9a59-2f049b3f8a03',0,'2024-11-26','2024-12-26',1),(70,'ac787622-abc3-42bb-8fc2-c8ac124bf156','5d9f81fe-d03d-4662-9a59-2f049b3f8a03',0,'2024-11-26','2024-12-26',1),(71,'2c6c9926-8c6b-4fd4-9187-9118fe46e734','5d9f81fe-d03d-4662-9a59-2f049b3f8a03',0,'2024-11-26','2024-12-26',1),(72,'7a221bd5-f67f-4a47-851e-e4337d463787','5d9f81fe-d03d-4662-9a59-2f049b3f8a03',0,'2024-11-26','2024-12-26',0),(73,'7a221bd5-f67f-4a47-851e-e4337d463787','5d9f81fe-d03d-4662-9a59-2f049b3f8a03',0,'2024-11-26','2024-12-26',1);
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-26 17:37:20
