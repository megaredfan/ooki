package cn.parabola.ooki.server.logic.player;

import cn.parabola.ooki.core.auto.GameProtos.MessageBody;
import cn.parabola.ooki.server.message.KHttpMessageHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PlayerHandler extends KHttpMessageHandler{

	@Resource(name = "playerProcess")
	private PlayerProcess playerProcess;
	
	@Override
	public int handler(MessageBody mb) {
		switch(mb.getMessageType()){
		case MSG_ID_CREATE_PLAYER:
			playerProcess.createNewPlayer(mb.getCsCreatePlayer());
			return KHttpMessageHandler.CATCH_HANDLER;
		default:
			return KHttpMessageHandler.NOT_CATCH_HANDLER;
		}
	}
}
