package cn.parabola.ooki.core.model;

import org.apache.commons.lang.StringUtils;

public class GameVersion {
	public GameVersion(){
		
	}
	
	public GameVersion(int firstNumber, int middleNumber,int lasterNumber){
		this.firstNumber = firstNumber;
		this.middleNumber = middleNumber;
		this.lasterNumber = lasterNumber;
		this.version = firstNumber + "." + middleNumber + "." + lasterNumber;
	}
	public GameVersion(String version){
		if(StringUtils.isBlank(version)){
			throw new IllegalArgumentException("version is null,version="+ version);
		}
		String[] vs = version.split("\\.");
		if(vs == null || vs.length != 3){
			throw new IllegalArgumentException("version format error,version="+ version);
		}
		this.firstNumber = Integer.parseInt(vs[0]);
		this.middleNumber = Integer.parseInt(vs[1]);
		this.lasterNumber = Integer.parseInt(vs[2]);
		this.version = version;
	}
	/**
	 * firstNumber不一致的客户端禁止游戏
	 */
	private int firstNumber;
	/**
	 * middleNumber不一致的客户端需要重新下载客户端，但是可以继续游戏
	 */
	private int middleNumber;
	/**
	 * lastNumber代表的是资源文件的版本号，如果低于最新的话，就需要在线更新资源
	 */
	private int lasterNumber;
	
	/**
	 * 版本号的字符串格式
	 */
	private String version;

	public int getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(int firstNumber) {
		this.firstNumber = firstNumber;
	}

	public int getMiddleNumber() {
		return middleNumber;
	}

	public void setMiddleNumber(int middleNumber) {
		this.middleNumber = middleNumber;
	}

	public int getLasterNumber() {
		return lasterNumber;
	}

	public void setLasterNumber(int lasterNumber) {
		this.lasterNumber = lasterNumber;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
}
