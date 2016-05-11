package org.kriver.core;

import redis.clients.jedis.Jedis;

public class RedisTest {
	

	
	//@Test
	public void test(){
		Jedis jedis = new Jedis("localhost");  
        jedis.set("php", "http://java.androidwhy.com");  
        String value = jedis.get("java");
        System.out.println(value);  
		
	}
}
