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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `exercise` varchar(45) NOT NULL,
  `exercise_area` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `exercise_UNIQUE` (`exercise`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (12,'딥스','가슴'),(13,'푸시업','가슴'),(14,'인클라인 벤치프레스','가슴'),(15,'디클라인 벤치프레스','가슴'),(16,'케이블 크로스오버','가슴'),(17,'바벨로우','등'),(18,'시티드 로우','등'),(19,'원암 덤벨로우','등'),(20,'풀업','등'),(21,'페이스풀','등'),(22,'사이드 레터럴 레이즈','어깨'),(23,'프론트 레이즈','어깨'),(24,'리버스 펙덱 플라이','어깨'),(25,'업라이트 로우','어깨'),(26,'런지','하체'),(27,'레그 익스텐션','하체'),(28,'레그 컬','하체'),(29,'카프 레이즈','하체'),(30,'힙 쓰러스트','하체'),(31,'해머 컬','이두'),(32,'인클라인 덤벨 컬','이두'),(33,'프리처 컬','이두'),(34,'케이블 컬','이두'),(35,'바벨 컬','이두'),(36,'클로즈그립 벤치프레스','삼두'),(37,'케이블 푸시다운','삼두'),(38,'스컬크러셔','삼두'),(39,'오버헤드 익스텐션','삼두'),(40,'킥백','삼두'),(41,'크런치','코어'),(42,'레그레이즈','코어'),(43,'러시안 트위스트','코어'),(44,'데드버그','코어'),(45,'사이드 플랭크','코어'),(46,'버피','전신'),(47,'클린 앤 저크','전신'),(48,'터키시 겟업','전신'),(49,'스내치','전신'),(50,'로프 클라이밍','전신');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
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
