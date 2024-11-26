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
-- Table structure for table `gym`
--

DROP TABLE IF EXISTS `gym`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gym` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `phonenumber` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym`
--

LOCK TABLES `gym` WRITE;
/*!40000 ALTER TABLE `gym` DISABLE KEYS */;
INSERT INTO `gym` VALUES (11,'피트니스 프렌즈 강남점','서울특별시 강남구 테헤란로 152','02-555-1234'),(12,'스포애니 역삼점','서울특별시 강남구 역삼로 123','02-555-5678'),(13,'커브스 서초점','서울특별시 서초구 서초대로 333','02-587-1234'),(14,'24시 휘트니스 강남역점','서울특별시 강남구 강남대로 432','02-558-9876'),(15,'하이짐 청담점','서울특별시 강남구 청담동 123-45','02-515-7890'),(16,'크로스핏 홍대점','서울특별시 마포구 와우산로 123','02-332-4567'),(17,'골드짐 합정점','서울특별시 마포구 양화로 234','02-334-5678'),(18,'애플짐 상수점','서울특별시 마포구 상수동 123-12','02-337-8901'),(19,'파워하우스 연남점','서울특별시 마포구 연남동 567-89','02-336-2345'),(20,'주짓수 & 피트니스 홍대점','서울특별시 마포구 어울마당로 456','02-335-6789'),(21,'스포짐 판교점','경기도 성남시 분당구 판교역로 234','031-705-1234'),(22,'헬스존 정자점','경기도 성남시 분당구 정자일로 123','031-708-5678'),(23,'24/7 피트니스 분당점','경기도 성남시 분당구 서현로 345','031-702-9012'),(24,'파워짐 수내점','경기도 성남시 분당구 수내로 678','031-704-3456'),(25,'트레이닝뱅크 판교테크노밸리점','경기도 성남시 분당구 대왕판교로 789','031-706-7890'),(26,'비치짐 해운대점','부산광역시 해운대구 해운대해변로 123','051-742-1234'),(27,'파워피트니스 서면점','부산광역시 부산진구 서면로 456','051-802-5678'),(28,'멜킨스짐 광안리점','부산광역시 수영구 광안해변로 789','051-756-9012'),(29,'헬스타임 부산대점','부산광역시 금정구 부산대학로 234','051-581-3456'),(30,'익스트림짐 센텀시티점','부산광역시 해운대구 센텀중앙로 567','051-744-7890');
/*!40000 ALTER TABLE `gym` ENABLE KEYS */;
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
