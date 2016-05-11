package cn.parabola.ooki.log.model;

import cn.parabola.ooki.log.GameLog;
import cn.parabola.ooki.log.mapper.CommonsMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class LoginLog extends GameLog{
	public LoginLog(){
		
	}
	
	public LoginLog(long playerId,Timestamp registerTime,int platformType,String deviceType){
		this.playerId = playerId;
		this.loginTime = new Timestamp(System.currentTimeMillis());
		this.registerTime = registerTime;
		this.platformType = platformType;
		this.deviceType = deviceType;
	}
	
	private int id;
	private long playerId;
	private Timestamp loginTime;
	private Timestamp registerTime;
	private int platformType;//渠道类型
	private String deviceType;//设备类型
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public Timestamp getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public int getPlatformType() {
		return platformType;
	}

	public void setPlatformType(int platformType) {
		this.platformType = platformType;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Override
	public String toString() {
		return "LoginLog [playerId=" + playerId + ", loginTime=" + loginTime
				+ ", registerTime=" + registerTime + ", platformType="
				+ platformType + ", deviceType=" + deviceType + "]";
	}

	@Override
	public int getGameLogType() {
		return GameLog.LOGIN;
	}
	@Override
	public String getTableName() {
		return "login_log_template";
	}
	
	@Override
	public void record(CommonsMapper commonsMapper) {
		commonsMapper.recordLogin(this);
	}
}
