-- 创建日志数据库
drop database if exists ooki_log;
create database ooki_log default charset utf8 COLLATE utf8_general_ci;
use ooki_log;
drop table if exists login_log_template;
create table login_log_template(
   id int auto_increment primary key
  ,playerId bigint not null
  ,loginTime datetime not null
  ,registerTime datetime not null
  ,platformType int default 1
  ,deviceType varchar(64)
);

drop table if exists player_log_template;
create table player_log_template(
   id int auto_increment primary key
  ,playerId bigint not null
  ,actionTime datetime not null
  ,messageType varchar(64) not null
  ,messageEnum int default 0
  ,content text
  ,amount int default 0
  ,currency int default 0
  ,mold int default 0
  ,islog int default 1
  ,platformType int default 1
  ,deviceType varchar(64)
  ,step int default 0
);
drop table if exists admin_log_template;
create table admin_log_template(
   id int auto_increment primary key
  ,name varchar(32) not null
  ,ip varchar(30) not null
  ,actionTime datetime not null
  ,content varchar(1024)
);
drop table if exists message_log_template;
create table message_log_template(
   id int auto_increment primary key
  ,playerId bigint not null
  ,messageType varchar(64) not null
  ,beginTime datetime not null
  ,endTime datetime not null
  ,useTime bigint default 0
);

-- 创建游戏逻辑服务器
drop database if exists ooki;
create database ooki default charset utf8 COLLATE utf8_general_ci;
use ooki;

drop table if exists player;
create table player(
  playerId bigint primary key,
  deviceType varchar(64) default '',
  playerName varchar(32) default '',
  icon varchar(64) default '',
  gold int default 0,
  diamond int default 0,
  level int default 0,
  actionPoint int default 0,
  rankScore int default 0,
  feed int default 0,
  bindAccount varchar(32) default '',
  platformUid varchar(32) default '',
  platformType int default 1,
  status int default 0,
  createTime datetime not null,
  lastLoginTime datetime,
  chatOn int default 0,
  password varchar(32) default ''
);

drop table if exists tent;
create table tent(
  tentId bigint auto_increment primary key,
  level int default 0,
  capacity int default 0,
  recoverIndex double default 0.0,
  position int default 0,
  status int default 0,
  playerId bigint not null
);

drop table if exists monster;
create table monster(
  monsterId bigint auto_increment primary key,
  name varchar(32) default '',
  type int default 0,
  level int default 0,
  exp int default 0,
  active tinyint(1) default 0,
  hungry int default 0,
  intimacy int default 0,
  status int default 0,
  actionPoint int default 0,
  playerId bigint not null
);

drop table if exists monsterEgg;
create table monsterEgg(
  eggId bigint auto_increment primary key,
  rareLevel int default 0,
  playerId bigint not null
);

drop table if exists monsterFactory;
create table monsterFactory(
  factoryId bigint auto_increment primary key,
  level int default 0,
  capacity int default 0,
  speed double default 0.0,
  position int default 0,
  status int default 0,
  nextMonsterTime datetime,
  playerId bigint not null
);

drop table if exists game_account;
create table game_account(
   id int auto_increment primary key
  ,account varchar(32) not null
  ,platformType int not null
  ,playerId bigint not null
  ,index(account,platformType)
);

drop table if exists gameserver_config;
create table gameserver_config(
   id int auto_increment primary key
  ,keyString varchar(32) unique key
  ,valueString varchar(32) not null
);