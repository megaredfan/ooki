package org.kriver.core;

/**
 * 定义core的错误代码
 */
public class CoreErrors {
	/* 消息包错误定义开始 */
	/** 错误的消息头长度 */
	public static final String PACKET_BAD_HEADER_LEN = "PAC.ERR.HEAD.LEN";
	/* 消息包错误定义结束 */

	/* 线程错误定义开始 */
	/** 线程被中断 */
	public static final String THRAD_ERR_INTERRUPTED = "THR.ERR.INTRRUPTED";
	/** 不在同一个线程中调用 */
	public static final String THREAD_NOT_SAME = "THR.ERR.NOT.SMAE";
	/* 线程错误定义结束 */

	/* 消息处理错误定义开始 */
	/** 未知的消息 */
	public static final String MSG_UNKNOWN = "MSG.ERR.UNKNOWN";
	/** 消息处理时收到空的消息 */
	public static final String MSG_PRO_ERR_NULL_MSG = "MSG.PRO.ERR.NULL.MSG";
	/** 消息处理时发生未知的异常 */
	public static final String MSG_PRO_ERR_EXP = "MSG.PRO.ERR.EXP";
	/** 消息处理过程中,断掉Sender时失败 */
	public static final String MSG_PRO_ERR_DIS_SENDER_FAIL = "MSG.PRO.ERR.DIS.SENDER.FAIL";
	/** 读消息失败 */
	public static final String MSG_PRO_ERR_READ_FAIL = "MSG.PRO.ERR.READ.FAIL";
	/** 写消息失败 */
	public static final String MSG_PRO_ERR_WRITE_FAIL = "MSG.PRO.ERR.WRITE.FAIL";
	
	/* 消息处理错误定义结束 */

	/* 数据库处理错误定义开始 */
	/** ibatis 连接失败 */
	public static final String DB_IBATIS_CONNECT_FAIL = "DB.IBATIS.CONNECT.FAIL";
	/** 数据库操作失败 */
	public static final String DB_OPERATE_FAIL = "DB.ERR.OPR";
	/** 没有正确设置ID */
	public static final String DB_NO_ID = "DB.ERR.NOID";
	/* 数据库处理错误定义结束 */

	/** 脚本执行失败 */
	public static final String SCRITP_EXECUTE_FAIL = "SCRIPT.ERR.EXE.FAIL";

	/** 文件IO异常 */
	public static final String FILE_IO_FAIL = "FILE.ERR.IO.FAILE";

	/* 参数相关的错误定义 */
	/** 期望的参数是正数 */
	public static final String ARG_POSITIVE_NUMBER_EXCEPT = "ARG.ERR.POSITIVE.NUM.EXCEPT";
	/** 期望的参数不是null */
	public static final String ARG_NOT_NULL_EXCEPT = "ARG.ERR.NOT.NULL.EXCEPT";
	/** 参数无效 */
	public static final String ARG_INVALID = "ARG.ERR.INVALID";

	/** 服务器初始化失败 */
	public static final String SERVER_INIT_FAIL = "SERVER.ERR.INIT.FAIL";

	/** Excel中有重复的配置 */
	public static final String CONFIG_DUP_FAIL = "CONFIG.ERR.DUP";

	/** JsonUtils 处理数据失败 */
	public static final String JSON_ANALYZE_FAIL = "JSON.ERR.ANALYZE.FAIL";

	/* 属性相关的错误定义 */
	/** 只读属性,不应该被修改 */
	public static final String PROP_READONLY = "PROP.ERR.READONLY";

	/** 无推荐服务器 */
	public static final String NO_RECOMMEND_SERVER = "ERR.NO.RECOMMEND.SERVER";

	/* HTTP 协议相关错误定义 */
	/** HTTP消息解析错误 */
	public static final String HTTP_ERR_DECODABLE = "ERR.HTTP.DECODABLE";

	/** HTTP消息处理错误 */
	public static final String HTTP_ERR_DO_ACTION = "ERR.HTTP.DO.ACTION";

	/** 额外任务处理错误 */
	public static final String EXTRA_TASK_FAIL = "ERR.EXTRA.TASK";

	/** 性能采集状态错误 */
	public static final String PIPROBE_ERR_STATUS = "PIPROBE.ERR.STATUS";

	/** 服务器生命周期状态错误 */
	public static final String SERVER_ERR_LIFE_STATUS = "SERVER.ERR.LIFE.STATUS";

}
