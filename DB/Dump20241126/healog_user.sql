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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `salt` varchar(20) NOT NULL,
  `created_at_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gender` int NOT NULL,
  `birthday` varchar(45) DEFAULT NULL,
  `phonenumber` varchar(45) NOT NULL,
  `trainer_exist` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_email_UNIQUE` (`email`),
  UNIQUE KEY `phonenumber_UNIQUE` (`phonenumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('02a17c2b-0b7e-433a-9b7c-e39b6ae8ecd4','최유진','yujin.choi@gmail.com','243d85d47ea77dc5f0048f4d2507bb5377eb6b74ca78e549724672d29d4cf158','5b92a4c85d83155d11d3','2024-11-26 16:43:11',1,'1998-09-25','010-5555-6666',1),('15a48a7c-bbed-445e-8f9e-b990470a1970','임도현','dohyun.lim@kakao.com','7cceb4383b2a0191a0c6eb797fccc63c18481f26a08d4dc57ba564c03c621590','3c715cd80ab983d91a6d','2024-11-26 16:44:54',0,'1995-10-08','010-8888-9999',1),('2c6c9926-8c6b-4fd4-9187-9118fe46e734','표미선','misun.pyo@naver.com','0a239f12bd5246ec12cb1b96734521018bfb5601f7fd678e13f33a07143edadc','dfbdfefcaa98b8de2f90','2024-11-26 17:12:18',1,'1964-12-03','010-9900-1122',1),('53fa4908-c7f2-4e91-a091-ccea1022e69d','김미래','mirae.kim@gmail.com','26638b64f6ee3ba1dfde1a01ae551cdb1e952674037215ee8d3edac1fb4c9478','34de7fa96c8592d34fe9','2024-11-26 16:41:00',1,'1997-03-15','010-1111-2222',1),('77c980f9-f7ab-431f-931b-2199075dde79','백서연','seoyeon.baek@gmail.com','a876f7d8a346ae35d8e8659af0ed8743d7f093285e8d4c330d54dc0b0d96f88b','89ec9b90be17bfd7c188','2024-11-26 17:10:48',1,'1990-08-12','010-1234-5678',1),('7914fb95-275a-4dd8-811d-c645ea60b1ec','한지민','jimin.han@gmail.com','530d22ec9e529806932cb4dedba30230274f1c1a468e81ab78b87dd083dbd681','df16a10c4e11b93db75c','2024-11-26 17:09:55',1,'1992-12-03','010-9999-0000',1),('7a221bd5-f67f-4a47-851e-e4337d463787','추광호','kwangho.chu@gmail.com','5aa3dbbf570f007230b452268ae90acd92bae6d8356058aad27a5ab60efc6b12','0c761d7c94bf7a1707f9','2024-11-26 17:12:59',0,'1960-04-27','010-0011-2233',1),('8480ed8f-f2b5-4ddb-9fef-725583cbe9fa','송민호','minho.song@naver.com','f25de07964a941c3aeab201b8eadffecfeb008c084d42aa1778732fa0fd46436','2d854ee76087c577f110','2024-11-26 17:10:21',0,'1991-04-27','010-0000-1111',1),('8c3aa93d-6727-4968-9130-59815a5fff6e','박준영','junyoung.park@naver.com','aa1b70572c8d72df072f26f0fc4798e471313e4660bb1ab8ac009131b239d65c','dd11f3a6bf11790ebfe4','2024-11-26 16:41:30',0,'1996-07-22','010-2222-3333',1),('95392136-ce24-4686-adad-602ae2fa0156','조현우','hyeonwoo.jo@daum.net','cd646499e15224bd50a5dddbe1a646d5d5b8ee530a2d942b56d3265be5c1e350','afb9d1cf32ed8ab2cc58','2024-11-26 17:11:17',0,'1993-01-29','010-2345-6789',1),('ac787622-abc3-42bb-8fc2-c8ac124bf156','차진호','jinho.cha@gmail.com','f25f9640a1e3369ec6c88de45f01cfc52bb6305b4574eb14725e39d7483650c3','048f30844a02d1da5aac','2024-11-26 17:11:49',0,'1961-10-08','010-8899-0011',1),('b1886632-d28e-4371-8d73-67e197abce4d','윤서아','seoa.yoon@gmail.com','139f4b41bc39498a744a93b443ff33a5cc1800f72554c9eda02b2a118ead0c7b','3a73de57eb9b65d8e62d','2024-11-26 16:44:23',1,'1996-06-20','010-7777-8888',1),('ed18e396-ca5b-42aa-b662-3526d267d556','이하은','haeun.lee@gmail.com','a4d62b2f067c66810910f66e94a85950e0e04ed5072106e437276ead0b1df354','7a185a039a016e5a1d69','2024-11-26 16:42:04',1,'1999-11-30','010-3333-4444',1),('efdc2060-f955-44e9-9a2c-0bdf386c2cc5','정우진','woojin.jung@daum.net','d3aeeff45ab69819e49e5f40f8d9556f2b6167a254fafe8c9bbd57fdf8822267','82264a03b2426229c145','2024-11-26 16:42:37',0,'1995-05-18','010-4444-5555',1),('f33a28a7-df1c-42c9-abd5-cd8c6054e63e','강민준','minjun.kang@naver.com','ebe30cb973f539dd922ed54522ecdd261209c7d40d5e8027370c9eadf2e4980f','9f792ec556c7e778f148','2024-11-26 16:43:55',0,'1997-02-14','010-6666-7777',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
