package cn.parabola.ooki.core.logic;

import cn.parabola.ooki.core.model.Player;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.kriver.core.common.HttpUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ChatLogic {
	private final static Logger logger = Logger.getLogger(ChatLogic.class);
	private final static String TOKEN = "gotye_access_token";
	
	@Resource(name = "jedisStoragePool")
	private JedisPool jedisStoragePool;

	@Resource(name = "jedisCachePool")
	private JedisPool jedisCachePool;
	
	@Resource(name = "playerLogic")
	private PlayerLogic playerLogic;

	@Resource(name = "configLogic")
	private ConfigLogic configLogic;
	private String tokenUrl = "https://api.gotye.com.cn/api/accessToken";


	//todo:申请新的亲加账户
	//private String sercetKey = "df3f9aed-d4b9-45f3-9f88-007b8d23089f";
	private String sercetKey = "";
	private long expireTime;
	
	//在redis中存储世界聊天id的key
	private final static String worldChannelId_key = "world_chat_key";
	private final static String qj_api_url_key = "qj_api_url";
	
	/*
	 * 获取世界聊天频道的key,如果没有世界聊天频道就创建一个
	 * @return
	 */

	public String getWroldChatChannel(String server){
		if(server == null){
			server = "test world chat ";
		}
		Jedis jedis = this.jedisStoragePool.getResource();
		String worldChannelId = null;
		try{
			worldChannelId = jedis.get(worldChannelId_key);
			if(worldChannelId == null){
				worldChannelId = this.createChatRoom("world chat " +server,5000);
				jedis.set(worldChannelId_key,worldChannelId);
			}
		}finally{
			jedis.close();
		}
		
		return worldChannelId;
	}

    /*
	 * 获取亲家的通信token,提前1分钟失效获取新的token
	 *  {"api_url":"https://api-a.gotye.com.cn/api","expires_in":86400,"access_token":"e76ac42ccaa9a08afa028d56877322dc"}
	 * @return [0] = token,[1]= apiUrl
	 */

	private String[] getQJToken(){
		Jedis jedis = this.jedisStoragePool.getResource();
		try{
			String token = jedis.get(TOKEN);
			String apiUrl = jedis.get(qj_api_url_key);
			if(StringUtils.isBlank(token) || StringUtils.isBlank(apiUrl)){
				Map<String,String> args = new HashMap<String, String>();
				args.put("grant_type", "client_credentials");
				args.put("client_id", configLogic.getGlobalConfig().getQjConfig().getAppKey());
				args.put("client_secret",sercetKey);
		        JSONObject json = HttpUtils.sendHttpsMessage(tokenUrl,JSONObject.toJSONString(args), null);
		        apiUrl = (String)json.get("api_url");
		        this.expireTime = (long)json.get("expires_in");
				token = (String)json.get("access_token");
				jedis.set(TOKEN, token);
				jedis.expire(TOKEN,(int)(expireTime - 60));
				jedis.set(qj_api_url_key,apiUrl);
			}
			String[] result = new String[2];
			result[0] = token;
			result[1] = apiUrl;
			return result;
		}finally{
			jedis.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject getJsonWithAppKey() {
		JSONObject jobj = new JSONObject();
		jobj.put("appkey", configLogic.getGlobalConfig().getQjConfig().getAppKey());
		return jobj;
	}
	
	private void forceDeleteToken() {
		Jedis jedis = this.jedisStoragePool.getResource();
		try{
			jedis.del(TOKEN);
		}finally{
			jedis.close();
		}
	}
	
	private JSONObject sendMessageForResponse(JSONObject args, String command) {
		String[] token = this.getQJToken();
		return HttpUtils.sendHttpsMessage(token[1] + command, args.toJSONString(),token[0]);
	}
	
	/*
	 * 添加用户到亲加
	 * @param player
	 * 
	 */

	@SuppressWarnings("unchecked")
	public boolean activateChat(Player player) {
		JSONObject args = this.getJsonWithAppKey();
		
		JSONArray array = new JSONArray();
		JSONObject user = new JSONObject();
		user.put("account", player.getPlayerId().toString());
		user.put("nickname", player.getPlayerId().toString()+"-nick");
		user.put("pwd", player.getPassword());
		array.add(user);
		
		args.put("users", array);
		
		JSONObject json = this.sendMessageForResponse(args, "/ImportUsers");
		long status = (long)json.get("status");
		if(status != 200){
			if(status == 401)
				this.forceDeleteToken();
			
			logger.error("regiest qinjia user error,response="+json);
			return false;
		}
		player.setChatOn(1);
		playerLogic.updatePlayer(player);
		return true;
	}
	
	

	/*
	 * 创建聊天室
	 * 
	 * @param roomName,memberCount
	 * @return 新建聊天室的ID
	 */

	@SuppressWarnings("unchecked")
	public String createChatRoom(String roomName,int maxUserNumber) {
		JSONObject args = this.getJsonWithAppKey();
		
		args.put("roomName", roomName);
		args.put("head", null);
		args.put("roomType", 1);
		args.put("scope", 0);
		args.put("maxUserNumber", maxUserNumber);
		
		JSONObject json = this.sendMessageForResponse(args, "/CreateIMRoom");
		long status = (long)json.get("status");
		if( status != 200){
			if(status == 401)
				this.forceDeleteToken();
			throw new RuntimeException("create qinjia chatroom error,response="+json);
		}
		JSONObject entity = (JSONObject)json.get("entity");
		return String.valueOf(entity.get("roomId"));
	}
	
	/*
	 * 删除聊天室
	 * 
	 * @param roomId
	 */

	@SuppressWarnings("unchecked")
	public void deleteChatRoom(String roomId) {
		JSONObject args = this.getJsonWithAppKey();
		
		args.put("roomId", roomId);
		
		JSONObject json = this.sendMessageForResponse(args, "/DelIMRoom");
		long status = (long)json.get("status");
		if(status != 200){
			if(status == 401)
				this.forceDeleteToken();
			throw new RuntimeException("delete qinjia chatroom error, response="+json);
		}
		
	}
	
	/*
	 * 创建群
	 * 
	 * @param groupName
	 * @param isPrivate
	 * @param ownerAccount
	 * @param needVerify
	 * @param groupInfo
	 * @return 新建群的ID
	 */

	@SuppressWarnings("unchecked")
	public String createGroup(String groupName, boolean isPrivate, String ownerAccount, boolean needVerify, String groupInfo) {
		JSONObject args = this.getJsonWithAppKey();
		
		args.put("groupName", groupName);
		args.put("groupHead", null);
		args.put("isPrivate", (isPrivate == true) ? 1 : 0);
		args.put("ownerAccount", ownerAccount);
		args.put("needVerify", (needVerify == true) ? 1 : 0);
		args.put("groupInfo", groupInfo);
		
		
		JSONObject json = this.sendMessageForResponse(args, "/CreateGroup");
		long status = (long)json.get("status");
		if(status != 200){
			if(status == 401)
				this.forceDeleteToken();
			throw new RuntimeException("create qinjia chatgroup error,response="+json);
		}
		JSONObject entity = (JSONObject)json.get("entity");
		return String.valueOf(entity.get("groupId"));
	}
	
	/*
	 * 修改群信息
	 * @param groupId
	 * @param groupName
	 * @param isPrivate
	 * @param ownerAccount
	 * @param needVerify
	 * @param groupInfo
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public void modifyGroup(String groupId, String groupName, boolean isPrivate, String ownerAccount, boolean needVerify, String groupInfo) {
		JSONObject args = this.getJsonWithAppKey();
		
		args.put("groupId", groupId);
		args.put("groupName", groupName);
		args.put("groupHead", null);
		args.put("isPrivate", (isPrivate == true) ? 1 : 0);
		args.put("ownerAccount", ownerAccount);
		args.put("needVerify", (needVerify == true) ? 1 : 0);
		args.put("groupInfo", groupInfo);
		
		JSONObject json = this.sendMessageForResponse(args, "/ModifyGroup");
		
		long status = (long)json.get("status");
		if(status != 200){
			if(status == 401)
				this.forceDeleteToken();
			throw new RuntimeException("modify qinjia chatgroup error,response="+json);
		}
	}
	
	/*
	 * 解散群
	 * 
	 * @param groupId
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public void dismissGroup(String groupId) {
		JSONObject args = this.getJsonWithAppKey();
		
		args.put("groupId", groupId);
		
		JSONObject json = this.sendMessageForResponse(args, "/DismissGroup");
				
		long status = (long)json.get("status");
		if(status != 200){
			if(status == 401)
				this.forceDeleteToken();
			throw new RuntimeException("dismiss qinjia chatgroup error,response="+json);
		}
	}
	
	/*
	 * 获取群成员列表
	 * 
	 * @param groupId
	 * @param index
	 * @param count
	 * @return 群成员的帐户列表
	 */

	@SuppressWarnings("unchecked")
	public ArrayList<String> getGroupMembers(String groupId, int index, int count) {
		ArrayList<String> membersAccount = new ArrayList<>();
		
		JSONObject args = this.getJsonWithAppKey();
		args.put("groupId", groupId);
		args.put("index", index);
		args.put("count", count);
		
		JSONObject json = this.sendMessageForResponse(args, "/GetGroupMembers");
		
		JSONArray array = (JSONArray) json.get("entities");
		
		long status = (long)json.get("status");
		if(status != 200){
			if(status == 401)
				this.forceDeleteToken();
			throw new RuntimeException("get qinjia chatgroup members account list error,response="+json);
		}
		Iterator<JSONObject> it = array.iterator();
		while(it.hasNext()) {
			JSONObject jobj = it.next();
			membersAccount.add(jobj.get("account").toString());
		}
		return membersAccount;
	}
	
	/*
	 * 添加群成员
	 * 
	 * @param groupId
	 * @param userAccount
	 */

	@SuppressWarnings("unchecked")
	public void addGroupMember(String groupId,String userAccount) {
		JSONObject args = this.getJsonWithAppKey();
		
		args.put("groupId", groupId);
		args.put("userAccount", userAccount);
		
		JSONObject json = this.sendMessageForResponse(args, "/AddGroupMember");
		
		long status = (long)json.get("status");
		if(status != 200){
			if(status == 401)
				this.forceDeleteToken();
			throw new RuntimeException("add qinjia chatgroup member,response="+json);
		}
	}
	
	/*
	 * 删除群成员
	 *
	 * @param groupId
	 * @param userAccount
	 */

	@SuppressWarnings("unchecked")
	public void delGroupMember(String groupId,String userAccount) {
		JSONObject args = this.getJsonWithAppKey();

		args.put("groupId", groupId);
		args.put("userAccount", userAccount);

		JSONObject json = this.sendMessageForResponse(args, "/DelGroupMember");

		long status = (long)json.get("status");
		if(status != 200){
			if(status == 401)
				this.forceDeleteToken();
			throw new RuntimeException("delete qinjia chatgroup member,response="+json);
		}
	}

	/*
	 * 获取指定用户所在群列表
	 *
	 * @param userAccount
	 * @param index
	 * @param count
	 * @return 群id列表
	 */

	@SuppressWarnings("unchecked")
	public List<String> getGroupsByUserAccount(String userAccount, int index, int count) {
		List<String> groupIds = new ArrayList<>();
		JSONObject args = this.getJsonWithAppKey();

		args.put("userAccount", userAccount);
		args.put("index", index);
		args.put("count", count);
		args.put("scope", 1);

		JSONObject json = this.sendMessageForResponse(args, "/GetGroups");

		JSONArray array = (JSONArray) json.get("entities");

		long status = (long)json.get("status");
		if(status != 200){
			if(status == 401)
				this.forceDeleteToken();
			throw new RuntimeException("get qinjia chatgroup list by useraccount error,response="+json);
		}

		Iterator<JSONObject> it = array.iterator();
		while(it.hasNext()) {
			JSONObject jobj = it.next();
			groupIds.add(jobj.get("groupId").toString());
		}
		return groupIds;
	}

	/*
	 * 获取所有群列表
	 *
	 * @param index
	 * @param count
	 * @return 群id列表
	 */

	@SuppressWarnings("unchecked")
	public List<String> getAllGroups(int index, int count) {
		JSONObject args = this.getJsonWithAppKey();

		args.put("index", index);
		args.put("count", count);
		args.put("scope", 2);

		JSONObject json = this.sendMessageForResponse(args, "/GetGroups");

		JSONArray array = (JSONArray) json.get("entities");

		long status = (long)json.get("status");
		if(status != 200){
			if(status == 401)
				this.forceDeleteToken();
			throw new RuntimeException("get qinjia chatgroup list error,response="+json);
		}

		List<String> groupIds = new ArrayList<>(array.size());
		Iterator<JSONObject> it = array.iterator();
		while(it.hasNext()){
			JSONObject jobj =  it.next();
			groupIds.add(jobj.get("groupId").toString());
		}
		return groupIds;
	}

 	/*
	 * 获取群详情
	 *
	 * @param groupId
	 * @return 包含群详细的map,key中包含groupId，groupName，groupHead，isPrivate(0或1)，ownerAccount，needVerify(0或1)，maxUserNumber，groupInfo
	 */

	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getGroupDetails(String groupId){
		JSONObject args = this.getJsonWithAppKey();

		args.put("groupId", groupId);

		JSONObject json = this.sendMessageForResponse(args, "/GetGroup");

		long status = (long)json.get("status");
		if(status != 200){
			if(status == 401)
				this.forceDeleteToken();
			throw new RuntimeException("get qinjia chatgroup detail error,response="+json);
		}

		JSONObject entity = (JSONObject)json.get("entity");

		HashMap<String, Object> groupDetails = new HashMap<>();
		groupDetails.put("groupId", entity.get("groupId"));
		groupDetails.put("groupName", entity.get("groupName"));
		groupDetails.put("groupHead", entity.get("groupHead"));
		groupDetails.put("isPrivate", entity.get("isPrivate"));
		groupDetails.put("ownerAccount", entity.get("ownerAccount"));
		groupDetails.put("needVerify", entity.get("needVerify"));
		groupDetails.put("maxUserNumber", entity.get("maxUserNumber"));
		groupDetails.put("groupInfo", entity.get("groupInfo"));

		return groupDetails;
	}

}
