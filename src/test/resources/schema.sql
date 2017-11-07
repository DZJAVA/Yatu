SET MODE MySQL;
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` char(32) NOT NULL,
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `token` varchar(200) NOT NULL,
  `nickname` varchar(100) NOT NULL,
  `avatar` varchar(500) NOT NULL,
  `sex` smallint(6) DEFAULT NULL,
  `createDate` datetime NOT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `type` smallint(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `works`
-- ----------------------------
DROP TABLE IF EXISTS `works`;
CREATE TABLE `works` (
  `id` char(32) NOT NULL,
  `width` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `type` smallint(6) NOT NULL,
  `thumbnail` varchar(200) NOT NULL,
  `contentUrl` varchar(200) DEFAULT NULL,
  `userId` char(32) NOT NULL,
  `createDate` datetime NOT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `likedCount` int(11) NOT NULL,
  `normalUrl` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `WORKS_FK_USER` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `applicationConfig`
-- ----------------------------
DROP TABLE IF EXISTS `applicationConfig`;
CREATE TABLE `applicationConfig` (
  `configName` varchar(100) NOT NULL,
  `configValue` varchar(200) NOT NULL,
  PRIMARY KEY (`configName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `topic`
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` char(32) NOT NULL,
  `title` varchar(200) NOT NULL,
  `content` varchar(500) NOT NULL,
  `images` varchar(500) DEFAULT NULL,
  `createUser` char(32) NOT NULL,
  `createDate` datetime NOT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `attentionCount` int(11) NOT NULL,
  `recentUploadDate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `createUser` (`createUser`),
  CONSTRAINT `TOPIC_FK_USER` FOREIGN KEY (`createUser`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `userTopic`
-- ----------------------------
DROP TABLE IF EXISTS `userTopic`;
CREATE TABLE `userTopic` (
  `userId` char(32) NOT NULL,
  `topicId` char(32) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`,`topicId`),
  KEY `topicId` (`topicId`),
  CONSTRAINT `usertopic_fk_topic` FOREIGN KEY (`topicId`) REFERENCES `topic` (`id`) ON DELETE CASCADE,
  CONSTRAINT `usertopic_fk_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `inviteCode`
-- ----------------------------
DROP TABLE IF EXISTS `inviteCode`;
CREATE TABLE `inviteCode` (
  `id` char(32) NOT NULL,
  `topicId` char(32) NOT NULL,
  `code` char(6) NOT NULL,
  `createDate` datetime NOT NULL,
  `expireDate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `topicIdIndex` (`topicId`),
  CONSTRAINT `invitecode_fk_topic` FOREIGN KEY (`topicId`) REFERENCES `topic` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `topicWorks`
-- ----------------------------
DROP TABLE IF EXISTS `topicWorks`;
CREATE TABLE `topicWorks` (
  `topicId` char(32) NOT NULL,
  `worksId` char(32) NOT NULL,
  `userId` char(32) NOT NULL,
  `createDate` datetime NOT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`topicId`,`worksId`),
  KEY `TOPICWORKS_FK_WORKS` (`worksId`),
  KEY `TOPICWORKS_FK_USER` (`userId`),
  CONSTRAINT `TOPICWORKS_FK_TOPIC` FOREIGN KEY (`topicId`) REFERENCES `topic` (`id`),
  CONSTRAINT `TOPICWORKS_FK_USER` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `TOPICWORKS_FK_WORKS` FOREIGN KEY (`worksId`) REFERENCES `works` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `userFollow`
-- ----------------------------
DROP TABLE IF EXISTS `userFollow`;
CREATE TABLE `userFollow` (
  `userId` char(32) NOT NULL,
  `followUid` char(32) NOT NULL,
  `followed` smallint(6) NOT NULL,
  `createDate` datetime NOT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`,`followUid`),
  KEY `followUid` (`followUid`),
  CONSTRAINT `USERFOLLOWED_FK_USER` FOREIGN KEY (`followUid`) REFERENCES `user` (`id`),
  CONSTRAINT `USER_FK_USER` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `worksLiked`
-- ----------------------------
DROP TABLE IF EXISTS `worksLiked`;
CREATE TABLE `worksLiked` (
  `worksId` char(32) NOT NULL,
  `userId` char(32) NOT NULL,
  `liked` smallint(6) NOT NULL,
  `createDate` datetime NOT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`worksId`,`userId`),
  KEY `WORKLIKED_FK_USER` (`userId`),
  CONSTRAINT `WORKLIKED_FK_USER` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `WORKLIKED_FK_WORKS` FOREIGN KEY (`worksId`) REFERENCES `works` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `worksLiked`;
CREATE TABLE `worksLiked` (
  `worksId` char(32) NOT NULL,
  `userId` char(32) NOT NULL,
  `liked` smallint(6) NOT NULL,
  `createDate` datetime NOT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`worksId`,`userId`),
  KEY `WORKLIKED_FK_USER` (`userId`),
  CONSTRAINT `WORKLIKED_FK_USER` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `WORKLIKED_FK_WORKS` FOREIGN KEY (`worksId`) REFERENCES `works` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
