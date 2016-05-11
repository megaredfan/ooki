package org.kriver.core.common;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
/**
 * aes加密算法
 * @author bear
 *
 */
public class SecretManager {
	private final static Logger logger = Logger.getLogger(SecretManager.class);
	private SecretKeySpec key = null;
	private Cipher encodeCipher = null;
	private Cipher decodeCipher = null;

	/**
	 * 初始化一个16位的秘钥
	 * 
	 * @param privateKey
	 * @throws FishError
	 */
	public SecretManager(String privateKey){
		if (privateKey == null || privateKey.length() < 16) {
			throw new IllegalArgumentException("privateKey's length must be 16,privateKey="+privateKey);
		}
		byte[] kb = privateKey.substring(0, 16).getBytes();
		key = new SecretKeySpec(kb, "AES");
		try {
			encodeCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			encodeCipher.init(Cipher.ENCRYPT_MODE, key);
			decodeCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			decodeCipher.init(Cipher.DECRYPT_MODE, key);
		} catch (Exception e) {
			logger.error("init error",e);
		}
	}

	public String encode(String plain){
		String v = StringUtils.EMPTY;
		try {
			byte[] result = encodeCipher.doFinal(plain.getBytes());
			v =  Base64.getEncoder().encodeToString(result);
		} catch (Exception e) {
			logger.error("encode error", e);
		}
		return v;
	}

	public String decode(String chiper){
		String v = StringUtils.EMPTY;
		try {
			byte[] c = Base64.getDecoder().decode(chiper);
			byte[] result = decodeCipher.doFinal(c);
			v =  new String(result);
		} catch (Exception e) {
			logger.error("decode error", e);
		}
		return v;
	}
}
