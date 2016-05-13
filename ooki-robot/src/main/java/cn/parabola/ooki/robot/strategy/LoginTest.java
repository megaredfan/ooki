package cn.parabola.ooki.robot.strategy;

import cn.parabola.ooki.core.auto.GameGlobalProtos.MessageType;
import cn.parabola.ooki.core.auto.GameGlobalProtos.MessageCode;
import org.springframework.stereotype.Service;

import cn.parabola.ooki.core.auto.GameGlobalProtos.PlatformType;
import cn.parabola.ooki.core.auto.GameProtos.CSLogin;
import cn.parabola.ooki.core.auto.GameProtos.MessageBody;
import cn.parabola.ooki.robot.core.Robot;
import cn.parabola.ooki.robot.core.TestGameFactory;

@Service
public class LoginTest extends TestGameFactory implements Runnable {

	private Robot robot;
	
	public LoginTest() {
		super();
	}

	public LoginTest(Robot robot) {
		super();
		this.robot = robot;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public void run() {
		try{
			MessageBody.Builder body = MessageBody.newBuilder();
			//第一步：初始用户登陆MESSAGE_ID_LOGIN
	    	body.setMessageType(MessageType.MSG_ID_LOGIN);
	    	
	    	CSLogin.Builder csLogin = CSLogin.newBuilder();
	    	csLogin.setPlatformType(PlatformType.UC);
	    	//csLogin.setVersion("1.0.0");
	    	body.setMessageCode(MessageCode.OK);
	    	body.setCsLogin(csLogin);
	    	body.setSessionKey("SMMjstT32a6VBP+OlhFMS1+MFdCnx4FmfdmgcMc3L6Lk8ljKguRvm2wPOwkG9ab5Octgv49xS09ewx/LAq2V4Q==");
	    	try {
				this.send(body.build().toByteArray(),robot);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	 
//			
//			SimpleKMessage sm_createNewRole=null;
//			try {
//				sm_createNewRole = this.send(body.build().toByteArray(),robot);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			KHttpMessageContext context = new KHttpMessageContext(sm_createNewRole);
//			context.getKHttpMessage().getMessageContents();
//	    	SCCreateNewRoleResult.Builder scCreateNewRoleResult = SCCreateNewRoleResult.newBuilder();
//			for(MessageContent mc:context.getKHttpMessage().getMessageContents()){
//				scCreateNewRoleResult = mc.getScCreateNewRoleResult().toBuilder();
//			}
	    	
//	    	long roleId = 137573171221L;
//	    	
//	    	
//	    	//3、战斗开始MESSAGE_ID_BATTLE_START 以下为循环发送交易，
//	    	//模拟多用户长时间在线对服务器的压力
//			
//	    	System.out.println("roleId="+roleId);
//			
//			//登陆MESSAGE_ID_LOGIN
//			System.out.println(MessageType.MESSAGE_ID_LOGIN);
//	    	body.setMessageType(MessageType.MESSAGE_ID_LOGIN);
//	    	body.setRoleId(roleId);
//	    	CSLogin.Builder csLoginWithId = CSLogin.newBuilder();
//	    	csLoginWithId.setVersion("1.0.1");
//	    	body.setCsLogin(csLoginWithId);
//	    	try {
//				this.send(body.build().toByteArray(),robot);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
	    	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
