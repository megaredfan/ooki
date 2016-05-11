package cn.parabola.ooki.server.logic.login;

import cn.parabola.ooki.core.auto.GameGlobalProtos.MessageType;
import cn.parabola.ooki.core.auto.GameProtos.CSLogin;
import cn.parabola.ooki.core.auto.GameProtos.MessageBody;
import cn.parabola.ooki.core.auto.GameProtos.SCLogin;
import cn.parabola.ooki.core.model.Account;
import cn.parabola.ooki.core.model.Player;
import cn.parabola.ooki.log.model.PlayerLog;
import cn.parabola.ooki.server.message.KHttpMessageProcess;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class LoginProcess extends KHttpMessageProcess{
	public void login(CSLogin msg) {
		MessageBody.Builder builder = httpMessageManager.getMessageContentBuilder();
		SCLogin.Builder scLogin = SCLogin.newBuilder();
		scLogin.setServerTime((int) (System.currentTimeMillis() / 1000));
		
		Player player = null;
		if(msg.hasPlatformUid() && StringUtils.isNotBlank(msg.getPlatformUid())){
			Account account = playerLogic.getAccount(msg.getPlatformUid(), msg.getPlatformType());
			player = playerLogic.getPlayer(account.getPlayerId());
		}else{
			 player = playerLogic.getPlayer(httpMessageManager.getPlayerId());
		}

		if (player == null) {
			scLogin.setIsNewPlayer(true);
			builder.setScLogin(scLogin);
			httpMessageManager.send(builder);
			return;
		}
		
		httpMessageManager.initSessionKey(player.getPlayerId(),player.getPassword());
		//检查用户是否已经注册为亲加用户
//		if(playerLogic.getPlayer(player.getPlayerId()).getChatOn()==0){
//			chatLogic.activateChat(player);
//		}

		scLogin.setPlayer(playerLogic.getPlayerMessage(player));
		builder.setScLogin(scLogin);
		
		logManager.record(new PlayerLog(player.getPlayerId(), "登录游戏",msg.toString(),player.getPlatformType(),player.getDeviceType(),MessageType.MSG_ID_LOGIN_VALUE));
		
		httpMessageManager.send(builder);
	}
}
