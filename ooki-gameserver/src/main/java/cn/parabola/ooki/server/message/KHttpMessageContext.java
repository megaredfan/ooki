package cn.parabola.ooki.server.message;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kriver.core.common.SecretManager;

import cn.parabola.ooki.core.auto.GameGlobalProtos.MessageCode;
import cn.parabola.ooki.core.auto.GameProtos.MessageBody;
import cn.parabola.ooki.core.model.Player;

public class KHttpMessageContext {
	private final static Logger log = Logger
			.getLogger(KHttpMessageContext.class);
	private final static SecretManager secretManager = new SecretManager(
			"lichuan_qinhuan_");

	public KHttpMessageContext(SimpleKHttpMessage kHttpMessage) {
		this.mp = kHttpMessage;
		this.sessionKey = mp.getMessageBody().getSessionKey();
		if (StringUtils.isNotBlank(sessionKey)) {
			this.session = parseSessionKey(sessionKey);
			this.playerId = Long.parseLong(session[1]);
		}
	}

	private SimpleKHttpMessage mp;
	private String sessionKey;
	private long playerId;
	private String[] session;
	public String getSessionPassword(){
		return this.session[2];
	}


	/**
	 * 生成会话key
	 * 
	 * @param playerId
	 * @return
	 */
	public String generateSessionKey(Long playerId,String password) {
		if (playerId == null || playerId == 0) {
			log.error("generateSessionKey error,playerId =" + playerId);
			return null;
		}
		this.sessionKey = secretManager.encode(System.currentTimeMillis() + "," + playerId + ","
				+ password);
		return sessionKey;
	}

	public String[] parseSessionKey(String key) {
		String s = secretManager.decode(key);
		return s.split(",");
	}

	public String getSessionKey() {
		return this.sessionKey;
	}

	/**
	 * 准备发送给客户端的数据
	 */
	private MessageBody.Builder messagebody;

	public void init(MessageBody.Builder messageBody) {
		this.messagebody = messageBody;
	}

	public MessageBody.Builder getMessageBody() {
		return this.messagebody;
	}

	public long getPlayerId() {
		return this.playerId;
	}

	private Player player;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public SimpleKHttpMessage getSimpleKHttpMessage() {
		return this.mp;
	}

	public void send(MessageBody.Builder builder) {
		try {
			if (!builder.hasMessageCode()) {
				builder.setMessageCode(MessageCode.OK);
			}
			MessageBody mc = builder.build();
			log.debug("send:\n" + mc);
			byte[] bytes = mc.toByteArray();
			mp.getOutputStream().write(bytes);
			mp.getOutputStream().flush();
		} catch (IOException e) {
			log.error("send faild,builder=" + builder, e);
		} finally {

		}
	}

}