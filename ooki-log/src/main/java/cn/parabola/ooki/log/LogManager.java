package cn.parabola.ooki.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.kriver.core.common.ExecutorUtil;
import org.kriver.core.common.TimeUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import cn.parabola.ooki.log.mapper.CommonsMapper;
import cn.parabola.ooki.log.model.AdminLog;
import cn.parabola.ooki.log.model.LoginLog;
import cn.parabola.ooki.log.model.MessageLog;
import cn.parabola.ooki.log.model.PlayerLog;
import cn.parabola.ooki.log.model.SqlAdapter;

/**
 * 游戏服务器：记录抽卡、充值、删除武将、扩展背包、复活、购买体力值、武将强化、武将进阶、将魂兑换、武将分解将魂、pk、
 * 			打关卡、添加好友、创建角色、战斗开始、战斗结束
 * 管理后台：记录修改用户资料的详细操作，以及操作人。
 * 			游戏服务器相关记录信息的查看界面
 * @author bear
 *
 */
@Service
public class LogManager {
	private final static BlockingQueue<GameLog> queue = new LinkedBlockingQueue<GameLog>();
	private List<GameLog> logs = new ArrayList<GameLog>();
	private final static long ONE_DAY = 24 * 60 * 60 * 1000; 
	/**dateformat初始化非常消耗性能 */
	public static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy_MM_dd");
	
	@Resource(name = "loginLog")
	private LoginLog loginLog;
	
	@Resource(name = "playerLog")
	private PlayerLog playerLog;
	
	@Resource(name = "adminLog")
	private AdminLog adminLog;
	
	@Resource(name = "messageLog")
	private MessageLog messageLog;

	@Resource(name = "commonsMapper")
	private CommonsMapper commonsMapper;
	
	public static BlockingQueue<GameLog> getQueues(){
		return queue;
	}
	
	public void record(GameLog s){
		queue.add(s);
	}
	public static GameLog getLog() throws InterruptedException{
		return queue.take();
	}
	
	@PostConstruct
	public void init(){
		
		//logs.add(loginLog);
		logs.add(playerLog);
		logs.add(adminLog);
		//logs.add(messageLog);
		//logs.add(paymentLog);
		
		ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1); 
		Thread takeThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					for(;;){
						LogManager.getLog().record(commonsMapper);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		//注册jvm关闭后的钩子
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				ExecutorUtil.shutdownAndAwaitTermination(executor);
			}
		}));
		
		//takeThread.setDaemon(true);
		takeThread.start();
		
		
		//检查表
		//服务器启动先check一遍
		for(GameLog tl : logs){
			checkTable(tl);
		}
		//每天凌晨3点再check一遍
		
	    long initDelay  = TimeUtils.getTodayTime(3, 0, 0) - System.currentTimeMillis();  
	    initDelay = initDelay > 0 ? initDelay : ONE_DAY + initDelay;
	    executor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				for(GameLog tl : logs){
					checkTable(tl);
				}
			}
		}, initDelay, ONE_DAY, TimeUnit.MILLISECONDS); 
	}
	
	/**
	 * 检查今天和明天的表是否存在，如果不存在就创建
	 */
	private void checkTable(GameLog tl){
		Calendar c = Calendar.getInstance();
		commonsMapper.executeSql(new SqlAdapter(
				"create table if not exists " + tl.getTableName().replace("template", DATEFORMAT.format(c.getTime())) + " like " + tl.getTableName()));
		c.add(Calendar.DAY_OF_MONTH, 1);
		commonsMapper.executeSql(new SqlAdapter(
				"create table if not exists " + tl.getTableName().replace("template", DATEFORMAT.format(c.getTime())) + " like " + tl.getTableName()));
	}
	
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext(  
	            "classpath*:application-config.xml"); 
		LogManager m = context.getBean(LogManager.class);
//		LoginLog log = new LoginLog();
//		log.setLoginTime(new Timestamp(System.currentTimeMillis()));
//		log.setPlayerId(9999L);
//		m.record(log);
//		Thread.sleep(1000);
//		System.out.println("game logic");
//		LoginLog log1 = new LoginLog();
//		log1.setLoginTime(new Timestamp(System.currentTimeMillis()));
//		log1.setPlayerId(8889L);
//		m.record(log1);
		m.init();
	}
}
