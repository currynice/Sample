DROP TABLE IF EXISTS IM_USER;
DROP TABLE IF EXISTS IM_MSG_CONTENT;
DROP TABLE IF EXISTS IM_MSG_RELATION;
DROP TABLE IF EXISTS IM_MSG_CONTACT;


-- 用户表
CREATE TABLE IM_USER (
  uid INT PRIMARY KEY,
  username VARCHAR(500) NOT NULL,
  password VARCHAR(500) NOT NULL,
  email VARCHAR(250) DEFAULT NULL,
  avatar VARCHAR(500) NOT NULL
);

--消息内容表
CREATE TABLE IM_MSG_CONTENT (
  mid INT AUTO_INCREMENT  PRIMARY KEY,
  content VARCHAR(1000) NOT NULL,
  sender_id INT NOT NULL,
  recipient_id INT NOT NULL,
  --文本消息(表情)/图片消息/音视频/文件
  msg_type INT NOT NULL,
  create_time TIMESTAMP NOT NUll
);

--消息关系索引表(sender删除了(不是撤回),recipient仍然可以查看)
--该表中针对一条消息,有两条相关记录, 以type区分 {0:一条sender的发件箱索引   1:一条recipient的收件箱索引}
--todo，群发，所以使用了other_uid表示接收方id
CREATE TABLE IM_MSG_RELATION (
  owner_uid INT NOT NULL,
  other_uid INT NOT NULL,
--分库分表的话将该字段作为分区键，保证一条消息的两个索引关系还在一张表中。
  mid INT NOT NULL,
  type INT NOT NULL,
  create_time TIMESTAMP NOT NULL,
  --联合主键
  PRIMARY KEY (`owner_uid`,`mid`)
);

CREATE INDEX `idx_owneruid_otheruid_msgid` ON IM_MSG_RELATION(`owner_uid`,`other_uid`,`mid`);


--联系人表,以快速查看最近对话,保存会话中最新一条消息mid即可, 作用:1.根据uid查询[一段时间]内的联系人  2.更新（两个人的都要更新）
CREATE TABLE IM_MSG_CONTACT (
 --联系人列表拥有者id
  owner_uid INT NOT NULL,
  --参与另一方id
  other_uid INT NOT NULL,
  mid INT NOT NULL,
  type INT NOT NULL,
  create_time TIMESTAMP NOT NULL,
  PRIMARY KEY (`owner_uid`,`other_uid`)
);