-- 创建日志数据库
drop database if exists war_log;
create database war_log default charset utf8 COLLATE utf8_general_ci;
use war_log;
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
drop database if exists war;
create database war default charset utf8 COLLATE utf8_general_ci;
use war;

drop table if exists player;
create table player(
	playerId bigint primary key
	,deviceType varchar(64) default ''
	,playerName varchar(32) default ''
	,icon varchar(64) default ''
	,gold int default 0
	,diamond int default 0
	,level int default 0
	,bindAccount varchar(32) default ''
	,platformUid varchar(32) default ''
	,platformType int default 1
	,status int default 0
	,createTime datetime not null
	,lastLoginTime datetime
	,vip int default 0
	,vipTime datetime
	,deviceToken varchar(64) default ''
	,chatOn int default 0
	,password varchar(32) default ''
	,lastFlushGoldTime datetime
);
drop table if exists player_tech;
create table player_tech(
	playerId bigint primary key 
	,techPoint int default 0
	,lastFlushTime datetime
	,flyProgress int default 0
	,economicProgress int default 0
	,scienceProgress int default 0
	,militaryProgress int default 0
	,flyLevel int default 0
	,economicLevel int default 0
	,militaryLevel int default 0
	,scienceLevel int default 0
);

drop table if exists player_state;
create table player_state(
	playerId bigint primary key
	,shipCost int default 0
	,landCost int default 0
	,shipSpeed int default 0
	,buildCost int default 0
	,addPublicOpinion int default 0
	,addPopulationCount int default 0
	,addPublicOpinionInCapital int default 0
	,addPopulationCountInCapital int default 0
	,addTechPoint int default 0
	,techCost int default 0
	,function int default 0
	,system int default 0
	,targetSystemId int default 0
	,overSystemTime datetime
);

drop table if exists game_action;
create table game_action(
	actionId bigint auto_increment primary key
	,actionType int not null
	,shipCount int default 0
	,sourcePlayerId bigint not null
	,targetPlayerId bigint not null
	,sourceCityId int not null
	,targetCityId int not null
	,actionData blob not null
	,createTime datetime not null
	,overTime datetime not null
	,loadingTime bigint not null
	,actionState int default 0
	,index(sourceCityId,actionState)
);

drop table if exists troop;
create table troop(
	troopId bigint auto_increment primary key
	,playerId bigint not null
	,cityId int not null
	,troopType int not null
	,troopResId int not null
	,count int not null
	,index(playerId,troopType)
	,index(cityId,troopType)
);

drop table if exists playerHistory;
create table playerHistory(
	playerId bigint not null
	,index(playerId)
);


drop table if exists city;
create table city(
	cityId int auto_increment primary key
	,playerId bigint not null
	,cityName varchar(255) not null
	,landId int default 0
	,position tinyint not null
	,food int default 0
	,stone int default 0
	,crystal int default 0
	,metal int default 0
	,wood int default 0
	,level int default 0
	,isCapital tinyint(1) default 0
	,totalPerson double default 0
	,woodWorker int default 0
	,resourceWorker int default 0
	,scientist int default 0
	,lastSetTime datetime not null
	,lastResourceSetTime datetime not null
	,createCityTime datetime not null
	,cityStatus int default 1
	,index(playerId)
	,index(landId,position)
);


drop table if exists building;
create table building(
	id bigint auto_increment primary key
	,playerId bigint not null
	,cityId int not null
	,buildId int not null
	,level int default 1
	,isbuilding tinyint(1) default 0
	,buildTime datetime
	,position tinyint not null
	,count int default 0
	,index(cityId,buildId)
	,index(playerId,buildId)
	,index(cityId,position)
);

drop table if exists landDonation;
create table landDonation(
	id int auto_increment primary key
	,landId int not null
	,playerId bigint not null
	,cityId int not null
	,woodDonationCount int default 0
	,resourceDonationCount int default 0
	,index(landId,playerId,cityId)
);

drop table if exists land;
create table land(
	landId int primary key
	,woodLevel int not null
	,woodExp int not null
	,woodTime datetime
	,resourceLevel int not null
	,resourceExp int not null
	,resourceTime datetime
);

drop table if exists ship;
create table ship(
	shipId bigint auto_increment primary key
	,playerId bigint not null
	,shipType int not null
	,count int default 0
	,index(playerId,shipType)
);

drop table if exists game_account;
create table game_account(
	id int auto_increment primary key
	,account varchar(32) not null
	,platformType int not null
	,playerId bigint not null
	,index(account,platformType)
);

drop table if exists payment_bill;
create table payment_bill(
	orderId varchar(64) primary key
	,playerId bigint not null
	,createTime datetime not null
	,rmb int not null
	,productId varchar(64) not null
	,diamond int default 0
	,index(playerId)
	,index(createTime)
);

drop table if exists payment_order;
create table payment_order(
	orderId varchar(64) primary key
	,playerId bigint not null
	,status int default 0
	,createTime datetime not null
	,index(playerId)
);

drop table if exists gameserver_config;
create table gameserver_config(
	id int auto_increment primary key
	,keyString varchar(32) unique key
	,valueString varchar(32) not null
);

drop table if exists game_notice;
create table game_notice(
	id int auto_increment primary key
	,title varchar(64) not null
	,content varchar(512) not null
	,startTime datetime not null
	,endTime datetime not null
	,createTime datetime not null
	,index(startTime,endTime)
);

drop table if exists packitems;
create table packitems(
  itemId bigint(20) auto_increment primary key
  ,itemType int(11) DEFAULT NULL
  ,itemAmount int(11) DEFAULT NULL
  ,playerId bigint(20) DEFAULT NULL
);


