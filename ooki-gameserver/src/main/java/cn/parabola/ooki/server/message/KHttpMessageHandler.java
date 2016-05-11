package cn.parabola.ooki.server.message;

import cn.parabola.ooki.core.auto.GameProtos.MessageBody;
import cn.parabola.ooki.server.logic.HttpMessageManager;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

public abstract class KHttpMessageHandler {
	
	@Resource(name = "httpMessageManager")
	private HttpMessageManager httpMessageManager;
	
	@PostConstruct
	public void init(){
		httpMessageManager.registHandler(this);
	}
	/**
	 * 被处理器捕获到了
	 */
	public static final int CATCH_HANDLER = 1;
	/**
	 * 没有被处理器捕获到
	 */
	public static final int NOT_CATCH_HANDLER = 0;
	/**
	 * 捕获并处理的话返回1
	 * @param messageBody
	 * @return
	 */
	public abstract int handler(MessageBody messageBody);
}
