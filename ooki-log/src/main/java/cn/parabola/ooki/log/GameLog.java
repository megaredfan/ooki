package cn.parabola.ooki.log;

import cn.parabola.ooki.log.mapper.CommonsMapper;


public abstract class GameLog {

	/**
	 * 登入日志
	 */
	public final static int LOGIN = 100;
	public final static int PLAYER = 101;
	public final static int ADMIN = 102;
	public final static int MESSAGE = 103;
	public final static int PAYMENT =104;
	/**
	 * 获取日志的枚举类型
	 * @return
	 */
	public abstract int getGameLogType();

	/**
	 * 获取数据库的表名,必须包含template字符，程序会将template替换成当天的日期
	 * @return
	 */
	public abstract String getTableName();
	
	public abstract void record(CommonsMapper commonsMapper);
}
