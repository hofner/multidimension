DROP TABLE IF EXISTS `multidimension`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multidimension` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NPRODUCTO` int(11) DEFAULT NULL,
  `CONCURSO` int(11) DEFAULT NULL,
  `R1` int(11) DEFAULT NULL,
  `R2` int(11) DEFAULT NULL,
  `R3` int(11) DEFAULT NULL,
  `R4` int(11) DEFAULT NULL,
  `R5` int(11) DEFAULT NULL,
  `R6` int(11) DEFAULT NULL,
  `R7` int(11) DEFAULT NULL,
  `BOLSA` decimal(20,0) DEFAULT NULL,
  `FECHA` date DEFAULT NULL,
  `FECHA_STR` varchar(45) DEFAULT NULL,
  `FECHA_INT` int(7)  DEFAULT NULL,
  `NOMBRE_DIA` varchar(45) DEFAULT NULL,
  `NOMBRE_MES` varchar(45) DEFAULT NULL,
  `NUMERO_DIA_SEMANA` int(1) DEFAULT NULL,
  `NUMERO_DIA` int(2) DEFAULT NULL,
  `NUMERO_MES` int(2) DEFAULT NULL,
  `NUMERO_YEAR` int(4) DEFAULT NULL,
  `R1_IMPAR` varchar(10) DEFAULT NULL,
  `R2_IMPAR` varchar(10) DEFAULT NULL,
  `R3_IMPAR` varchar(10) DEFAULT NULL,
  `R4_IMPAR` varchar(10) DEFAULT NULL,
  `R5_IMPAR` varchar(10) DEFAULT NULL,
  `R6_IMPAR` varchar(10) DEFAULT NULL,
  `R7_IMPAR` varchar(10) DEFAULT NULL,
  `R1_PRIMO` varchar(45) DEFAULT NULL,
  `R2_PRIMO` varchar(45) DEFAULT NULL,
  `R3_PRIMO` varchar(45) DEFAULT NULL,
  `R4_PRIMO` varchar(45) DEFAULT NULL,
  `R5_PRIMO` varchar(45) DEFAULT NULL,
  `R6_PRIMO` varchar(45) DEFAULT NULL,
  `R7_PRIMO` varchar(45) DEFAULT NULL,
  `R1_FIBONACCI` varchar(45) DEFAULT NULL,
  `R2_FIBONACCI` varchar(45) DEFAULT NULL,
  `R3_FIBONACCI` varchar(45) DEFAULT NULL,
  `R4_FIBONACCI` varchar(45) DEFAULT NULL,
  `R5_FIBONACCI` varchar(45) DEFAULT NULL,
  `R6_FIBONACCI` varchar(45) DEFAULT NULL,
  `R7_FIBONACCI` varchar(45) DEFAULT NULL,
  `DIFF` int(11) DEFAULT NULL,
  `DIFF2` int(11) DEFAULT NULL,
  `DIFF3` int(11) DEFAULT NULL,
  `DIFF4` int(11) DEFAULT NULL,
  `DIFF5` int(11) DEFAULT NULL,
  `DIFF6` int(11) DEFAULT NULL,
  `FECHA_CARGA` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DELETE FROM multidimension;