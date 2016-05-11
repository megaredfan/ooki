package cn.parabola.ooki.server.logic.login;

import cn.parabola.ooki.core.auto.GameProtos.MessageBody;
import cn.parabola.ooki.server.message.KHttpMessageHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginHandler extends KHttpMessageHandler{
	
	@Resource(name = "loginProcess")
	private LoginProcess loginProcess;
	
	@Override
	public int handler(MessageBody messageBody) {
		switch(messageBody.getMessageType()){
		case MSG_ID_LOGIN:
			loginProcess.login(messageBody.getCsLogin());
			return KHttpMessageHandler.CATCH_HANDLER;
		default:
			return KHttpMessageHandler.NOT_CATCH_HANDLER;
		}
	}
}
