package cn.parabola.ooki.robot.strategy;

import cn.parabola.ooki.core.auto.GameGlobalProtos.PlatformType;
import cn.parabola.ooki.core.auto.GameProtos.CSCreatePlayer;
import cn.parabola.ooki.core.auto.GameProtos.MessageBody;
import cn.parabola.ooki.core.auto.GameGlobalProtos.MessageCode;
import cn.parabola.ooki.core.auto.GameGlobalProtos.MessageType;
import cn.parabola.ooki.robot.core.Robot;
import cn.parabola.ooki.robot.core.TestGameFactory;

public class CreatePlayerTest extends TestGameFactory implements Runnable {

private Robot robot;
	
	public CreatePlayerTest() {
		super();
	}

	public CreatePlayerTest(Robot robot) {
		super();
		this.robot = robot;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	
	@Override
	public void run() {
		MessageBody.Builder body = MessageBody.newBuilder();
		//创建角色信息MSG_ID_CREATE_PLAYER
		
    	body.setMessageCode(MessageCode.OK);
		body.setMessageType(MessageType.MSG_ID_CREATE_PLAYER);
		CSCreatePlayer.Builder csCreatePlayer = CSCreatePlayer.newBuilder();
		csCreatePlayer.setDeviceType("iphone10");
		
		csCreatePlayer.setPlayerName("megaredfan");
		csCreatePlayer.setDeviceType("windows7 x64");
		csCreatePlayer.setPlatformType(PlatformType.UC);
		body.setCsCreatePlayer(csCreatePlayer);

		try{
			this.send(body.build().toByteArray(), robot);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
