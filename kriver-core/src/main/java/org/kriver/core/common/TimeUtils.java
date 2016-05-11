package org.kriver.core.common;

import java.util.Calendar;

/**
 * 时间相关工具
 *
 * @author <a href="mailto:yong.fang@opi-corp.com">fang yong</a>
 * @version 2010-11-26
 *
 */
public class TimeUtils {
	/** 秒（1000ms) */
	public static final int SECOND = 1000;
	/** 分 */
	public static final int MIN = 60 * SECOND;
	/** 时 */
	public static final int HOUR = 60 * MIN;
	/** 天 */
	public static final int DAY = 24 * HOUR;
	
	/**
	 * 判断时间戳是否是今天
	 * @param time
	 * @return
	 */
	public static boolean isToday(long time){
		Calendar start = Calendar.getInstance();
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		end.set(Calendar.MILLISECOND, 999);
		
		return time > start.getTimeInMillis() && time < end.getTimeInMillis();
		
	}
	/**
	 * 判断时间戳是否是今天，小时用来做偏差，例如今天8点算今天就输入8
	 * @param time
	 * @return
	 */
	public static boolean isToday(long time,int hour){
		Calendar start = Calendar.getInstance();
		start.set(Calendar.HOUR_OF_DAY, hour);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		end.set(Calendar.MILLISECOND, 999);
		
		return time > start.getTimeInMillis() && time < end.getTimeInMillis() + hour * TimeUtils.HOUR;
		
	}
	/**
	 * 获取当天零点时间
	 * 
	 * @return
	 */
	public static long getTodayBegin() {
		Calendar _calendar = Calendar.getInstance();
		_calendar.set(Calendar.HOUR_OF_DAY, 0);
		_calendar.set(Calendar.MINUTE, 0);
		_calendar.set(Calendar.SECOND, 0);
		_calendar.set(Calendar.MILLISECOND, 0);
		return _calendar.getTimeInMillis();
	}
	/**
	 * 获取当天结束时间
	 * 
	 * @return
	 */
	public static long getTodayEnd() {
		Calendar _calendar = Calendar.getInstance();
		_calendar.set(Calendar.HOUR_OF_DAY, 23);
		_calendar.set(Calendar.MINUTE, 59);
		_calendar.set(Calendar.SECOND, 59);
		_calendar.set(Calendar.MILLISECOND, 999);
		return _calendar.getTimeInMillis();
	}
	/**
	 * 获取某一天的零点时间
	 * 
	 * @return
	 */
	public static long getSomedayBegin(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}
	/**
	 * 获取某一天的结束时间
	 * 
	 * @return
	 */
	public static long getSomedayEnd(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTimeInMillis();
	}
	/**
	 * 获取今天指定时间的时间戳
	 * @param hour Calendar.HOUR_OF_DAY 24进制
	 * @param minute Calendar.MINUTE
	 * @param second Calendar.SECOND
	 * @return
	 */
	public static long getTodayTime(int hour,int minute,int second){
		Calendar _calendar = Calendar.getInstance();
		_calendar.set(Calendar.HOUR_OF_DAY, hour);
		_calendar.set(Calendar.MINUTE, minute);
		_calendar.set(Calendar.SECOND, second);
		return _calendar.getTimeInMillis();
	}
	/**
	 * 获得年月日小时分钟格式的时间信息， 如1208181810, 到2021年时此接口需防止越界问题。 
	 * 
	 * @return
	 */
	public static int getYYMMDDhhmmOfTime() {
		Calendar _calendar = Calendar.getInstance();
		return getYYMMDDhhmmOfTime(_calendar);
	}
	
	/**
	 * 获得年月日小时分钟格式的时间信息， 如1208181810, 到2021年时此接口需防止越界问题。 
	 * 
	 * @return
	 */
	public static int getYYMMDDhhmmOfTime(long time) {
		Calendar _calendar = Calendar.getInstance();
		_calendar.setTimeInMillis(time);
		return getYYMMDDhhmmOfTime(_calendar);
	}
	
	private static int getYYMMDDhhmmOfTime(Calendar calendar) {
		int _year = calendar.get(Calendar.YEAR);
		int _month = calendar.get(Calendar.MONTH) + 1;
		int _day = calendar.get(Calendar.DAY_OF_MONTH);
		int _hour = calendar.get(Calendar.HOUR_OF_DAY);
		int _minute = calendar.get(Calendar.MINUTE);
		
		return (_year % 100) * 100000000 + _month * 1000000 + _day * 10000 + _hour * 100 + _minute;
	}
	
	/**
	 * 获得年月日小时分钟秒格式的时间信息， 如20120818061030
	 * 
	 * @return
	 */
	public static long getYYYYMMDDhhmmssOfTime() {
		Calendar _calendar = Calendar.getInstance();
		int _year = _calendar.get(Calendar.YEAR);
		int _month = _calendar.get(Calendar.MONTH) + 1;
		int _day = _calendar.get(Calendar.DAY_OF_MONTH);
		int _hour = _calendar.get(Calendar.HOUR_OF_DAY);
		int _minute = _calendar.get(Calendar.MINUTE);
		int _second = _calendar.get(Calendar.SECOND);
		return _year * 10000000000l + _month * 100000000 + _day * 1000000 + _hour * 10000 + _minute * 100 + _second;
	}
	
	/**
	 * 取得时间+随机6位UUID
	 */
	public static String getTimeOrderId(){
		StringBuilder sb = new StringBuilder(String.valueOf(System.currentTimeMillis()));
		sb.append(KeyUtil.getLenKey(6));
		return  sb.toString();
	}
}
