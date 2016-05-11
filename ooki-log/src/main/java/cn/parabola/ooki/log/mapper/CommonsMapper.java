package cn.parabola.ooki.log.mapper;

import java.util.List;
import java.util.Map;

import cn.parabola.ooki.log.GameLog;
import cn.parabola.ooki.log.model.AdminLog;
import cn.parabola.ooki.log.model.LoginLog;
import cn.parabola.ooki.log.model.MessageLog;
import cn.parabola.ooki.log.model.PlayerLog;
import cn.parabola.ooki.log.model.SqlAdapter;

public interface CommonsMapper {
	/**
	 * 记录用户登陆
	 * 
	 * @param log
	 */
	public void recordLogin(GameLog log);

	public void executeSql(SqlAdapter sql);

	public long getLoginCount(String tableName);

	public List<LoginLog> getDayLoginPlayerId(String date);

	public int executeSqlWithResult(SqlAdapter sql);

	public void recordPlayer(GameLog log);

	public void recordAdmin(GameLog log);

	public List<PlayerLog> getPlayerLog(String date);

	public List<PlayerLog> getPlayerLogByDateAndId(String tableName,
			Long playerId,int start,int num);
	
	public int getPlayerLogByDateAndIdCount(String tableName,
			Long playerId);

	public List<AdminLog> getAdminLogByDate(String date);

	public int getPlayerLogByAmount(String tableName, int currency, int mold);

	public int getPlayerLogByAmountType(String tableName, int currency,
			int mold, int platformType);

	public int getPlayerLogByAmountBoby(String tableName, int currency,
			int mold, long playerId, int messageEnum);

	public int getPlayerCount(Map<String, Object> map);
	
	public int getShareCount(Map<String, Object> map);
	
	public int getShareCountByType(Map<String, Object> map);
	
	public int getShareCountByBoby(Map<String, Object> map);
	
	public int getPlayerPassCount(Map<String, Object> map);
	
	public int getPlayerCountByHour(Map<String, Object> map);
	
	public int getPlayerPassCountByHour(Map<String, Object> map);

	public int getLoginCountForDays(Map<String, Object> map);

	public int getLoginCountByToday(Map<String, Object> map);
	
	public int getUserLoginCountByToday(Map<String, Object> map);
	
	public List<PlayerLog> getUserLoginListByToday(Map<String, Object> map);
	
	public List<PlayerLog> getNewUserListByToday(Map<String, Object> map);
	
	public int getLoginCountByHour(Map<String, Object> map);

	public int getLoginCountForToday(Map<String, Object> map);

	public int getLoginCountByDown(Map<String, Object> map);
	
	public int getLoginCountByDownForHour(Map<String, Object> map);

	public int recordMessage(GameLog log);

	public List<MessageLog> getMessageLogByDate(String date);

	public List<String> getLoginListByPlayer1(Map<String, Object> map);

	public List<String> getLoginListByPlayer2(Map<String, Object> map);

	public int getPayUserCount(Map<String, Object> map);
	
	public int getPayByDiamonds(Map<String, Object> map);
	
	public int getPayByDiamondsByBody(Map<String, Object> map);
	
	public int getPayByAmounts(Map<String, Object> map);
	
	public int getPayByAmountsByBody(Map<String, Object> map);
	
	public int getStepCountByType(Map<String, Object> map);
	
	public int getStepCountByAll(Map<String, Object> map);
	
	public List<String> getPlayerIdByStep(Map<String, Object> map);

}
