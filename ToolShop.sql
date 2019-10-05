-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: localhost    Database: ToolShop
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Items`
--

DROP TABLE IF EXISTS `Items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Items` (
  `itemId` int(11) NOT NULL,
  `itemName` varchar(45) DEFAULT NULL,
  `ItemQuantity` int(11) DEFAULT NULL,
  `itemPrice` decimal(6,2) DEFAULT NULL,
  `alreadyOrdered` tinyint(4) DEFAULT '0',
  `supId` int(11) DEFAULT NULL,
  PRIMARY KEY (`itemId`),
  KEY `supId_idx` (`supId`),
  CONSTRAINT `supId` FOREIGN KEY (`supId`) REFERENCES `suppliers` (`supId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Items`
--

LOCK TABLES `Items` WRITE;
/*!40000 ALTER TABLE `Items` DISABLE KEYS */;
INSERT INTO `Items` VALUES (1001,'Widgets',10,35.50,0,8004),(1003,'Wedges',15,10.15,0,8020),(1004,'Wing Bats',11,11.25,0,8003),(1005,'Twinkies',75,15.75,0,8012),(1006,'Wiggles',30,12.34,0,8020),(1007,'Bing Bobs',25,2.39,0,8005),(1008,'Wog Wits',300,19.99,0,8004),(1009,'Barney Bits',21,23.59,0,8006),(1010,'Willie Widgits',89,12.99,0,8003),(1011,'Barge Bogs',35,2.99,0,8011),(1012,'Poggy Pogs',99,1.10,0,8002),(1013,'Pardle Pins',400,0.69,0,8013),(1014,'Piddley Wicks',54,5.19,0,8013),(1015,'Iggy Orks',22,49.95,0,8010),(1016,'Crank Cribs',888,0.29,0,8005),(1017,'Thingies',67,45.98,0,8008),(1018,'Orf Dappers',32,19.98,0,8018),(1019,'Piff Knocks',82,4.95,0,8002),(1020,'Knit Piks',66,6.75,0,8015),(1021,'Piddley Pins',370,0.25,0,8020),(1022,'Tic Tocs',87,1.36,0,8014),(1023,'Tin Wits',23,5.67,0,8014),(1024,'Thinga-ma-bobs',40,10.98,0,8012),(1025,'Fling Flobs',254,2.33,0,8009),(1026,'Barn Bins',45,88.67,0,8006),(1027,'Flap Wrappers',89,44.88,0,8009),(1028,'Pong Pangs',2345,0.10,0,8002),(1029,'Oof Tongs',345,8.49,0,8011),(1030,'Nic Nacs',238,2.99,0,8015),(1031,'Tork Tins',376,0.95,0,8012),(1032,'Lilly Larks',56,12.99,0,8007),(1033,'Minnie Morks',800,1.95,0,8007),(1034,'Cork Handles',654,2.66,0,8016),(1035,'Ding Darns',1208,0.15,0,8019),(1036,'Erk Orks',498,3.99,0,8017),(1037,'Foo Figs',234,5.89,0,8018),(1039,'Handy Pandies',321,4.35,0,8017),(1040,'Inny Outies',219,3.45,0,8010);
/*!40000 ALTER TABLE `Items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Suppliers`
--

DROP TABLE IF EXISTS `Suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Suppliers` (
  `supId` int(11) NOT NULL,
  `supName` varchar(45) DEFAULT NULL,
  `supAddress` varchar(45) DEFAULT NULL,
  `supContactName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`supId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Suppliers`
--

LOCK TABLES `Suppliers` WRITE;
/*!40000 ALTER TABLE `Suppliers` DISABLE KEYS */;
INSERT INTO `Suppliers` VALUES (8002,'Pong Works','749 Dufferin Blvd., SE, Calgary','Bart'),(8003,'Wiley Inc.','26 40th St., SE, Calgary','BillyBob'),(8004,'Winork Manufacturing Inc.','156 51st Ave., SE, Calgary','Marty'),(8005,'Grab Bag Inc.','138 42nd Ave., NE, Calgary','Monty'),(8006,'Paddy\'s Manufacturing','311 McCall Way, NE, Calgary','Barney'),(8007,'Smickles Industries','966 Smed Lane, SE, Calgary','Lisa'),(8008,'Thangs Inc.','879 37th Ave., NE, Calgary','Thelma'),(8009,'Flip Works Inc.','472 Ogden Dale Rd., SE, Calgary','Rory'),(8010,'Irkle Industries','754 Sunridge Way, NE, Calgary','Irma'),(8011,'Biff Builders','690 19th St., NE, Calgary','Borjn'),(8012,'Twinkles Inc.','318 29th St.,NE, Calgary','Barkley'),(8013,'Piddles Industries','238 112th Ave., NE, Calgary','Parnell'),(8014,'Tic Tac Manufacturing','598 Palmer Rd., NE, Calgary','Teisha'),(8015,'Knock Knock Inc.','363 42nd Ave., NE, Calgary','Ned'),(8016,'Corky Things Inc.','35 McCall Way, NE, Calgary','Corrine'),(8017,'E & O Industries','883 44th St., SE, Calgary','Stan'),(8018,'Fiddleys Makes Stuff Inc.','350 27th St., NE, Calgary','Fredda'),(8019,'Horks and Stuff Manufacturing','997 42nd Ave., NE, Calgary','Harold'),(8020,'Wings Works','754 48th St., SE, Calgary','Wing');
/*!40000 ALTER TABLE `Suppliers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-11 12:11:38
