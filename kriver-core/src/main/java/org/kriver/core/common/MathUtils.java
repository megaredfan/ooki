package org.kriver.core.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author qianwp
 * 
 */
public class MathUtils {

	// public static final float EPSILON = 0.00001f;//10-6对于double转float容易误差
	// 宠物点数校验时。 modified by sxf 090121
	public static final float EPSILON = 0.00004f;// 再把误差调得大一点,现在这样,在150级时百万次检查大概会出现8次超出误差值

	/**
	 * 返回>=low, <=hi的整数随机数，均匀分布
	 * 
	 * @param low
	 * @param hi
	 * @return
	 */
	public static int random(int low, int hi) {
		return (int) (low + (hi - low + 0.9) * Math.random());
	}

	/**
	 * 返回>=low, <hi的浮点随机数，均匀分布
	 * 
	 * @param low
	 * @param hi
	 * @return
	 */
	public static float random(float low, float hi) {
		float result = (float) (low + (hi - low) * Math.random());
		if (result >= hi || result < low) {
			result = low;
		}
		return result;
	}

	/**
	 * 返回是否满足概率值。
	 * 
	 * @param shakeNum
	 *            float 概率值 0.0---1.0
	 * @return 比如某操作有２０％的概率，shakeNum=0.2 如果返回true表明概率满足。
	 */
	public static boolean shake(float shakeNum) {
		if (shakeNum >= 1) {
			return true;
		}
		if (shakeNum <= 0) {
			return false;
		}

		double a = Math.random();
		return a < shakeNum;
	}

	/**
	 * 从概率数组中挑选一个概率
	 * 低位索引的优先级高，如果概率累积大于1，后面的索引概率会被忽略
	 * @param rateAry
	 * @return 返回被选中的概率数组索引,-1,没有符合概率的;0~rateAry.length-1,符合概率的数组索引
	 */
	public static int luckyDraw(final float[] rateAry) {
		if (rateAry == null) {
			return -1;
		}
		final int[] balls = new int[rateAry.length * 2];
		int pt = 0;
		int _ballsLength = 0;
		for (int i = 0, j = 0; i < rateAry.length; i += 1, j += 2) {
			final int mulRate = (int) (rateAry[i] * 100) - 1;
			final int _s = j;
			final int _e = j + 1;
			if (mulRate < 0) {
				balls[_s] = -1;
				balls[_e] = -1;
			} else {
				balls[_s] = pt;
				int _end = pt + mulRate;
				if (_end > 99) {
					_end = 99;
				}
				balls[_e] = _end;
				pt = _end + 1;
			}
			_ballsLength++;
			if (pt > 99) {
				break;
			}
		}
		final int _rnd = random(0, 99);
		final int _ballsLengthReal = _ballsLength * 2;
		for (int i = 0; i < _ballsLengthReal; i += 2) {
			if (balls[i] <= _rnd && balls[i + 1] >= _rnd) {
				return i / 2;
			}
		}
		return -1;
	}

	/**
	 * 轮盘赌 建议仅在不确定选择库的时候使用此函数。如果已经知道要从什么里面选，建议事先加好轮盘赌概率
	 * 
	 * @param rateAry
	 *            概率数组
	 * @return 选中的下标
	 */
	public static int rolette(final int[] rateAry) {
		if (rateAry == null) {
			return -1;
		}

		if (rateAry.length == 0) {
			return -1;
		}

		int[] itemListPer = new int[rateAry.length];
		itemListPer[0] = rateAry[0];
		for (int i = 1; i < rateAry.length; i++) {
			itemListPer[i] = itemListPer[i - 1] + rateAry[i];
		}
		int maxper = itemListPer[itemListPer.length - 1];
		int per = MathUtils.random(0, maxper - 1);
		if ((per >= 0) && (per < itemListPer[0])) {
			return 0;
		} else {
			for (int j = 1; j < itemListPer.length; j++) {
				if ((per >= itemListPer[j - 1]) && (per < itemListPer[j])) {
					return j;
				}
			}
			return -1;
		}
	}

	public static int parseInt(Object input, int defaultValue) {
		if (input == null)
			return defaultValue;
		try {
			return Integer.parseInt(input.toString());
		} catch (Exception e) {
		}
		return defaultValue;
	}

	public static int compareFloat(float f1, float f2) {
		float delta = f1 - f2;
		if (Math.abs(delta) > EPSILON) {
			if (delta > 0) {
				return 1; // f1> f2
			} else if (delta < 0) {
				return -1;// f1<f2
			}
		}
		return 0;// f1==f2
	}

	public static int compareToByDay(Calendar dayone, Calendar daytwo) {
		if (dayone.get(Calendar.YEAR) > daytwo.get(Calendar.YEAR)) {
			return 1;
		} else if (dayone.get(Calendar.YEAR) < daytwo.get(Calendar.YEAR)) {
			return -1;
		} else {
			if (dayone.get(Calendar.MONTH) > daytwo.get(Calendar.MONTH)) {
				return 1;
			} else if (dayone.get(Calendar.MONTH) < daytwo.get(Calendar.MONTH)) {
				return -1;
			} else {
				if (dayone.get(Calendar.DAY_OF_MONTH) > daytwo
						.get(Calendar.DAY_OF_MONTH)) {
					return 1;
				} else if (dayone.get(Calendar.DAY_OF_MONTH) < daytwo
						.get(Calendar.DAY_OF_MONTH)) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	/**
	 * 计算两个日期间相差的天数(按24小时算)
	 * 
	 * @param enddate
	 * @param begindate
	 * @return
	 */
	public static int getIntervalDays(Date enddate, Date begindate) {
		long millisecond = enddate.getTime() - begindate.getTime();
		int day = (int) (millisecond / 24l / 60l / 60l / 1000l);
		return day;
	}

	/**
	 * 计算两个日期间相差的天数(按24小时算)
	 * 
	 * @param enddate
	 * @param begindate
	 * @return
	 */
	public static int getIntervalDays(long enddate, long begindate) {
		long millisecond = enddate - begindate;
		int day = (int) (millisecond / 24l / 60l / 60l / 1000l);
		return day;
	}

	/**
	 * 计算两个日期间相差的分钟数
	 * 
	 * @param enddate
	 * @param begindate
	 * @return
	 */
	public static int getIntervalMinutes(Date enddate, Date begindate) {
		long millisecond = enddate.getTime() - begindate.getTime();
		int minute = (int) (millisecond / 60l / 1000l);
		return minute;
	}

	/**
	 * 限置为 >=min <=max的值
	 * 
	 * @param original
	 * @param min
	 * @param max
	 * @return
	 */
	public static int setBetween(int original, int min, int max) {
		if (original > max) {
			original = max;
		}
		if (original < min) {
			original = min;
		}
		return original;
	}

	/**
	 * @param ary1
	 * @param ary2
	 * @return ary1 >= ary2 true else false
	 */
	public static boolean compareArrays(int[] ary1, int[] ary2) {
		if (ary1 != null && ary2 != null) {
			if (ary1.length == ary2.length) {
				for (int i = 0; i < ary1.length; i++) {
					if (ary1[i] < ary2[i]) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public static int float2Int(float f) {
		return (int) (f + 0.5f);
	}

	/**
	 * 获取两数相除的结果,精确到小数
	 * 
	 * @param num
	 * @param deno
	 * @return
	 */
	public static float doDiv(int numerator, int denominator) {
		if (denominator != 0) {
			return numerator / (denominator + 0.0f);
		}
		return 0f;
	}

	public static float doDiv(float numerator, float denominator) {
		if (denominator != 0) {
			return numerator / (denominator);
		}
		return 0f;
	}

	/**
	 * 两个正整数相加
	 * 
	 * @param n1
	 *            第一个参数
	 * @param n2
	 *            第二个参数
	 * @return 相加后的结果
	 * @exception IllegalArgumentException
	 *                ,如果n1或者n2有一个负数,则会抛出此异常;如果n1与n2相加后的结果是负数,即溢出了,也会抛出此异常
	 */
	public static int addPlusNumber(final int n1, final int n2) {
		if (n1 < 0 || n2 < 0) {
			throw new IllegalArgumentException(
					"Both n1 and n2 must be plus,but n1=" + n1 + " and n2 ="
							+ n2);
		}
		final int _sum = n1 + n2;
		if (_sum < 0) {
			throw new IllegalArgumentException(
					"Add n1 and n2 must be plus,but n1+n2=" + _sum);
		}
		return _sum;
	}

	/**
	 * 获得不重复的随机数，取值范围(>=min, <=max)，个数size
	 * 
	 * @author sd 2009-11-9
	 * @param min
	 * @param max
	 * @param size
	 * @return
	 */
	public static List<Integer> getRandomIntWithoutRepeat(int min, int max,
			int size) {
		if (min > max) {
			throw new IllegalArgumentException(
					"#GS.MathUtils.getRandomIntWithoutRepeat"
							+ String.format("min(%s) >= max(%s)", min, max));
		}
		int _arraySize = max - min + 1;
		if (_arraySize < size) {
			throw new IllegalArgumentException(
					"#GS.MathUtils.getRandomIntWithoutRepeat"
							+ String.format("max(%s) - min(%s) >= size(%s)",
									min, max, size));
		}
		List<Integer> _result = new ArrayList<Integer>(size);
		int[] _intArray = new int[_arraySize];
		for (int i = 0; i < _arraySize; i++) {
			_intArray[i] = i + min;
		}
		for (int i = 0; i < size; i++) {
			int _index = random(min, max - i) - min;
			int _temp = _intArray[_index];
			_intArray[_index] = _intArray[_arraySize - 1 - i];
			_intArray[_arraySize - 1 - i] = _temp;
			_result.add(_intArray[_arraySize - 1 - i]);
		}
		return _result;
	}

	/**
	 * 取多个整数当中的最小值
	 * 
	 * @param args
	 * @return
	 */
	public static int min(int... args) {
		if (args.length <= 0) {
			throw new IllegalArgumentException(
					"#GS.MathUtils.min argument size should be positive");
		}
		int _min = Integer.MAX_VALUE;
		for (int _arg : args) {
			if (_arg < _min) {
				_min = _arg;
			}
		}
		return _min;
	}

	/**
	 * 取多个整数当中的最大值
	 * 
	 * @param args
	 * @return
	 */
	public static int max(int... args) {
		if (args.length <= 0) {
			throw new IllegalArgumentException(
					"#GS.MathUtils.max argument size should be positive");
		}
		int _max = Integer.MIN_VALUE;
		for (int _arg : args) {
			if (_arg > _max) {
				_max = _arg;
			}
		}
		return _max;
	}

	/**
	 * 转化成16进制并补全零
	 * 
	 * @param value
	 * @return
	 */
	public static String toHexString(long value) {
		String _tmp = Long.toHexString(value);
		int _size0 = 16 - _tmp.length();
		StringBuffer _sb = new StringBuffer();
		for (int i = 0; i < _size0; i++) {
			_sb.append('0');
		}
		return _sb.toString() + _tmp;
	}

	/**
	 * 取符号
	 * 
	 * @param value
	 */
	public static int sign(int value) {
		return value > 0 ? 1 : value < 0 ? -1 : 0;
	}

	/**
	 * 取符号
	 * 
	 * @param value
	 */
	public static int sign(long value) {
		return value > 0 ? 1 : value < 0 ? -1 : 0;
	}

	/**
	 * 计算以 base 为底的 value 值的对数
	 * 
	 * @param value
	 * @param base
	 * @return
	 */
	public static double log(double value, double base) {
		return Math.log(value) / Math.log(base);
	}

	/**
	 * 求不大于num的最大2的次幂
	 * 
	 * @param num
	 * @return
	 */
	public static int get2pow(int num) {
		int _result = num & num - 1;
		return _result == 0 ? num : get2pow(_result);
	}

	/**
	 * 取得一个正整数的位数
	 * 
	 * @param num
	 * @return
	 */
	public static int getBit(int num) {
		if (num < 0) {
			return 0;
		}
		int count = 0;
		while (num != 0) {
			num = num >> 1;
			count++;
		}
		return count;
	}

	/**
	 * 取得一个正整数为1的位数
	 * 
	 * @param num
	 * @return
	 */
	public static int getMaskBit(int num) {
		if (num < 0) {
			return 0;
		}
		int count = 0;
		while (num != 0) {
			if ((num & 1) == 1)
				count++;
			num = num >> 1;
		}
		return count;
	}

	/**
	 * 功能等同于Math.ceil
	 * 
	 * @param src
	 * @param dep
	 * @return
	 */
	public static int ceil(int src, int dep) {
		if (src < 0) {
			return -1;
		}
		if (dep <= 0) {
			return -1;
		}
		int i = src / dep;
		int j = src % dep;
		if (j == 0) {
			return i;
		}
		return i + 1;
	}

	/**
	 * 返回随机权重索引
	 * 
	 * @param weightList
	 * @return
	 */
	public static int randWeight(List<Integer> weightList, int weightCount) {
		int rand = random(1, weightCount);
		int weight = 0;
		for (int i = 0; i < weightList.size(); i++) {
			weight += weightList.get(i);
			if (rand <= weight) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * 随机获取相应的id
	 * TOOD:这个函数需要重构
	 * @param randStr
	 *            随机字符串 以1,2;2,3;4,5形式
	 * @return randId 2014-3-26 add
	 */
	public static int randId(String randStr) {
		// 返回数字
		int rand = 0;
		// 转成数组形式
		String[] randS = randStr.split(";");
		// 总数(累加)
		int randCount = 0;
		String str = "";
		// 循环算出总数
		for (int i = 0; i < randS.length; i++) {
			str = randS[i];
			if (!StringUtils.isEmpty(str)) {
				String[] s = str.split(",");
				if(s.length != 2){
					throw new IllegalArgumentException("第" + i
							+ "行 ，输入格式不正确！");
				}else{
					try {
						randCount += Integer.parseInt(s[1]);
					} catch (Exception e) {
						throw new IllegalArgumentException("第" + i
								+ "行 ，输入为非法数字！", e);
					}
				}
			}
		}
		// 获取随机数
		int randNum = (int) (Math.random() * randCount) + 1;
		// 算累加数
		int newRandCount = 0;
		// 再次循环获取相应的值
		for (int i = 0; i < randS.length; i++) {
			str = randS[i];
			if (!StringUtils.isEmpty(str)) {
				String[] s = str.split(",");
				if(s.length != 2){
					throw new IllegalArgumentException("第" + i
							+ "行 ，输入格式不正确！");
				}else{
					try {
						newRandCount += Integer.parseInt(s[1]);
					} catch (Exception e) {
						throw new IllegalArgumentException("第" + i
								+ "行 ，输入为非法数字！", e);
					}
					// 判断是否在范围内 如果是就获取相应的值
					if (randNum <= newRandCount) {
						try {
							rand = Integer.parseInt(s[0]);
						} catch (Exception e) {
							throw new IllegalArgumentException("第" + i
									+ "行 ，输入为非法数字！", e);
						}
						break;
					}
				}
			}
		}
		return rand;
	}
	/**  
     * 对double数据进行取精度.  
     * @param value  double数据.  
     * @param scale  精度位数(保留的小数位数).  
     * @param roundingMode  精度取值方式.  
     * @return 精度计算后的数据.  
     */  
    public static double round(double value, int scale, int roundingMode) {   
        BigDecimal bd = new BigDecimal(value);   
        bd = bd.setScale(scale, roundingMode);   
        double d = bd.doubleValue();   
        bd = null;   
        return d;   
    }   
    
    public static void main(String[] args) {
    	float rateAry[] = {0.20f,0.10f,0.99f};
    	for(int i = 0;i<10;i++){
    		int a = MathUtils.luckyDraw(rateAry);
    		System.out.println(a);
    	}
		
	}
}
