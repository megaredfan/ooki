package cn.parabola.ooki.server.logic;

import cn.parabola.ooki.core.auto.GameGlobalProtos.PlatformType;
import cn.parabola.ooki.core.auto.GameGlobalProtos.VersionStatus;
import cn.parabola.ooki.core.mapper.GameServerMapper;
import cn.parabola.ooki.core.model.GameServer;
import cn.parabola.ooki.core.model.GameVersion;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.kriver.core.uuid.UUID64Tetris;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class GameConfigManager {
	public static UUID64Tetris uuid;
//	
//	@Resource(name = "configManager")
//	private ConfigManager configManager;
//	

	
	@Resource(name = "jedisStoragePool")
	private JedisPool jedisStoragePool;
	
//	@Resource(name = "chatLogic")
//	private ChatLogic chatLogic;

	@Resource(name = "gameServerMapper")
	private GameServerMapper gameServerMapper;

	private boolean isShow;
	private String isShowVersion;
	
	private String clientReleaseUrlIOS;
	private String clientReleaseUrlUC;
	private String clientReleaseUrl360;
	private String clientReleaseUrlBAIDU;
	private String clientReleaseUrlXIAOMI;
	
	private String clientResourceUrl;
	private GameVersion serverVersion;
	
	private String sandBoxUrl;
	private String verifyUrl;
	private String bundleId;
	private int appId;
	private boolean isSandbox;
	
	private GameServer gameServer = new GameServer();
	private String serverKey;
	
	public boolean isShow() {
		return isShow;
	}
	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}
	public String getIsShowVersion() {
		return isShowVersion;
	}
	public void setIsShowVersion(String isShowVersion) {
		this.isShowVersion = isShowVersion;
	}
	public GameServer getGameServerConfig(){
		return gameServer;
	}
	public String getAppleUrl(){
		return this.isSandbox ? this.sandBoxUrl : this.verifyUrl;
	}
	public long getOldPlayerId(){
		String count = gameServerMapper.getConfig(this.serverKey);

		if(count == null){
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("key", this.serverKey);
			params.put("value", 0);
			gameServerMapper.insertConfig(params);
			return 0;
		}
		return Long.parseLong(count);
	}
//
	public void setOldPlayerId(long oldPlayerId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("key", this.serverKey);
		params.put("value", oldPlayerId);
		gameServerMapper.updateConfig(params);
	}
	
	public String getReleaseUrl(PlatformType type){
		switch(type){
		case XIAOMI:
			return this.clientReleaseUrlXIAOMI;
		case A360:
			return this.clientReleaseUrl360;
		case IOS:
			return this.clientReleaseUrlIOS;
		case UC:
			return this.clientReleaseUrlUC;
		case BAIDU:
			return this.clientReleaseUrlBAIDU;
		default : throw new RuntimeException("platform type error,type = " + type);
		}
	}
	
	public String getResourceUrl(){
		return this.clientResourceUrl;
	}
	
	public VersionStatus getClientVersionStatus(String version){
		//为了苹果审核的过滤
		if(isShowVersion.equals(version)){
			return VersionStatus.NORMAL;
		}
		if(version == null || !version.contains(".")){
			return VersionStatus.STOP;
		}
		GameVersion clientVersion = new GameVersion(version);
		
		if(clientVersion.getFirstNumber() != serverVersion.getFirstNumber()){
			return VersionStatus.STOP;
		}
		if(clientVersion.getMiddleNumber() < serverVersion.getMiddleNumber()){
			return VersionStatus.UPDATE;
		}
		if(clientVersion.getLasterNumber() != serverVersion.getLasterNumber()){
			return VersionStatus.UPDATE;
		}
		if(clientVersion.getFirstNumber() == serverVersion.getFirstNumber() 
				&& clientVersion.getMiddleNumber() == serverVersion.getMiddleNumber()
				&& clientVersion.getLasterNumber() == serverVersion.getLasterNumber()){
			return VersionStatus.NORMAL;
		}
		return VersionStatus.STOP;
	}
	
	
	public String getSandBoxUrl() {
		return sandBoxUrl;
	}

	public void setSandBoxUrl(String sandBoxUrl) {
		this.sandBoxUrl = sandBoxUrl;
	}

	public String getVerifyUrl() {
		return verifyUrl;
	}

	public void setVerifyUrl(String verifyUrl) {
		this.verifyUrl = verifyUrl;
	}

	public String getBundleId() {
		return bundleId;
	}

	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public boolean isSandbox() {
		return isSandbox;
	}

	public void setSandbox(boolean isSandbox) {
		this.isSandbox = isSandbox;
	}


	
	@PostConstruct
	public void loadConfig() throws Exception{
		Jedis jedis = this.jedisStoragePool.getResource();
		try{
			Configuration config = new PropertiesConfiguration("conf.properties");
			
			this.appId = config.getInt("appId");
			this.verifyUrl = config.getString("url.verify");
			this.sandBoxUrl = config.getString("url.sandBox");
			this.bundleId = config.getString("bundleId");
			this.isSandbox = config.getBoolean("isSandbox");
			this.isShow = config.getBoolean("isShow");
			this.isShowVersion = config.getString("isShowVersion");
			this.serverVersion = new GameVersion(config.getString("server.version"));
			this.clientReleaseUrl360 = config.getString("client.release.url.360");
			this.clientReleaseUrlBAIDU = config.getString("client.release.url.baidu");
			this.clientReleaseUrlIOS = config.getString("client.release.url.ios");
			this.clientReleaseUrlUC = config.getString("client.release.url.uc");
			this.clientReleaseUrlXIAOMI = config.getString("client.release.url.xiaomi");
			this.clientResourceUrl = config.getString("client.resource.url");
			gameServer.setRegion(config.getInt("server.region"));
			gameServer.setServer(config.getInt("server.server"));
			this.serverKey = gameServer.buildGameServerKey();
		//	chatLogic.getWroldChatChannel(serverKey);
			uuid = UUID64Tetris.buildDefaultUUID(gameServer.getRegion(), gameServer.getServer(), this.getOldPlayerId());
		}finally{
			jedis.close();
		}
		
	}
//	
//	public SCUpdateList.Builder getUpdateList(GameVersion version){
//		return null;
//	}
}
