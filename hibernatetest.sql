-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: hibernatetest
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course` (
  `courseId` int(11) NOT NULL AUTO_INCREMENT,
  `courseName` varchar(255) DEFAULT NULL,
  `regDate` date DEFAULT NULL,
  `courseDesc` varchar(255) DEFAULT NULL,
  `teacherId` int(11) DEFAULT NULL,
  `maxcrew` int(11) DEFAULT NULL,
  `mincrew` int(11) DEFAULT NULL,
  `grpPrefix` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`courseId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (2,'计算机课程一','2019-01-10','《计算机网络》课程分为三个单元：“计算机网络之网尽其用”、“计算机网络之探赜索隐”和“计算机网络之危机四伏”。教授学生计算机网络的知识',2,5,2,''),(3,'计算机网络','2019-01-10','《计算机网络》课程分为三个单元：“计算机网络之网尽其用”、“计算机网络之探赜索隐”和“计算机网络之危机四伏”。教授学生计算机网络的知识',3,10,3,'');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ggroup`
--

DROP TABLE IF EXISTS `ggroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ggroup` (
  `grpId` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` varchar(255) DEFAULT NULL,
  `totalscore` double DEFAULT NULL,
  `courseId` int(11) DEFAULT NULL,
  `leaderId` int(11) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`grpId`),
  KEY `FK1fa9xb1sjqgxv33s140cbvd74` (`courseId`),
  CONSTRAINT `FK1fa9xb1sjqgxv33s140cbvd74` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ggroup`
--

LOCK TABLES `ggroup` WRITE;
/*!40000 ALTER TABLE `ggroup` DISABLE KEYS */;
INSERT INTO `ggroup` VALUES (2,'',0,3,2015212033,'123456789'),(3,'',0,3,2015211290,'123456789123'),(4,'',0,3,2015211306,'123789456');
/*!40000 ALTER TABLE `ggroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_contrib`
--

DROP TABLE IF EXISTS `group_contrib`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `group_contrib` (
  `grpId` int(11) NOT NULL,
  `stuId` int(11) NOT NULL,
  `contribution` double DEFAULT NULL,
  PRIMARY KEY (`grpId`,`stuId`),
  CONSTRAINT `FKr4e1m12t2nnb1okcgdl56mq4w` FOREIGN KEY (`grpId`) REFERENCES `ggroup` (`grpid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_contrib`
--

LOCK TABLES `group_contrib` WRITE;
/*!40000 ALTER TABLE `group_contrib` DISABLE KEYS */;
INSERT INTO `group_contrib` VALUES (2,2015211288,97.5),(2,2015211983,98.5),(2,2015211984,95.5),(2,2015212033,96.5),(3,2015211281,97),(3,2015211287,98),(3,2015211290,99),(4,2015211282,96.5),(4,2015211283,97.5),(4,2015211284,98.5),(4,2015211306,99.5);
/*!40000 ALTER TABLE `group_contrib` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_score`
--

DROP TABLE IF EXISTS `group_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `group_score` (
  `grpId` int(11) NOT NULL,
  `hwname` varchar(255) NOT NULL,
  `score` double DEFAULT NULL,
  PRIMARY KEY (`grpId`,`hwname`),
  CONSTRAINT `FK2oi4ym205tsu5lxuglmwpqbsw` FOREIGN KEY (`grpId`) REFERENCES `ggroup` (`grpid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_score`
--

LOCK TABLES `group_score` WRITE;
/*!40000 ALTER TABLE `group_score` DISABLE KEYS */;
INSERT INTO `group_score` VALUES (2,'Java编程作业1',90),(2,'计算机编程作业',80),(3,'Java编程作业1',88),(3,'计算机编程作业',90),(4,'Java编程作业1',97),(4,'计算机编程作业',99);
/*!40000 ALTER TABLE `group_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_stulist`
--

DROP TABLE IF EXISTS `group_stulist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `group_stulist` (
  `grpId` int(11) NOT NULL,
  `stuId` int(11) DEFAULT NULL,
  KEY `FKiimm7iga3qwcsgps1h3hl4jaj` (`grpId`),
  CONSTRAINT `FKiimm7iga3qwcsgps1h3hl4jaj` FOREIGN KEY (`grpId`) REFERENCES `ggroup` (`grpid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_stulist`
--

LOCK TABLES `group_stulist` WRITE;
/*!40000 ALTER TABLE `group_stulist` DISABLE KEYS */;
INSERT INTO `group_stulist` VALUES (2,2015212033),(2,2015211984),(2,2015211288),(2,2015211983),(3,2015211290),(3,2015211281),(3,2015211287),(4,2015211306),(4,2015211282),(4,2015211283),(4,2015211284);
/*!40000 ALTER TABLE `group_stulist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homework`
--

DROP TABLE IF EXISTS `homework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `homework` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hwName` varchar(255) DEFAULT NULL,
  `courseId` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `assigntime` date DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `percentage` double DEFAULT NULL,
  `idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc3js44mrd4tm4cnwv3ugkkkvr` (`courseId`),
  CONSTRAINT `FKc3js44mrd4tm4cnwv3ugkkkvr` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homework`
--

LOCK TABLES `homework` WRITE;
/*!40000 ALTER TABLE `homework` DISABLE KEYS */;
INSERT INTO `homework` VALUES (2,'计算机编程作业',3,'需要实现一个学生作业管理系统，完成查看作业布置作业提交作业作业评分功能','2019-01-10','2019-01-11',0.3,0),(3,'Java编程作业1',3,'课程大作业。代码使用java语言','2019-01-10','2019-01-15',0.7,1);
/*!40000 ALTER TABLE `homework` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mysubmit_pathlist`
--

DROP TABLE IF EXISTS `mysubmit_pathlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `mysubmit_pathlist` (
  `Id` int(11) NOT NULL,
  `Idx` int(11) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`,`Idx`),
  CONSTRAINT `FKfeyhtr30211xmm4yjaoc9g017` FOREIGN KEY (`Id`) REFERENCES `submission` (`submitid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mysubmit_pathlist`
--

LOCK TABLES `mysubmit_pathlist` WRITE;
/*!40000 ALTER TABLE `mysubmit_pathlist` DISABLE KEYS */;
INSERT INTO `mysubmit_pathlist` VALUES (1,0,'D:\\projects\\SEP\\out\\artifacts\\SEP_war_exploded\\\\hw\\2\\train_code.zip'),(2,0,'D:\\projects\\SEP\\out\\artifacts\\SEP_war_exploded\\\\hw\\2\\train_spatiotemporal.py'),(3,0,'D:\\projects\\SEP\\out\\artifacts\\SEP_war_exploded\\\\hw\\3\\train_spatial.py'),(4,0,'D:\\projects\\SEP\\out\\artifacts\\SEP_war_exploded\\\\hw\\3\\spatiotemporal.py'),(5,0,'D:\\projects\\SEP\\out\\artifacts\\SEP_war_exploded\\\\hw\\4\\temporal.py'),(6,0,'D:\\projects\\SEP\\out\\artifacts\\SEP_war_exploded\\\\hw\\4\\spatiotemporal.py');
/*!40000 ALTER TABLE `mysubmit_pathlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student` (
  `stuId` int(11) NOT NULL AUTO_INCREMENT,
  `stuName` varchar(255) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `classId` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`stuId`)
) ENGINE=InnoDB AUTO_INCREMENT=2015212287 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (2013211410,'陈亚威',0,'2015211309','123456'),(2014210123,'郝绍明',0,'2015211304','123456'),(2014211166,'高勇',0,'2015211302','123456'),(2014211412,'陈亚熙',0,'2015211310','123456'),(2014211526,'冉卓立',0,'2015211314','123456'),(2015211132,'夏荣泽',0,'2015211301','123456'),(2015211135,'徐永杰',0,'2015211301','123456'),(2015211136,'曾开云',0,'2015211301','123456'),(2015211137,'黄勇康',0,'2015211301','123456'),(2015211138,'贾东港',0,'2015211301','123456'),(2015211143,'甘钊宇',0,'2015211301','123456'),(2015211149,'李佳坤',0,'2015211301','123456'),(2015211150,'刘筱昭',0,'2015211301','123456'),(2015211152,'罗冰璇',0,'2015211301','123456'),(2015211153,'莫榕云',0,'2015211301','123456'),(2015211156,'齐茵瑶',0,'2015211301','123456'),(2015211158,'谢非',0,'2015211302','123456'),(2015211159,'王海龙',0,'2015211302','123456'),(2015211164,'刘子奇',0,'2015211302','123456'),(2015211165,'杨子航',0,'2015211302','123456'),(2015211167,'徐谦',0,'2015211302','123456'),(2015211168,'张景涛',0,'2015211302','123456'),(2015211171,'王思阳',0,'2015211302','123456'),(2015211174,'刘威良',0,'2015211302','123456'),(2015211181,'李乐琪',0,'2015211302','123456'),(2015211183,'韩佳琦',0,'2015211302','123456'),(2015211184,'刘荆',0,'2015211302','123456'),(2015211185,'李笑竹',0,'2015211302','123456'),(2015211187,'陈雅文',0,'2015211302','123456'),(2015211188,'艾依努尔地力夏提',0,'2015211302','123456'),(2015211191,'李琦',0,'2015211303','123456'),(2015211198,'李基豪',0,'2015211303','123456'),(2015211203,'张珩',0,'2015211303','123456'),(2015211204,'旷锐锋',0,'2015211303','123456'),(2015211216,'李嘉文',0,'2015211303','123456'),(2015211219,'杨玉琴',0,'2015211303','123456'),(2015211220,'朱家树',0,'2015211304','123456'),(2015211222,'谷嘉航',0,'2015211304','123456'),(2015211226,'程鑫',0,'2015211304','123456'),(2015211227,'唐文韬',0,'2015211304','123456'),(2015211232,'郭子晖',0,'2015211304','123456'),(2015211233,'胡国真',0,'2015211304','123456'),(2015211239,'刘睿博',0,'2015211304','123456'),(2015211242,'钦热',0,'2015211304','123456'),(2015211244,'王心渝',0,'2015211304','123456'),(2015211247,'杜婉莹',0,'2015211304','123456'),(2015211248,'梁萧',0,'2015211304','123456'),(2015211263,'肖选毅',0,'2015211305','123456'),(2015211273,'黄晓宇',0,'2015211305','123456'),(2015211275,'乔俊',0,'2015211305','123456'),(2015211277,'左苏菲',0,'2015211305','123456'),(2015211281,'齐鹏举',0,'2015211306','123456'),(2015211282,'王星淏',0,'2015211306','123456'),(2015211283,'李云龙',0,'2015211306','123456'),(2015211284,'李航',0,'2015211306','123456'),(2015211287,'王宇航',0,'2015211306','aaa111'),(2015211288,'张尚之',0,'2015211306','123456'),(2015211289,'谢宇',0,'2015211306','123456'),(2015211290,'李勇刚',0,'2015211306','aaa111'),(2015211293,'张栩嘉',0,'2015211306','123456'),(2015211294,'陈正宇',0,'2015211306','123456'),(2015211296,'林思得',0,'2015211306','123456'),(2015211298,'周涛',0,'2015211306','123456'),(2015211304,'刘泽君',0,'2015211306','123456'),(2015211306,'蒋一帆',0,'2015211306','aaa111'),(2015211307,'杨雅皓',0,'2015211306','123456'),(2015211309,'欧日青',0,'2015211306','123456'),(2015211310,'李语涵',0,'2015211306','123456'),(2015211312,'刘雨昂',0,'2015211307','123456'),(2015211322,'李卓',0,'2015211307','123456'),(2015211327,'王旭飞',0,'2015211307','123456'),(2015211334,'赵如茵',0,'2015211307','123456'),(2015211339,'韩宜书',0,'2015211307','123456'),(2015211340,'李想',0,'2015211307','123456'),(2015211348,'吕美林',0,'2015211308','123456'),(2015211350,'严由泉',0,'2015211308','123456'),(2015211353,'魏自强',0,'2015211308','123456'),(2015211354,'曾楷',0,'2015211308','123456'),(2015211355,'吴森梵',0,'2015211308','123456'),(2015211358,'谢立煌',0,'2015211308','123456'),(2015211359,'罗今朝',0,'2015211308','123456'),(2015211360,'陈易森',0,'2015211308','123456'),(2015211361,'李林君',0,'2015211308','123456'),(2015211362,'李楠轩',0,'2015211308','123456'),(2015211364,'赵晟萱',0,'2015211308','123456'),(2015211372,'刘鑫淼',0,'2015211309','123456'),(2015211383,'魏宽伟',0,'2015211309','123456'),(2015211384,'罗哲维',0,'2015211309','123456'),(2015211385,'易世安',0,'2015211309','123456'),(2015211387,'金星宇',0,'2015211309','123456'),(2015211391,'李泽坤',0,'2015211309','123456'),(2015211392,'郭德轩',0,'2015211309','123456'),(2015211395,'梁静茹',0,'2015211309','123456'),(2015211398,'杨帆',0,'2015211309','123456'),(2015211404,'崔云港',0,'2015211310','123456'),(2015211410,'吴迪',0,'2015211310','123456'),(2015211427,'曾明慧',0,'2015211310','123456'),(2015211565,'杨智先',0,'2015211315','123456'),(2015211983,'楼赞',0,'2015211306','123456'),(2015211984,'王涵',0,'2015211306','123456'),(2015212033,'穆凯',0,'2015211306','aaa111'),(2015212160,'张琪',0,'2015211309','123456'),(2015212286,'田瑞濛',0,'2015211308','123456');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_groupmap`
--

DROP TABLE IF EXISTS `student_groupmap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student_groupmap` (
  `stuId` int(11) NOT NULL,
  `courseId` int(11) NOT NULL,
  `grpId` int(11) DEFAULT NULL,
  PRIMARY KEY (`stuId`,`courseId`),
  CONSTRAINT `FK8tybgq5q83y8h194trs0je2` FOREIGN KEY (`stuId`) REFERENCES `student` (`stuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_groupmap`
--

LOCK TABLES `student_groupmap` WRITE;
/*!40000 ALTER TABLE `student_groupmap` DISABLE KEYS */;
INSERT INTO `student_groupmap` VALUES (2015211281,3,3),(2015211282,3,4),(2015211283,3,4),(2015211284,3,4),(2015211287,3,3),(2015211288,3,2),(2015211290,3,3),(2015211306,3,4),(2015211983,3,2),(2015211984,3,2),(2015212033,3,2);
/*!40000 ALTER TABLE `student_groupmap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission`
--

DROP TABLE IF EXISTS `submission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `submission` (
  `submitId` int(11) NOT NULL AUTO_INCREMENT,
  `courseId` int(11) DEFAULT NULL,
  `grpID` int(11) DEFAULT NULL,
  `hwname` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `ranked` bit(1) DEFAULT NULL,
  `uploaderId` int(11) DEFAULT NULL,
  PRIMARY KEY (`submitId`),
  KEY `FKqi5hw09pfhn6tu30lv4fy7w16` (`grpID`),
  CONSTRAINT `FKqi5hw09pfhn6tu30lv4fy7w16` FOREIGN KEY (`grpID`) REFERENCES `ggroup` (`grpid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission`
--

LOCK TABLES `submission` WRITE;
/*!40000 ALTER TABLE `submission` DISABLE KEYS */;
INSERT INTO `submission` VALUES (1,3,2,'计算机编程作业','2019-01-10',_binary '\0',2015212033),(2,3,2,'Java编程作业1','2019-01-10',_binary '\0',2015212033),(3,3,3,'计算机编程作业','2019-01-10',_binary '\0',2015211290),(4,3,3,'Java编程作业1','2019-01-10',_binary '\0',2015211290),(5,3,4,'计算机编程作业','2019-01-10',_binary '\0',2015211306),(6,3,4,'Java编程作业1','2019-01-10',_binary '\0',2015211306);
/*!40000 ALTER TABLE `submission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `take`
--

DROP TABLE IF EXISTS `take`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `take` (
  `stuId` int(11) NOT NULL,
  `courseId` int(11) DEFAULT NULL,
  KEY `FK6651wfpe0jp1qae574vpj5ed7` (`stuId`),
  CONSTRAINT `FK6651wfpe0jp1qae574vpj5ed7` FOREIGN KEY (`stuId`) REFERENCES `student` (`stuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `take`
--

LOCK TABLES `take` WRITE;
/*!40000 ALTER TABLE `take` DISABLE KEYS */;
INSERT INTO `take` VALUES (2015211281,3),(2015211282,3),(2015211283,3),(2015211284,3),(2015211287,3),(2015211288,3),(2015211289,3),(2015211290,3),(2015211293,3),(2015211294,3),(2015211296,3),(2015211298,3),(2015211304,3),(2015211306,3),(2015211307,3),(2015211309,3),(2015211310,3),(2015211983,3),(2015211984,3),(2015212033,3);
/*!40000 ALTER TABLE `take` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `teacher` (
  `teacherId` int(11) NOT NULL AUTO_INCREMENT,
  `teacherName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teacherId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (2,'老师甲','aaa111'),(3,'老师乙','aaa111'),(4,'老师丙','123456');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teaches`
--

DROP TABLE IF EXISTS `teaches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `teaches` (
  `teacherId` int(11) NOT NULL,
  `courseId` int(11) DEFAULT NULL,
  KEY `FK6mawfmavltmmsid22u5pu1pem` (`teacherId`),
  CONSTRAINT `FK6mawfmavltmmsid22u5pu1pem` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`teacherid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teaches`
--

LOCK TABLES `teaches` WRITE;
/*!40000 ALTER TABLE `teaches` DISABLE KEYS */;
INSERT INTO `teaches` VALUES (2,2),(3,3);
/*!40000 ALTER TABLE `teaches` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-10 19:16:14
