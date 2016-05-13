package cn.parabola.ooki.robot.main;

import cn.parabola.ooki.core.auto.GameProtos.MessageBody;
import cn.parabola.ooki.robot.core.Robot;
import cn.parabola.ooki.robot.strategy.*;
/**
 * 测试机器人入口类
 * @author admin
 *
 */
public class Main 
{
	private static final int ALL_STRATEGY = 0;
	private static final int LOGIN_STRATEGY = 1;
	private static final int CREATE_PLAYER_STRATEGY = 2;
	


	public byte[] convert(MessageBody mc){
		return null;
	}
	/**
	 * 参数说明：
	 * 192.168.0.202 8080 1 300 20
	 * 服务器ip  端口 策略id 执行次数 间隔时间(毫秒)
	 * @param args
	 * @throws Exception
	 */
    public static void main( String[] args ) throws Exception
    {
    	//ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
//    	//LoginTest loginTest = context.getBean(LoginTest.class);
//    	//CreateNewRoleTest createNewRoleTest = context.getBean(CreateNewRoleTest.class);
    	
    	args = new String[5];
    	args[0] = "42.96.177.79" ;
    	args[1] = "8888";
		args[2] = ""+LOGIN_STRATEGY;
    	//args[2] = ""+CREATE_PLAYER_STRATEGY;
    	args[3] = "1";
    	args[4] = "1000";

    	if(args == null || args.length != 5){
    		throw new RuntimeException("参数错误，正确格式为：服务器ip  端口 策略id 执行次数 间隔时间(毫秒)");
    	}
    	
    	String ip = args[0];
    	int port = Integer.valueOf(args[1]);
    	int strategyId = Integer.valueOf(args[2]);
    	int times = Integer.valueOf(args[3]);
    	int interruptTime = Integer.valueOf(args[4]);

    	int i=0;
    	while(i<times){
    		System.out.println("----------------------启动第"+i+"个机器人-----------------------");
        	Robot robot = new Robot(ip,port,interruptTime);
			switch(strategyId){
		//	case ALL_STRATEGY:robot.addStrategy(new SimulateUserTest(robot));break;
			case LOGIN_STRATEGY:robot.addStrategy(new LoginTest(robot));break;
//			case BATTLE_START_STRATEGY:robot.addStrategy(new BattleTest(robot));break;
//			case BATTLE_END_STRATEGY:robot.addStrategy(new BattleTest(robot));break;
//			case SHOW_RANK_STRATEGY:robot.addStrategy(new RankTest(robot));break;
//			case ANCILENT_SHOW_STRATEGY:robot.addStrategy(new AncilentShowTest(robot));break;
//			case ANCILENT_START_STRATEGY:robot.addStrategy(new AncilentStartTest(robot)); break;
//			case ANCILENT_END_STRATEGY:robot.addStrategy(new AncilentEndTest(robot)); break;
//			case MESSAGE_ID_BUY_RELIVE_SHOW:
//					robot.addStrategy(new BattleTest(robot)); 
//					robot.addStrategy(new ReliveShowTest(robot)); 
//			break;
//			case MESSAGE_ID_BIND_ACCOUNT:robot.addStrategy(new BindAccountTest(robot)); break;
//			case MESSAGE_ID_SHOW_PUB:robot.addStrategy(new ShowPubTest(robot)); break;
//			case MESSAGE_ID_DAILY_QUEST_FLUSH:robot.addStrategy(new DailyQuestFlushTest(robot)); break;
//			case MESSAGE_ID_GENERALS_UP:robot.addStrategy(new GeneralsUpTest(robot)); break;
//			case MESSAGE_ID_BATTLE_SWEEP:robot.addStrategy(new BattleSweeTest(robot)); break;
//			case MESSAGE_ID_SHARE:robot.addStrategy(new ShareTest(robot));break;
			case CREATE_PLAYER_STRATEGY:robot.addStrategy(new CreatePlayerTest(robot));break;
			}
			robot.start();
			Thread.sleep(interruptTime);
			i++;
    	}
    	
    }

}
