package cn.parabola.ooki.robot.core;

import java.util.ArrayList;
import java.util.List;

public class Robot {
	private String ip;
	private Integer port;
	private Integer interruptTime;
	
	private List<Runnable> strategyList = new ArrayList<Runnable>();
	
	public void addStrategy(Runnable strategy){
		this.strategyList.add(strategy);
	}
	
	public void start(){
		for(Runnable r:strategyList){
			new Thread(r).start();
		}
	}
	
	public Robot() {
		super();
	}

	public Robot(String ip, Integer port,Integer interruptTime) {
		super();
		this.ip = ip;
		this.port = port;
		this.interruptTime = interruptTime;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Integer getInterruptTime() {
		return interruptTime;
	}
	public void setInterruptTime(Integer interruptTime) {
		this.interruptTime = interruptTime;
	}

	@Override
	public String toString() {
		return "Robot [ip=" + ip + ", port=" + port + ", interruptTime="
				+ interruptTime + ", strategyList=" + strategyList + "]";
	}



}
