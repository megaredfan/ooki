package cn.parabola.ooki.log.shardTable;

import com.google.code.shardbatis.strategy.ShardStrategy;

public class LoginCountShard implements ShardStrategy{

	@Override
	public String getTargetTableName(String baseTableName, Object params, String mapperId) {
		return baseTableName.replace("date", (String)params);
	}

}
