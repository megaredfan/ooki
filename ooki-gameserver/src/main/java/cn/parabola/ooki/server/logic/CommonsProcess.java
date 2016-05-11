package cn.parabola.ooki.server.logic;

import cn.parabola.ooki.core.auto.GameGlobalProtos.MessageCode;
import cn.parabola.ooki.core.auto.GameProtos.MessageBody;
import cn.parabola.ooki.core.logic.PlayerLogic;
import cn.parabola.ooki.core.model.Player;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 对发给客户端的请求做一些通用的附加值计算
 * 
 * @author bear
 * 
 */
@Service
public class CommonsProcess {

	@Resource(name = "playerLogic")
	private PlayerLogic playerLogic;

//	@Resource(name = "friendLogic")
//	private FriendLogic friendLogic;
	
	@Resource(name = "httpMessageManager")
	private HttpMessageManager httpMessageManager;
	
	private final static Logger log = Logger.getLogger(CommonsProcess.class);

	
	/**
	 * 处理邮件的同步问题
	 * 
	 * @param builder
	 * @param player
	 */
	private void doMail(MessageBody.Builder builder, Player player) {
//		SCUncheckMailIds.Builder scUncheckMailIds = SCUncheckMailIds.newBuilder();
//		List<Long> ids = mailLogic.getMailIds(player.getPlayerId(), false);
//		ids.addAll(mailLogic.getSystemMailIds(player.getPlayerId()));
//		scUncheckMailIds.addAllMailIds(ids);
//		builder.setScUncheckMailIds(scUncheckMailIds);
	}


	private void doCityResource(Player player,MessageBody.Builder builder){
		
	}
	
	private void doNotice(MessageBody.Builder builder, Player player){
		// 添加公告通知
//		List<String> notices = new ArrayList<String>();
//		Timestamp time = new Timestamp(System.currentTimeMillis());
//		List<GameNotice> gameNotices = gameNoticeLogic
//				.getGameNoticeByTime(time);
//		for (GameNotice gn : gameNotices) {
//			notices.add(gn.getContent());
//		}
		
//		SCGameNotice.Builder scGameNoticeBuilder = SCGameNotice.newBuilder();
//		scGameNoticeBuilder.addAllNotices(notices);
//		builder.setScGameNotice(scGameNoticeBuilder);
	}
	
	/**
	 * 同步各种数据
	 * 
	 * @param builder
	 * @param player
	 */
	public void process(Player player,MessageBody.Builder builder) {
		if (player != null && builder.getMessageCode() == MessageCode.OK) {
			/*if(player.isNeedSave()){
				SCUpdatePlayerInfo.Builder scUpdatePlayerInfo = SCUpdatePlayerInfo.newBuilder();
				scUpdatePlayerInfo.setDiamond(player.getDiamond());
				scUpdatePlayerInfo.setGold(player.getGold());
				builder.setScUpdatePlayerInfo(scUpdatePlayerInfo);
			}*/
		}
	}
}
