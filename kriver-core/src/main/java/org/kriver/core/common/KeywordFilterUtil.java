package org.kriver.core.common;

import java.util.List;

/**
 * 过虑词操作
 * 
 * @author wangshaojun
 * 
 * @version 0.00 2014-04-18 add
 * 
 */


public class KeywordFilterUtil {

	/**
	 * 仅判断roleName中是否有关键字
	 */
	public static boolean isContentKeyWords(List<String> keywords,String roleName) {
		for (int i = 0; i < keywords.size(); i++) {
			String key = keywords.get(i).trim();
			if(roleName.indexOf(key) > -1){
				return true;
			}
		}
		return false;
	}
}
