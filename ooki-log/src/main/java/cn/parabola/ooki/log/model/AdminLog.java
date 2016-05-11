package cn.parabola.ooki.log.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import cn.parabola.ooki.log.GameLog;
import cn.parabola.ooki.log.mapper.CommonsMapper;

@Service
public class AdminLog extends GameLog {

	public AdminLog() {
	}

	public AdminLog(String name, String ip, String content) {
		this.name = name;
		this.ip = ip;
		this.content = content;
		this.actionTime = new Timestamp(System.currentTimeMillis());
	}

	private int id;
	private String name;
	private String ip;
	private Timestamp actionTime;
	private String content;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getActionTime() {
		return actionTime;
	}

	public void setActionTime(Timestamp actionTime) {
		this.actionTime = actionTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "AdminLog [id=" + id + ", name=" + name + ", ip=" + ip
				+ ", actionTime=" + actionTime + ", content=" + content 
				+ "]";
	}

	@Override
	public int getGameLogType() {
		return GameLog.ADMIN;
	}

	@Override
	public String getTableName() {
		return "admin_log_template";
	}

	@Override
	public void record(CommonsMapper commonsMapper) {
		commonsMapper.recordAdmin(this);
	}
}
