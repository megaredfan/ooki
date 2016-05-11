package org.kriver.core.uuid;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 32位UUID
 *
 */
public class UUID32 implements IUUID<Integer>{
	/** 日志 */
	private static final Logger logger = Logger.getLogger(UUID32.class);
	/** 只适用31位，最高位不用 */
	private final static int MAX_BITS = 31;
	/** 服ID位数 */
	private final int sidBits;
	/** 类型位数 是游戏生成[报名]还是GM生成[战况] */
	private final int tidBits;
	/** 自增位的位数 */
	private final int oidBits;
	/** 服ID */
	private final int sid;
	/** 类型位 */
	private final int tid;
	/** 服ID掩码 */
	private final int sidMask;
	/** 类型位掩码 */
	private final int tidMask;
	/** 自增位掩码 */
	private final int oidMask;
	/** 自增值 */
	private final AtomicInteger oid;
	/** 自增值最小值 */
	private final int oidMin;
	/** 自增值最大值 */
	private final int oidMax;

	private UUID32(int sidBits,int tidBits,int oidBits,int sid,int tid,int OId) {
		this.sidBits = sidBits;
		this.tidBits = tidBits;
		this.oidBits = oidBits;
		this.sid = sid;
		this.tid = tid;
		this.checkBits();
		this.sidMask = (1 << sidBits) - 1;
		this.tidMask = (1 << tidBits) - 1;
		this.oidMask = (1 << oidBits) - 1;
		this.oidMin = (sid << (oidBits + tidBits)) | (tid << oidBits);
		this.oidMax = this.oidMin + ((1<< oidBits) - 1);
		this.oid = new AtomicInteger(OId + this.oidMin);
		fixOIdWhenStarted();
	}
	
	/**
	 * 检查位参数的有效性
	 */
	private void checkBits() {
		if(this.sidBits + this.tidBits + this.oidBits > MAX_BITS) {
			throw new IllegalArgumentException("too many bits for Integer UUID");
		}
	}
	/**
	 * 构建默认的服战UUID
	 * @param sid
	 * @param tid
	 * @param OId
	 * @return
	 */
	public static UUID32 buildDefaultFBUUID(int sid,int tid,int OId) {
		return new UUID32(10,2,19,sid,tid,OId);
	}
	
	@Override
	public Integer getNextUUID() {
		int _next = oid.incrementAndGet();
		if(_next > oidMax) {
			_next = roundOIdWhenOverflow();
		}
		return _next;
	}
	
	@Override
	public Integer getCurUUID() {
		return oid.get();
	}
	
	/**
	 * 取最小UUID
	 * @return
	 */
	public int getMinUUID() {
		return this.oidMin;
	}
	
	/**
	 * 取最大UUID
	 * @return
	 */
	public int getMaxUUID() {
		return this.oidMax;
	}
	
	/**
	 * 取Sid
	 * @return
	 */
	public int getSid() {
		return this.sid;
	}
	
	/**
	 * 取Tid
	 * @return
	 */
	public int getTid() {
		return this.tid;
	}
	
	/**
	 * 从Oid中剥出Sid
	 * @param oid
	 * @return
	 */
	public int getSid(int oid) {
		return (oid >> (this.oidBits + this.tidBits)) & sidMask;
	}
	
	/**
	 * 从Oid中剥出Tid
	 * @param oid
	 * @return
	 */
	public int getTid(int oid) {
		return (oid >> (this.oidBits)) & tidMask;
	}
	
	/**
	 * 从Oid中博取自增位
	 * @param oid
	 * @return
	 */
	public int getOid(int oid) {
		return oid & (oidMask);
	}
	
	/**
	 * 启动时折返
	 */
	private void fixOIdWhenStarted() {
		int _cur = this.oid.get();
		if(_cur < this.oidMin || _cur > this.oidMax) {
			this.roundOIdWhenOverflow();
		}
	}
	
	/**
	 * 当Id达到最大值时，折返为最小值
	 * @return
	 */
	private int roundOIdWhenOverflow() {
		if (logger.getLevel() == Level.WARN) {
			logger.error("UUID.roundOIdWhenOverflow" + this.oid.get());
		}
		this.oid.set(this.oidMin);
		return this.oid.get();
	}
	public static void main(String[] args) {
//		UUID32 uuid = UUID32.buildDefaultFBUUID(0, 0, 1);
//		System.out.println(uuid.getOid(uuid.getMaxUUID()));
//		System.out.println(uuid.getMaxUUID());
//		System.out.println(uuid.getMinUUID());
		System.out.println(Short.MAX_VALUE);
	}
}
