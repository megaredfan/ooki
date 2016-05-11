package cn.parabola.ooki.log.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import cn.parabola.ooki.log.GameLog;
import cn.parabola.ooki.log.mapper.CommonsMapper;

@Service
public class PlayerLog extends GameLog {

	public PlayerLog() {
	}

	public PlayerLog(long playerId, String messageType, String content, 
			int platformType, String deviceType,int messageEnum) {
		this.playerId = playerId;
		this.actionTime = new Timestamp(System.currentTimeMillis());
		this.messageType = messageType;
		this.content = content;
		this.islog = 1;
		this.platformType = platformType;
		this.deviceType = deviceType;
		this.messageEnum = messageEnum;
	}
	
	public PlayerLog(long playerId, String messageType, String content, 
			int platformType, String deviceType,int messageEnum,int step) {
		this.playerId = playerId;
		this.actionTime = new Timestamp(System.currentTimeMillis());
		this.messageType = messageType;
		this.content = content;
		this.islog = 1;
		this.platformType = platformType;
		this.deviceType = deviceType;
		this.messageEnum = messageEnum;
		this.step = step;
	}
	
	public PlayerLog(long playerId, String messageType, int amount, int currency, 
			int mold, int islog, int platformType, String deviceType, int messageEnum) {
		this.playerId = playerId;
		this.actionTime = new Timestamp(System.currentTimeMillis());
		this.messageType = messageType;
		this.amount = amount;
		this.currency = currency;
		this.mold = mold;
		this.islog = islog;
		this.platformType = platformType;
		this.deviceType = deviceType;
		this.messageEnum = messageEnum;
	}

	private int id;
	private long playerId;
	private Timestamp actionTime;
	private String messageType;
	private String content;
	private String strContent;
	private int amount;//数量 
	private int currency;//币种 1：YB 2：铜钱
	private int mold;//1：消耗 2:产出
	private int islog;//1：是日志
	private int platformType;//渠道类型
	private String deviceType;//设备类型
	private int messageEnum;//消息类型
	private int step;

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

	public Timestamp getActionTime() {
		return actionTime;
	}

	public void setActionTime(Timestamp actionTime) {
		this.actionTime = actionTime;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStrContent() {
		return strContent;
	}

	public void setStrContent(String strContent) {
		this.strContent = strContent;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCurrency() {
		return currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public int getMold() {
		return mold;
	}

	public void setMold(int mold) {
		this.mold = mold;
	}

	public int getIslog() {
		return islog;
	}

	public void setIslog(int islog) {
		this.islog = islog;
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

	public int getMessageEnum() {
		return messageEnum;
	}

	public void setMessageEnum(int messageEnum) {
		this.messageEnum = messageEnum;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	@Override
	public String toString() {
		return "PlayerLog [id=" + id + ", playerId=" + playerId
				+ ", actionTime=" + actionTime + ", messageType=" + messageType
				+ ", content=" + content + ", strContent="
				+ strContent + ", amount=" + amount + ", currency=" + currency
				+ ", mold=" + mold + ", islog=" + islog + ", platformType="
				+ platformType + ", deviceType=" + deviceType
				+ ", messageEnum=" + messageEnum + "]";
	}

	@Override
	public int getGameLogType() {
		return GameLog.PLAYER;
	}

	@Override
	public String getTableName() {
		return "player_log_template";
	}

	@Override
	public void record(CommonsMapper commonsMapper) {
		commonsMapper.recordPlayer(this);
	}

}
