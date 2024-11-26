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
-- Table structure for table `trainer`
--

DROP TABLE IF EXISTS `trainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trainer` (
  `id` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `salt` varchar(20) NOT NULL,
  `created_at_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(45) NOT NULL,
  `phonenumber` varchar(45) NOT NULL,
  `birthday` varchar(45) DEFAULT NULL,
  `bio` text,
  `img` varchar(45) DEFAULT NULL,
  `location` int NOT NULL,
  `user_count` int NOT NULL DEFAULT '0',
  `gender` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `gym_id` (`location`),
  CONSTRAINT `gym_id` FOREIGN KEY (`location`) REFERENCES `gym` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainer`
--

LOCK TABLES `trainer` WRITE;
/*!40000 ALTER TABLE `trainer` DISABLE KEYS */;
INSERT INTO `trainer` VALUES ('0d3b4d13-0c3d-464e-b4eb-bb681142daf1','kevin.jung@healog.com','e48930a7b8193177b26c223130f5ecb71b5cd61271f6e843b3a8850acec8b813','9635b6ec561f1941044b','2024-11-26 16:32:53','정케빈','010-6789-0123','1989-07-09',NULL,NULL,24,0,0),('1323e55f-d35a-4593-bc77-7c09bc27719e','jenny.choi@healog.com','6851b996ffff16bc052304a0c17dc9b0129bb3fbc1d0f0aa2d534a6a9091f7dd','fd2096846099f613a681','2024-11-26 16:24:32','최지은','010-4567-8901','1993-05-18',NULL,NULL,14,2,0),('19989692-0268-45fb-be91-b04e877146bd','david.hong@healog.com','e194d3d400c1224ccacee813f64172f9c6e6c2ba9a3619bbc60dc7fcd2baf96d','fdae7e59a1fbf676f439','2024-11-26 16:25:15','홍대현','010-5678-9012','1991-09-25',NULL,NULL,15,0,0),('1dde24e6-9156-4290-8460-fe191fa9df28','eric.seo@healog.com','8c32b4a7aa96333238e95acc86ae068ef1e7c593e1f1618374ecae51c7436a5b','36e84a802b9e73c0ba29','2024-11-26 16:37:46','서에릭','010-0123-4567','1990-06-30',NULL,NULL,29,0,0),('1f2e7810-14e5-4790-8b53-39a7190218e6','tom.shin@healog.com','f98b3d5463d95ecc5be0436fb38ab5bc55a49d22dc6fb18e20fdaa75f946e484','ec14dea5679b7e507f2b','2024-11-26 16:26:39','신태호','010-7890-1234','1989-06-20',NULL,NULL,17,0,0),('1fa7f01b-8707-4f12-a606-c1dae59858a3','sarah.lee@healog.com','b361c2450f283bb93c41cca984bc1fc6a7e596458e010b69aad9769b69faeb48','a1be116fe64c0474b42e','2024-11-26 16:23:03','이서연','010-2345-6789','1992-07-22',NULL,NULL,12,3,0),('3dbf846a-fa11-4f44-afa8-e88566bdd662','grace.kim@healog.com','26a133685e62bf846d3b88193aeb2b0111703f2239489b4dccf7d406f15cad3e','6fe6809ee0b705cc2db6','2024-11-26 16:38:18','김그레이스','010-1234-5678','1993-08-17',NULL,NULL,30,0,1),('5779a1c5-76af-488e-aca8-e872dc027072','amy.go@healog.com','d1a67e2e1f1641b98f02f79b6071e29b564a08984f2dc6477ea2e6b87c3b2458','3be7723ba12686a9e5ad','2024-11-26 16:32:18','고에이미','010-5678-9012','1991-11-23',NULL,NULL,24,0,1),('5d33c548-c019-4548-b194-09d4855e58c1','bella.nam@healog.com','add2ce8ada4e86d430a2ad9769f73f889922da4656fa23ce46aa64a0ff1de1f8','3aa087625eecabc10edc','2024-11-26 16:35:33','남벨라','010-7890-1234','1994-09-05',NULL,NULL,25,0,1),('5d9f81fe-d03d-4662-9a59-2f049b3f8a03','mike.park@healog.com','9ec44f03b7c0f18851a6566b7b87ae04da0534bdbdf214ea6fa50a56a851a996','c1aac6126a6d0d9c128e','2024-11-26 16:23:49','박민수','010-3456-7890','1988-11-30',NULL,NULL,13,7,0),('7c64fd17-56eb-45e8-88b3-c73302bfdee3','sophia.han@healog.com','07f3acd6e3ca9bf9e0a3cf4c1cb65a54ca18577a7fb06d7e20e3a2ecf2559467','e72715909df76de2a069','2024-11-26 16:28:37','한소피아','010-0123-4567','1992-04-27',NULL,NULL,20,0,1),('7ec5d059-4584-4f73-9cbf-6ffb5499627b','peter.lim@healog.com','9be775c2419463ae6099564f29da2622801c7c8ed842afe30f13e6b6fb25ca06','e75dbca8a1fa80792bb7','2024-11-26 16:27:59','임피터','010-9012-3456','1987-12-03',NULL,NULL,19,0,0),('9128b544-8b15-4c01-81e3-8723e9e8f1e0','chris.ahn@healog.com','2aea3d34d16f7d996c7d77e606186875c7a069c39de608ae8316b3509a3983ff','8a8ae3b1830aa107884c','2024-11-26 16:36:31','안크리스','010-8901-2345','1987-03-21',NULL,NULL,25,0,0),('9ecdf586-67ce-47d4-95a7-1d2ccfc31bcb','lucy.yoon@healog.com','e25eeb38c02e798339af5bbad73e9d730d541028d7a1bc4a90072ca89094a039','da1863d71c9cf5305599','2024-11-26 16:27:22','윤루시','010-8901-2345','1995-10-08',NULL,NULL,18,0,0),('b255ec1c-bcfe-45b4-b723-cb0f5a2c6f0c','emma.kang@healog.com','71e2648af8e437f218d8893f498b479676a3c34ae1d2e2aef9e49f4bb56b3cd1','83be1c61942a9c04625f','2024-11-26 16:26:02','강은미','010-6789-0123','1994-02-14',NULL,NULL,16,0,0),('b79631d1-2648-4a49-b308-71842c8bfb48','alex.song@healog.com','2e25d808cfec7e81c2614d5736e86a21346ec988809341becaa2c86106491684','35c9470c53ce6aee6193','2024-11-26 16:31:33','송알렉스','010-4567-8901','1988-05-16',NULL,NULL,23,0,0),('bc0ca54e-44d2-4a53-a73a-cd24de847f94','lisa.baek@healog.com','c43c3d32bca480913a138c345f5e688c6476775f8a066ad9bfecdd4e60fa00b3','e643c84db1488f943456','2024-11-26 16:30:49','백리사','010-3456-7890','1993-01-29',NULL,NULL,21,0,0),('e41d79e1-4ba0-4786-81a9-4ae86aaeb7bf','jake.jung@healog.com','a4df63c54090008e810643f4d18b19faaec07218344517cb480542f02e0496d6','4c17e9bcbbaf35bbe83e','2024-11-26 16:29:51','정재국','010-2345-6789','1990-08-12',NULL,NULL,21,0,0),('f0ccf49f-615a-44cb-b5a2-958a7cc3a8fd','diana.oh@healog.com','a43ca094852c8f9f433868d1bcc4d6a17cca015ebc6d9bc82aac967611fefe1b','3aa06bd4779563abbcc5','2024-11-26 16:37:15','오다이애나','010-9012-3456','1992-12-15',NULL,NULL,28,0,1),('f1e38be8-9fc1-432c-abe0-abd12e0da340','john.kim@healog.com','db0a70dcbb185fc44b7f84ce437074be5c5f70e35590a8e2a628eeabdfa57b5c','c24d073e3c5a65a9778a','2024-11-26 16:21:50','김준호','010-1234-5678','1990-03-15',NULL,NULL,11,3,0);
/*!40000 ALTER TABLE `trainer` ENABLE KEYS */;
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
