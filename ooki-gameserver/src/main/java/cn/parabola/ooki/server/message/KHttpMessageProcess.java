package cn.parabola.ooki.server.message;

import cn.parabola.ooki.core.logic.*;
import cn.parabola.ooki.log.LogManager;
import cn.parabola.ooki.server.logic.GameConfigManager;
import cn.parabola.ooki.server.logic.HttpMessageManager;
import org.apache.log4j.Logger;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

public abstract class KHttpMessageProcess {

	@Resource(name = "playerLogic")
	protected PlayerLogic playerLogic;
	
	@Resource(name = "configLogic")
	protected ConfigLogic configLogic;

	@Resource(name = "httpMessageManager")
	protected HttpMessageManager httpMessageManager;

	@Resource(name = "logManager")
	protected LogManager logManager;

	@Resource(name = "jedisStoragePool")
	protected JedisPool jedisStoragePool;
	
	@Resource(name = "chatLogic")
	protected ChatLogic chatLogic;

	@Resource(name = "gameConfigManager")
	protected GameConfigManager gameConfigManager;
	
	protected final static Logger log = Logger.getLogger(KHttpMessageProcess.class);
}
