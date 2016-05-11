package org.kriver.core.common;

import java.lang.management.ManagementFactory;


public class JMXUtil {
	/**
	 * 获得程序当前运行的进程ID
	 * 
	 * @return 取得失败时返回-1
	 */
	public static String getPid() {
		String _name = ManagementFactory.getRuntimeMXBean().getName();
		try {
			return _name.substring(0, _name.indexOf('@'));
		} catch (Exception e) {
			return "-1";
		}
	}
	
	public static void main(String[] args) {
		System.out.println("pid : " + JMXUtil.getPid());
	}
}
