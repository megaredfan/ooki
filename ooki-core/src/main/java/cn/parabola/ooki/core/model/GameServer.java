package cn.parabola.ooki.core.model;

/**
 * 用于服务器集群中，每台服务器的对象
 * @author bear
 *
 */
public class GameServer {
	private int region;
	private int server;
	public int getRegion() {
		return region;
	}
	public void setRegion(int region) {
		this.region = region;
	}
	public int getServer() {
		return server;
	}
	public void setServer(int server) {
		this.server = server;
	}
	
	public String buildGameServerKey(){
		return new StringBuilder().append(this.region).append(",").append(this.server).toString();
	}
	@Override
	public String toString() {
		return "GameServer [region=" + region + ", server=" + server + "]";
	}
	
	
}
