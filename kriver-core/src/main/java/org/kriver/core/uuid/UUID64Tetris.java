package org.kriver.core.uuid;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 按分区/服/线实现的64位的UUID
 * 
 */
public class UUID64Tetris implements IUUID<Long>{
	/** 有效的位数 */
	private static final int MAX_BITS = 42;
	/** 大区ID的位数 */
	private final int ridBits;
	/** 服ID的位数 */
	private final int sidBits;
	/** 对象ID的位数 */
	private final int oidBits;
	/** 大区的ID */
	private final long rid;
	/** 服的ID */
	private final long sid;
	/** 大区ID的掩码 */
	private final long ridMask;
	/** 服ID的掩码 */
	private final long sidMask;
	/** oid的掩码 */
	private final long oidMask;
	/** 对象的ID */
	private final AtomicLong oid;
	/** 当前UUID所能表示的最大值 */
	private final long maxUUID;
	/** 当前UUID的最小值 */
	private final long minUUID;

	/**
	 * 
	 * @param ridBits
	 *            大区ID的bit位数,至少要1位
	 * @param sidBits
	 *            服ID的bit位数,至少要1位
	 * @param oidBits
	 *            对象ID的bit数,至少要1位,1位的id没啥用
	 * @param rid
	 * @param sid
	 * @param initOid
	 * @exception IllegalArgumentException
	 *                如果参数错误,会抛出此异常
	 */
	UUID64Tetris(int ridBits, int sidBits, int oidBits, long rid, long sid, long initOid) {
		if (ridBits < 1 || sidBits < 1 || oidBits < 1) {
			throw new IllegalArgumentException("The ridBits,sidBits,lidBits and the oidBits must be >= 1");
		}
		if (initOid < 0) {
			throw new IllegalArgumentException("The initOid must   must be >= 0 ");
		}

		checkBitsMaxNum(ridBits, rid);
		checkBitsMaxNum(sidBits, sid);
		checkBitsMaxNum(oidBits, initOid);

		this.ridBits = ridBits;
		this.sidBits = sidBits;
		this.oidBits = oidBits;

		this.rid = rid;
		this.sid = sid;

		int _leftShiftBits = MAX_BITS - ridBits;
		long _high = rid << _leftShiftBits;
		ridMask = getMaskBits(ridBits, _leftShiftBits);

		_leftShiftBits -= sidBits;
		_high = _high | (sid << _leftShiftBits);
		sidMask = getMaskBits(sidBits, _leftShiftBits);

		this.oidMask = getMaskBits(this.oidBits, 0);
		this.minUUID = _high;
		this.maxUUID = _high | this.oidMask;

		this.oid = new AtomicLong(_high | initOid);
		if (this.oid.get() >= this.maxUUID) {
			throw new IllegalArgumentException("The oid [" + this.oid.get() + "] has reach the maxUUID ["
					+ this.maxUUID + "]");
		}
	}

	/**
	 * 取得一个递增的UUID
	 * 
	 * @return
	 * @exception IllegalStateException
	 *                如果当前取得的UUID大于{@link #maxUUID},表示发生了溢出,会抛出此异常
	 */
	@Override
	public Long getNextUUID() {
		final long _curId = this.oid.incrementAndGet();
		if (_curId > this.maxUUID) {
			this.oid.set(this.maxUUID);
			throw new IllegalStateException("The uuid has been overflow,curId [" + _curId + "] maxUUID ["
					+ this.maxUUID + "]");
		}
		return _curId;
	}
	
	/**
	 * 取得当前的UUID
	 * @return
	 */
	@Override
	public Long getCurUUID(){
		final long _curId = this.oid.get();
		if (_curId > this.maxUUID) {
			this.oid.set(this.maxUUID);
			throw new IllegalStateException("The uuid has been overflow,curId [" + _curId + "] maxUUID ["
					+ this.maxUUID + "]");
		}
		return _curId;
	}

	/**
	 * @return the rid
	 */
	public long getRid() {
		return rid;
	}

	/**
	 * 获取指定UUID的大区ID
	 * 
	 * @param uuid
	 * @return
	 */
	public long getRid(final long uuid) {
		return (this.ridMask & uuid) >> (MAX_BITS - this.ridBits);
	}

	/**
	 * @return the sid
	 */
	public long getSid() {
		return sid;
	}

	/**
	 * 获取指定UUID的服ID
	 * 
	 * @param uuid
	 * @return
	 */
	public long getSid(final long uuid) {
		return (this.sidMask & uuid) >> (MAX_BITS - this.ridBits - this.sidBits);
	}

	/**
	 * 获取指定UUID的oid部分
	 * 
	 * @param uuid
	 * @return
	 */
	public long getOid(final long uuid) {
		return this.oidMask & uuid;
	}

	/**
	 * @return the maxUUID
	 */
	public long getMaxUUID() {
		return maxUUID;
	}

	/**
	 * @return the minUUID
	 */
	public long getMinUUID() {
		return minUUID;
	}
	
	/**
	 * 
	 * 构建一个系统默认的UUID64实例,5位rid(大区的ID),10位sid(服的ID),27位oid,在oid之前保留4位
	 * 
	 * @param rid
	 *            大区的ID
	 * @param sid
	 *            服的ID
	 * @param initOid
	 *            初始的对象id
	 * @return
	 */
	public static UUID64Tetris buildDefaultUUID(final int rid, final int sid, final long initOid) {
		return new UUID64Tetris(5, 10, 27, rid, sid, initOid);
	}

	/**
	 * 检查指定的value是否超出了bits可以表示的范围
	 * 
	 * @param bits
	 * @param value
	 */
	private void checkBitsMaxNum(int bits, long value) {
		long _bitMax = (1L << bits);
		if (value >= _bitMax) {
			throw new IllegalArgumentException("Can't represent value [" + value + "] with " + bits + " bits");
		}
	}

	/**
	 * 取得指定bits左移指定位数leftShiftBits后的最大值
	 * 
	 * @param bits
	 * @param leftShiftBits
	 * @return
	 * @exception IllegalArgumentException
	 */
	public static long getMaskBits(int bits, int leftShiftBits) {
		long _max = (1L << bits) - 1;
		if (_max <= 0) {
			throw new IllegalArgumentException("Bad bits [" + bits + "]");
		}
		return Long.MAX_VALUE & (_max << leftShiftBits);
	}
	
	public static void main(String[] args) {
//		UUID64Tetris uuid = UUID64Tetris.buildDefaultUUID(3, 5, 0);
//		System.out.println(uuid.getMaxUUID());
//		System.out.println(uuid.getMinUUID());
//		System.out.println(uuid.getOid(uuid.getMaxUUID()));
//		System.out.println(uuid.getOid(uuid.getMinUUID()));
//		System.out.println(uuid.getNextUUID());
//		System.out.println(uuid.getNextUUID());
//		System.out.println(uuid.getOid(uuid.getCurUUID()));
//		System.out.println(uuid.getRid(uuid.getCurUUID()));
//		System.out.println(uuid.getSid(uuid.getCurUUID()));
		
		System.out.println(Integer.MAX_VALUE/1024/1024);
		//System.out.println(Long.MAX_VALUE);
	}
	
}
