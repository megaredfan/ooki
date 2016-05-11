package cn.parabola.ooki.log.shardTable;

import cn.parabola.ooki.log.LogManager;
import com.google.code.shardbatis.strategy.ShardStrategy;

import java.util.Calendar;

public class CommonsLogShard implements ShardStrategy{

	@Override
	public String getTargetTableName(String baseTableName, Object params, String mapperId) {
		return baseTableName.replace("template", LogManager.DATEFORMAT.format(Calendar.getInstance().getTime()));
	}
}
