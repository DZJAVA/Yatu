SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO `applicationConfig` VALUES ('appID', '1'), ('appName', '1'), ('appKey', 'q87sdfskdhfksdjf343');

INSERT INTO `topic` VALUES
  ('1', '1', '1', '1', '1', '2016-09-27 10:11:18', null),
  ('2', '3', '3', '3', '3', '2016-09-27 10:11:18', null);


INSERT INTO `works` VALUES
  ('1', '10', '20', '0', 'asd', 'qwewq', '1', '2016-09-21 10:37:59', null),
  ('2', '30', '30', '1', 'qwe', 'zczxc', '1', '2016-09-21 10:38:22', null),
  ('3', '22', '45', '1', 'dfgf', 'ireut', '1', '2016-09-27 10:28:19', null);

INSERT INTO `user` VALUES
  ('1', '1', '1', '1', '1', '1', '1', '2016-09-21 10:37:20', null, '0'),
  ('2', '2', '2', '2', '2', '2', null, '2016-09-21 10:37:32', null, '0'),
  ('3', '3', '3', '3', '3', '3', '3', '2016-09-21 10:37:32', null, '0');

INSERT INTO `topicWorks` VALUES
  ('1', '1', '1', '2016-09-27 10:11:34', null),
  ('1', '3', '1', '2016-09-27 10:28:32', null);

INSERT INTO `userFollow` VALUES
  ('1', '2', '1', '2016-09-27 19:31:27', '2016-09-27 19:51:47'),
  ('2', '1', '1', '2016-09-27 19:31:36', null);

INSERT INTO `worksLiked` VALUES
  ('1', '1', '1', '2016-09-21 10:38:57', '2016-09-27 19:14:39'),
  ('1', '2', '1', '2016-09-21 10:39:21', null),
  ('2', '1', '1', '2016-09-27 19:41:33', null);

SET FOREIGN_KEY_CHECKS = 1;
