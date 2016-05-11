package cn.parabola.ooki.core.logic;

import cn.parabola.ooki.core.auto.GameGlobalProtos.PlatformType;
import cn.parabola.ooki.core.auto.GameProtos;
import cn.parabola.ooki.core.mapper.AccountMapper;
import cn.parabola.ooki.core.mapper.PlayerMapper;
import cn.parabola.ooki.core.model.Account;
import cn.parabola.ooki.core.model.Player;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kriver.core.common.MathUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PlayerLogic {
	private final static Logger logger = Logger.getLogger(PlayerLogic.class);
	

	@Resource(name = "jedisStoragePool")
	private JedisPool jedisStoragePool;

	@Resource(name = "jedisCachePool")
	private JedisPool jedisCachePool;

	@Resource(name = "playerMapper")
	private PlayerMapper playerMapper;

	@Resource(name = "accountMapper")
	private AccountMapper accountMapper;
	
	private final static String PLAYER_CACHE_KEY = "p_";
	private final static int EXPIRE_SECOND = 60 * 60 * 24;//缓存一天
	private final static String PLAYER_INDEX = "player_index";
	public final static String UID_PLAYER = "uid_player";

	/**
	 * 创建用户角色
	 * @param playerId
	 * @param deviceType
	 * @param playerName
	 * @param bindAccount
	 * @param platformUid
	 * @param platformType
	 * @return
	 */
	public Player createPlayer(long playerId, String deviceType,String playerName,String bindAccount, String platformUid,PlatformType platformType) {
		//如果设备类型或角色名称为空时，返回null
		if (StringUtils.isBlank(deviceType) || StringUtils.isBlank(playerName)) {
			logger.error("create player error,deviceType=" + deviceType
					+ ",playerName=" + playerName);
			return null;
		}
		//设置玩家默认信息
		Player player = new Player();
		player.setDeviceType(deviceType);
		player.setPlayerId(playerId);
		player.setPlayerName(playerName);
		player.setCreateTime(new Timestamp(System.currentTimeMillis()));
		player.setLevel(1);
		player.setPassword(DigestUtils.md5Hex(String.valueOf(MathUtils.random(100000, 999999))));
		player.setChatOn(0);
		//插入玩家信息
		if (playerMapper.insert(player) != 1) {
			logger.error("insert error,player="+player);
			return null;
		}
		//playerStateMapper.createPlayerState(new PlayerState(playerId,configLogic.getGlobalConfig().getDefaultSystemId()));
		
		//rankLogic.updateRankWithIncrement(RankLogic.RankType.GOLD_RANK, playerId, Double.valueOf(player.getGold()));
		
		return player;
	}
	
	public void addAccount(String platformUid,PlatformType type, long playerId){
		this.accountMapper.addAccount(new Account(platformUid,type.getNumber(),playerId));
	}


	
	public Account getAccount(String platformUid,PlatformType type){
		return this.accountMapper.getAccount(platformUid, type.getNumber());
	}
	
	/**
	 * 通过玩家ID获取相应的玩家信息
	 * 
	 * @param playerId
	 * @return
	 */
	public Player getPlayer(Long playerId) {
		Player player = playerMapper.select(playerId);
		return player;
	}
	
	private final static String New_Player_Key = "np_";
	public String buildNewPlayerKey(long playerId){
		return New_Player_Key + playerId;
	}
	public void addNewPlayerStepIds(long playerId,List<Integer> ids){
		Jedis jedis = this.jedisStoragePool.getResource();
		try{
			String[] sid = new String[ids.size()];
			for(int i = 0;i<sid.length;i++){
				sid[i] = String.valueOf(ids.get(i));
			}
			jedis.sadd(this.buildNewPlayerKey(playerId), sid);
		}finally{
			jedis.close();
		}
		
	}
	
	public List<Integer> getNewPlayerStepIds(long playerId){
		Jedis jedis = this.jedisStoragePool.getResource();
		try{
			List<Integer> result = new ArrayList<Integer>();
			Set<String> ids = jedis.smembers(this.buildNewPlayerKey(playerId));
			if(ids != null){
				for(String s :ids){
					result.add(Integer.parseInt(s));
				}
			}
			return result;
		}finally{
			jedis.close();
		}
		
	}
	/**
	 * 更新玩家的信息
	 * 
	 * @param player
	 * @return
	 */
	public boolean updatePlayer(Player player) {
		boolean result = playerMapper.update(player) > 0;
		return result;
	}
	
	public GameProtos.Player getPlayerMessage(Player player){
		GameProtos.Player.Builder builder = GameProtos.Player.newBuilder();
		builder.setPlayerId(player.getPlayerId());
		builder.setPwd(player.getPassword());
		builder.setPlayerName(player.getPlayerName());
		builder.setIcon(player.getIcon() == null ? "" : player.getIcon());
		builder.setGold(player.getGold());
		builder.setLevel(player.getLevel());
		builder.setDiamond(player.getDiamond());
		builder.setStatus(player.getStatus());

		
		
		/*Set<String> productIds = paymentLogic.getProductIdsByPlayerId(player.getPlayerId());
		if(productIds != null){
			for(String s : productIds){
				builder.addProductIds(s);
			}
		}*/
		//builder.setWorldChatKey(chatLogic.getWroldChatChannel(null));
		return builder.build();
	}
	
	public static void main(String[] args) {
		System.out.println(137573171273L % 12);
	}

	public List<Player> selectByRange(int start, int length) {
		return playerMapper.selectByRange(start, length);
	}

	public int count() {
		return playerMapper.count();
	}
}
