-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: gymdb
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Current Database: `gymdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `gymdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `gymdb`;

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise` (
  `exercise_id` int NOT NULL,
  `complexity` varchar(255) DEFAULT NULL,
  `exercise_name` varchar(255) DEFAULT NULL,
  `focus` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`exercise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES (1,'Beginner','Chest Ex 1','Chest','Isolated'),(2,'Beginner','Bicep Ex 1','Bicep','Isolated'),(3,'Beginner','Chest Ex 2','Chest','Compound'),(4,'Advanced','Bicep Ex 1','Bicep','Isolated'),(5,'Beginner','Chest Ex 1','Chest','Isolated');
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym`
--

DROP TABLE IF EXISTS `gym`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gym` (
  `gym_email` varchar(255) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `gym_name` varchar(255) DEFAULT NULL,
  `gym_password` varchar(255) DEFAULT NULL,
  `gym_password_confirmed` varchar(255) DEFAULT NULL,
  `gym_username` varchar(20) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gym_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym`
--

LOCK TABLES `gym` WRITE;
/*!40000 ALTER TABLE `gym` DISABLE KEYS */;
INSERT INTO `gym` VALUES ('germana@g.com',_binary '','Germana','$2a$10$DUnm3fFWkKv.l5rs4EyEY.lxjI8J6NnLH9fNnd8R/qoTEwOodLQgi','$2a$10$zw9Nb/e80v0v0aWogcOltOXLY07EyaghIgeYcyzjkDD9NRRCseWWu','Germana',NULL,NULL,'ROLE_USER'),('luca@email.com',_binary '','Luca','$2a$10$QMvkSOZe2fcvg6BHqQUK2e8DMyGPd3CYNhYN.dSbwkWAyeHDZfMO6','$2a$10$oBWMNkdtF2kE0mnglfr8ceuLYt2O0yGiUrMHOGGk/6wSeE5Wh2Xfm','Luca',NULL,NULL,'ROLE_USER');
/*!40000 ALTER TABLE `gym` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `program` (
  `program_id` int NOT NULL AUTO_INCREMENT,
  `gym_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`program_id`),
  KEY `FK63kd7xsgkbc3antdr6mlqlyp` (`gym_email`),
  CONSTRAINT `FK63kd7xsgkbc3antdr6mlqlyp` FOREIGN KEY (`gym_email`) REFERENCES `gym` (`gym_email`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program`
--

LOCK TABLES `program` WRITE;
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
INSERT INTO `program` VALUES (19,'germana@g.com'),(20,'germana@g.com'),(21,'germana@g.com'),(22,'germana@g.com'),(23,'germana@g.com'),(24,'germana@g.com'),(25,'germana@g.com'),(26,'germana@g.com'),(27,'germana@g.com'),(28,'luca@email.com'),(29,'luca@email.com'),(30,'luca@email.com');
/*!40000 ALTER TABLE `program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_preference`
--

DROP TABLE IF EXISTS `user_preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_preference` (
  `user_preference_id` int NOT NULL AUTO_INCREMENT,
  `days_per_week` int DEFAULT NULL,
  `fitness_level` varchar(255) DEFAULT NULL,
  `focus` varchar(255) DEFAULT NULL,
  `goal` varchar(255) DEFAULT NULL,
  `gym_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_preference_id`),
  KEY `FKm6f8n4dg19exvpulyoyo2qy0` (`gym_email`),
  CONSTRAINT `FKm6f8n4dg19exvpulyoyo2qy0` FOREIGN KEY (`gym_email`) REFERENCES `gym` (`gym_email`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_preference`
--

LOCK TABLES `user_preference` WRITE;
/*!40000 ALTER TABLE `user_preference` DISABLE KEYS */;
INSERT INTO `user_preference` VALUES (19,3,'Beginner','Upper','Gain','germana@g.com'),(20,3,'Beginner','Upper','Gain','germana@g.com'),(21,3,'Beginner','Upper','Gain','germana@g.com'),(22,4,'Beginner','Upper','Gain','germana@g.com'),(23,3,'Beginner','Upper','Gain','germana@g.com'),(24,3,'Beginner','Upper','Gain','germana@g.com'),(25,3,'Beginner','Upper','Gain','germana@g.com'),(26,3,'Beginner','Upper','Gain','germana@g.com'),(27,3,'Beginner','Upper','Gain','germana@g.com'),(28,3,'Beginner','Upper','Gain','luca@email.com'),(29,3,'Beginner','Upper','Gain','luca@email.com'),(30,3,'Beginner','Upper','Gain','luca@email.com');
/*!40000 ALTER TABLE `user_preference` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-15 10:28:18
