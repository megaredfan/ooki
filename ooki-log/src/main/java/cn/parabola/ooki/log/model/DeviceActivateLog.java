package cn.parabola.ooki.log.model;

import java.sql.Timestamp;

/**
 * @title 广告bean
 * 
 * @author wangshaojun 
 *
 * @version 0.00 2014-08-19 add
 */
public class DeviceActivateLog {
	private int id;//id
	private String idfa;//唯一标识
	private String mac;//mac地址
	private Timestamp createTime;//创建时间
	private int status;//状态
	private String content;//描述

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
