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
-- Table structure for table `personaltraining`
--

DROP TABLE IF EXISTS `personaltraining`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personaltraining` (
  `trainer_id` varchar(45) NOT NULL,
  `diary_id` int NOT NULL,
  `requestDiary` int DEFAULT NULL,
  `requestState` int NOT NULL DEFAULT '1',
  `time` int NOT NULL,
  `requestTime` int DEFAULT NULL,
  PRIMARY KEY (`diary_id`),
  KEY `fk_personaltraining_trainer1_idx` (`trainer_id`),
  KEY `fk_personaltraining_diary1_idx` (`diary_id`),
  CONSTRAINT `fk_personaltraining_diary1` FOREIGN KEY (`diary_id`) REFERENCES `diary` (`id`),
  CONSTRAINT `fk_personaltraining_trainer1` FOREIGN KEY (`trainer_id`) REFERENCES `trainer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personaltraining`
--

LOCK TABLES `personaltraining` WRITE;
/*!40000 ALTER TABLE `personaltraining` DISABLE KEYS */;
INSERT INTO `personaltraining` VALUES ('f1e38be8-9fc1-432c-abe0-abd12e0da340',480,0,1,8,0),('f1e38be8-9fc1-432c-abe0-abd12e0da340',500,0,1,12,0),('f1e38be8-9fc1-432c-abe0-abd12e0da340',501,NULL,1,10,NULL),('f1e38be8-9fc1-432c-abe0-abd12e0da340',502,0,1,12,0),('f1e38be8-9fc1-432c-abe0-abd12e0da340',503,NULL,1,8,NULL),('f1e38be8-9fc1-432c-abe0-abd12e0da340',504,NULL,1,11,NULL),('f1e38be8-9fc1-432c-abe0-abd12e0da340',506,NULL,1,11,NULL),('f1e38be8-9fc1-432c-abe0-abd12e0da340',507,NULL,1,11,NULL),('f1e38be8-9fc1-432c-abe0-abd12e0da340',508,NULL,1,13,NULL),('f1e38be8-9fc1-432c-abe0-abd12e0da340',510,NULL,1,10,NULL),('f1e38be8-9fc1-432c-abe0-abd12e0da340',511,NULL,1,11,NULL),('f1e38be8-9fc1-432c-abe0-abd12e0da340',512,NULL,1,14,NULL),('f1e38be8-9fc1-432c-abe0-abd12e0da340',513,NULL,1,10,NULL),('f1e38be8-9fc1-432c-abe0-abd12e0da340',515,NULL,1,10,NULL);
/*!40000 ALTER TABLE `personaltraining` ENABLE KEYS */;
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
