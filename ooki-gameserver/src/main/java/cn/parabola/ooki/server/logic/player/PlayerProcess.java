package cn.parabola.ooki.server.logic.player;

import cn.parabola.ooki.core.auto.GameGlobalProtos.MessageCode;
import cn.parabola.ooki.core.auto.GameGlobalProtos.MessageType;
import cn.parabola.ooki.core.auto.GameProtos.CSCreatePlayer;
import cn.parabola.ooki.core.auto.GameProtos.MessageBody;
import cn.parabola.ooki.core.auto.GameProtos.SCCreatePlayer;
import cn.parabola.ooki.core.model.Player;
import cn.parabola.ooki.log.model.PlayerLog;
import cn.parabola.ooki.server.logic.GameConfigManager;
import cn.parabola.ooki.server.message.KHttpMessageProcess;
import org.kriver.core.uuid.UUID64Tetris;
import org.springframework.stereotype.Service;

@Service
public class PlayerProcess extends KHttpMessageProcess {
	
	/**
	 * 创建用户
	 * 
	 * @param msg
	 */
	public void createNewPlayer(CSCreatePlayer msg) {
		MessageBody.Builder builder = httpMessageManager.getMessageContentBuilder();

		//TODO:oldId可以优化一下用redis来做计数器，递增的同时存储数据
		UUID64Tetris uuid = GameConfigManager.uuid;
		long playerId = uuid.getNextUUID();
		gameConfigManager.setOldPlayerId(uuid.getOid(playerId));

		Player player = playerLogic.createPlayer(playerId, msg.getDeviceType(),
				msg.getPlayerName(), "",msg.getPlatformUid(), msg.getPlatformType());
		if (player == null) {
			log.warn("create player error,msg=" + msg);
			builder.setMessageCode(MessageCode.ERR);
			httpMessageManager.send(builder);
			return;
		}
		//注册亲家用户
		//chatLogic.activateChat(player);
		
		httpMessageManager.initSessionKey(player.getPlayerId(),player.getPassword());
		SCCreatePlayer.Builder scCreatePlayer = SCCreatePlayer.newBuilder();
		scCreatePlayer.setPlayer(playerLogic.getPlayerMessage(player));
		
		builder.setScCreatePlayer(scCreatePlayer);
		logManager.record(new PlayerLog(player.getPlayerId(), "创建角色",msg.toString(),player.getPlatformType(),player.getDeviceType(),MessageType.MSG_ID_CREATE_PLAYER_VALUE));
		
		httpMessageManager.send(builder);
	}
}
