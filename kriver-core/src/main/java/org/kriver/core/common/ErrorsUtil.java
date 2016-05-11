package org.kriver.core.common;

public class ErrorsUtil {

	
	/**
	 * 构造一个标准格式的错误信息 [errorCode] [origin] [param]
	 * 例：[ITEM.ERR.NOEXIST] [#GS.ItemLogicalProcessor.onRepair] [bagId:1001,bagIndex:2]<br/>
	 * 常用键值列表如下，如果有新的请自己加上<br/>
	 * cid-角色Id cname-角色名 pid-passportId ip-ip地址  level-等级  reason-记录日志的原因<br/>
	 *        taskId-任务模板Id taskInstId-任务实例Id petId-宠物模板Id petInstId-宠物实例Id<br/>
	 *        itemId-物品模板Id itemInstId-物品实例Id skillId-技能Id parSkillId-父类技能<br/>
	 *        xinfaId-心法Id mapId-地图Id mapx-x坐标点 mapy-y坐标点 monsterId-怪物模板Id<br/>
	 *        npcId-npc编号 titleId-称号Id buffId-buff编号 nbbufId-战斗外buff编号<br/>
	 *        shopId-商店编号 repId-声望编号 raidId-副本编号 raidInstId-副本实例编号<br/>
	 *        exrecId-交易平台单号 gold-金币数 silver-银币数 moneyType-钱币类型 moneyValue-钱币值<br/>
	 *        wallowMin-防沉迷分钟数 playerSize-同步时间的玩家数量 secondSize-同步后时间容器的数量<br/>
	 *        exRecPrice-交易单价格 exRecCount-交易单金币数量 exRecType-交易单类型 exRecId-交易单编号<br/>
	 *        bodyGold-玩家身上的金币 bodySilver-玩家身上的银币 rpetId-骑宠模板ID rpetInstId-骑宠实例ID
	 *        exp-经验值 str-特殊字符串 points-玩家点数 leftpoint-剩余点数
	 *        guildId-公会编号
	 *        mmcost-充值金币
	 *        url-接口调用地址 urlret-接口调用返回结构
	 * 常用键值列表 {@link PMKey}, 如有新的请添加到PMKey
	 * @param param
	 *        需要记录实时数据<br/>
	 *        键值对用等号链接，分号分隔，对于列表值使用大括弧括起逗号分隔，例如key1={value11,value12,value13};key2=value2;key3=value3<br/>
	 *        建议使用@see {@link PM}(ParamterMap)类<br/>
	 * @param errorCode
	 *             错误代码 @see {@link CoreErrors}
	 * @param origin
	 *             错误产生地 #包缩写(GS,WS,LS,DBS,CORE,LOG).类名.方法名
	 * @author sd 2009-10-20       
	 * @return
	 */
	public static String error(String errorCode, String origin, String param) {
		StringBuilder _errorStr = new StringBuilder("[").append(errorCode).append("] [").append(origin).append("]");
		if(param != null && param.length() > 0) {
			_errorStr.append(" [").append(param).append("]");
		}
		return _errorStr.toString();
	}
}
