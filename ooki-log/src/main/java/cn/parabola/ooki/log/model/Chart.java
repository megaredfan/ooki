package cn.parabola.ooki.log.model;
/**
 * 每天在线人数数据结构
 * @author admin
 *
 */
public class Chart {
	private String date;
	private long count;
	
	public Chart(String date, long count) {
		super();
		this.date = date;
		this.count = count;
	}
	public Chart() {
		super();
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "OnlineData [date=" + date + ", count=" + count + "]";
	}
	
}
