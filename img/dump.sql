-- MySQL dump 10.13  Distrib 8.0.34, for Linux (x86_64)
--
-- Host: localhost    Database: paymybuddy
-- ------------------------------------------------------
-- Server version	8.0.34-0ubuntu0.22.04.1

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint NOT NULL,
  `balance` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,0),(2,0),(3,0),(4,0),(5,0),(6,0),(7,0),(8,0),(9,0),(10,0),(11,0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_seq`
--

DROP TABLE IF EXISTS `account_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_seq`
--

LOCK TABLES `account_seq` WRITE;
/*!40000 ALTER TABLE `account_seq` DISABLE KEYS */;
INSERT INTO `account_seq` VALUES (101);
/*!40000 ALTER TABLE `account_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_transaction_list`
--

DROP TABLE IF EXISTS `account_transaction_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_transaction_list` (
  `account_id` bigint NOT NULL,
  `transaction_list_id` bigint NOT NULL,
  UNIQUE KEY `UK_8bqy1b29ihq1bblhl86nk4gau` (`transaction_list_id`),
  KEY `FKgmmmebdirbga1e1hhfx3of7di` (`account_id`),
  CONSTRAINT `FKgmmmebdirbga1e1hhfx3of7di` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKl2wb11280x3b8ylc0rf3aroiy` FOREIGN KEY (`transaction_list_id`) REFERENCES `transaction` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_transaction_list`
--

LOCK TABLES `account_transaction_list` WRITE;
/*!40000 ALTER TABLE `account_transaction_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_transaction_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id` bigint NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(60) NOT NULL,
  `username` varchar(255) NOT NULL,
  `person_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_acrq16tm1ioc620qk2nm5gwyg` (`person_id`),
  CONSTRAINT `FKf61mmbwjhkbs7vrjjndw26mwr` FOREIGN KEY (`person_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'ines.martin@example.com','Gabriel','Duval','$2a$10$Vcy1Ej5uypf8JlmL023uSOMxA9bZKBY4nwMb1d25BVpSC6TdVcaZS','Butterfree',1),(2,'enzo.menard@example.com','Noémie','Lemaire','$2a$10$E2NyvTlmdP3HDOiNkwXeKeApwenf4O6DeI7lixYftcfuJ9Fg1M2zK','Pinsir',2),(3,'valentin.guyot@example.com','Axel','Perrot','$2a$10$qGLWPrIsZt3CkL3bPcas6uYDNAehjjoaVylyaoFfVohw0Gv7JvolC','Nidoqueen',3),(4,'enzo.charpentier@example.com','Lou','David','$2a$10$rR1Xkh6ncvSczwwgzxcOeOwFopFcdUSn3Nol3OQaVmbJYfMikstFu','Slowbro',4),(5,'juliette.carre@example.com','Clémence','Roger','$2a$10$MPKHHDDr497Q5POGtZX88.r.clW9PF89O.LiaGPHhbLZEDSRn5y/W','Slowpoke',5),(6,'louna.carpentier@example.com','Nicolas','Perrot','$2a$10$C7iAt8c33BKqdkAdz97VI.1W8sA1l4GV4znNJLWivMOsNh6/mB1Ey','Golem',6),(7,'jade.legrand@example.com','Julie','Gautier','$2a$10$OIjRhecMeqsGycfgMpW3GuoRPaC1HoGKzO3XwmSaULjJFHuymBrV6','Kabutops',7),(8,'alexandre.lemoine@example.com','Victor','Lucas','$2a$10$7JwnwU64tTdDbwqUhkZS7urmjDV3SyJNJaUVjaHNEGpfStmFaedHm','Omastar',8),(9,'clara.roger@example.com','Mathis','Guérin','$2a$10$HdQ86EWRJnicjmUt5XNsdOE3KTHN54kyZJ/1XSfCG7zQ8tG.w8Aku','Machop',9),(10,'gabriel.roche@example.com','Yanis','Arnaud','$2a$10$Ik7ZAMXEFONqvZb0ca8Ux.7LpekORbmMV00.riTOq.bMHJxfl4hU2','Zapdos',10),(11,'test@test.com','test','test','$2a$10$4lYPtU3rnDkkAIWYYEbEG.zGizCOpW.3JstpGnS/hhbNbYIt6Lu8C','test',11);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_friends_list`
--

DROP TABLE IF EXISTS `person_friends_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_friends_list` (
  `user_id` bigint NOT NULL,
  `friends_list_id` bigint NOT NULL,
  KEY `FKe1wxdcr5s8rlcdskcxw5mei8h` (`friends_list_id`),
  KEY `FKf65s744m6mcv3s3449qqcbins` (`user_id`),
  CONSTRAINT `FKe1wxdcr5s8rlcdskcxw5mei8h` FOREIGN KEY (`friends_list_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKf65s744m6mcv3s3449qqcbins` FOREIGN KEY (`user_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_friends_list`
--

LOCK TABLES `person_friends_list` WRITE;
/*!40000 ALTER TABLE `person_friends_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `person_friends_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_seq`
--

DROP TABLE IF EXISTS `person_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_seq`
--

LOCK TABLES `person_seq` WRITE;
/*!40000 ALTER TABLE `person_seq` DISABLE KEYS */;
INSERT INTO `person_seq` VALUES (101);
/*!40000 ALTER TABLE `person_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint NOT NULL,
  `amount` double NOT NULL,
  `description` varchar(255) NOT NULL,
  `debtor_id` bigint DEFAULT NULL,
  `receiver_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKowhw0brqe2hnxqntp3xrfisdw` (`debtor_id`),
  KEY `FK6jx97wnd05cy42uodqnnvbi56` (`receiver_id`),
  CONSTRAINT `FK6jx97wnd05cy42uodqnnvbi56` FOREIGN KEY (`receiver_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKowhw0brqe2hnxqntp3xrfisdw` FOREIGN KEY (`debtor_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_seq`
--

DROP TABLE IF EXISTS `transaction_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_seq`
--

LOCK TABLES `transaction_seq` WRITE;
/*!40000 ALTER TABLE `transaction_seq` DISABLE KEYS */;
INSERT INTO `transaction_seq` VALUES (1);
/*!40000 ALTER TABLE `transaction_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-15 17:30:31
