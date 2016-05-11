package cn.parabola.ooki.log.model;

public class RemainChart {
	private String date;
	private float rate;
	
	public RemainChart() {
		super();
	}

	public RemainChart(String date, float rate) {
		super();
		this.date = date;
		this.rate = rate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "RemainChart [date=" + date + ", rate=" + rate + "]";
	}
}
