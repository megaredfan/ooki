package cn.parabola.ooki.log.model;

import cn.parabola.ooki.log.GameLog;
import cn.parabola.ooki.log.mapper.CommonsMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MessageLog extends GameLog {

	private int id;
	private long playerId;
	private String messageType;
	private Timestamp beginTime;//消息开始时间
	private Timestamp endTime;//消息结束时间
	private long useTime;//消息处理时间
	
	public MessageLog() {
	}
	
	public MessageLog(long playerId, String messageType, long beginTime, long endTime,long useTime) {
		this.playerId = playerId;
		this.messageType = messageType;
		this.beginTime = new Timestamp(beginTime);
		this.endTime = new Timestamp(endTime);
		this.useTime = useTime;
	}

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
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public Timestamp getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public long getUseTime() {
		return useTime;
	}
	public void setUseTime(long useTime) {
		this.useTime = useTime;
	}
	
	@Override
	public String toString() {
		return "MessageLog [id=" + id + ", playerId=" + playerId
				+ ", messageType=" + messageType + ", beginTime=" + beginTime
				+ ", endTime=" + endTime + ", useTime=" + useTime + "]";
	}
	@Override
	public int getGameLogType() {
		return GameLog.MESSAGE;
	}
	@Override
	public String getTableName() {
		return "message_log_template";
	}
	@Override
	public void record(CommonsMapper commonsMapper) {
		commonsMapper.recordMessage(this);
	}
}
