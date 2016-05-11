package cn.parabola.ooki.core.model;

import java.sql.Timestamp;

public class Player {
	private Long playerId;
	private String deviceType;
	private String playerName;
	private String icon;
	private int gold;
	private int diamond;
	private int level;
	private int actionPoint;
	private int rankScore;
	private int feed;
	private String bindAccount;
	private int platformType;
	private String platformUid;
	private int status;//0:正常,1:删除,2:非活跃3天，3：非活跃7天，4是非活跃1个月以上
	private Timestamp createTime;
	private Timestamp lastLoginTime;//最后一次登录的时间
	private int chatOn;
	private String password;//新用户随机生成6位数的密码

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getDiamond() {
		return diamond;
	}

	public void setDiamond(int diamond) {
		this.diamond = diamond;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getActionPoint() {
		return actionPoint;
	}

	public void setActionPoint(int actionPoint) {
		this.actionPoint = actionPoint;
	}

	public int getRankScore() {
		return rankScore;
	}

	public void setRankScore(int rankScore) {
		this.rankScore = rankScore;
	}

	public int getFeed() {
		return feed;
	}

	public void setFeed(int feed) {
		this.feed = feed;
	}

	public String getBindAccount() {
		return bindAccount;
	}

	public void setBindAccount(String bindAccount) {
		this.bindAccount = bindAccount;
	}

	public int getPlatformType() {
		return platformType;
	}

	public void setPlatformType(int platformType) {
		this.platformType = platformType;
	}

	public String getPlatformUid() {
		return platformUid;
	}

	public void setPlatformUid(String platformUid) {
		this.platformUid = platformUid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getChatOn() {
		return chatOn;
	}

	public void setChatOn(int chatOn) {
		this.chatOn = chatOn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Player{" +
				"playerId=" + playerId +
				", deviceType='" + deviceType + '\'' +
				", playerName='" + playerName + '\'' +
				", icon='" + icon + '\'' +
				", gold=" + gold +
				", diamond=" + diamond +
				", level=" + level +
				", actionPoint=" + actionPoint +
				", rankScore=" + rankScore +
				", feed=" + feed +
				", bindAccount='" + bindAccount + '\'' +
				", platformType='" + platformType + '\'' +
				", platformUid='" + platformUid + '\'' +
				", status=" + status +
				", createTime=" + createTime +
				", lastLoginTime=" + lastLoginTime +
				", chatOn=" + chatOn +
				", password='" + password + '\'' +
				'}';
	}
}
