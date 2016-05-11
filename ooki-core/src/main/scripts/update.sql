DELIMITER $$
DROP PROCEDURE IF EXISTS `createPartitionTable` $$
CREATE PROCEDURE `createPartitionTable`()
begin
	declare v int default 0;
	declare tableName VARCHAR(60);
	while v < 12
	do
	
	set tableName = CONCAT('playerHistory_', CAST(v AS CHAR));
	set @sqlCmd = CONCAT('CREATE TABLE ', tableName,
	' (playerId bigint primary key
	,newGameCount int default 0
	,lastNewGameTime datetime
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;');
	set @sqlCmd2 = CONCAT('drop table if exists ', tableName);
	PREPARE stmt FROM @sqlCmd2;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
	PREPARE stmt FROM @sqlCmd;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
	
	set v = v + 1;
	end while;
end$$
DELIMITER ;
call createPartitionTable();