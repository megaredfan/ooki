package cn.parabola.ooki.core.model;

public class Account {
	private int id;
	private String account;
	private int platformType;
	private long playerId;

	public Account(){
		
	}
	public Account(String account,int platformType,long playerId){
		this.account = account;
		this.platformType = platformType;
		this.playerId = playerId;
	}
	

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getPlatformType() {
		return platformType;
	}
	public void setPlatformType(int platformType) {
		this.platformType = platformType;
	}
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", account=" + account + ", platformType="
				+ platformType + ", playerId=" + playerId + "]";
	}
}
