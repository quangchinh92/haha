CREATE TABLE IF NOT EXISTS `endpoint` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VALUE` varchar(1000) CHARACTER SET utf16 NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `role` (
  `ID` bigint(20) DEFAULT NULL,
  `VALUE` varchar(50) CHARACTER SET utf16 DEFAULT NULL,
  `TYPE` int(2) DEFAULT NULL,
  `APP_ID` bigint(20) DEFAULT NULL,
  `DESCRIPTION` varchar(1024) CHARACTER SET utf16 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `role_endpoint` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(20) NOT NULL DEFAULT 0,
  `ENDPOINT_ID` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `user` (
  `ID` bigint(20) NOT NULL,
  `USERNAME` varchar(50) CHARACTER SET utf16 NOT NULL,
  `PASSWORD` varchar(1024) CHARACTER SET utf16 NOT NULL,
  `IS_SUB_USER` tinyint(1) NOT NULL,
  `NAME` varchar(1024) CHARACTER SET utf16 NOT NULL,
  `PHONE_NUMBER` varchar(15) CHARACTER SET utf16 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `user_role` (
  `ID` bigint(20) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;