package cn.parabola.ooki.core;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {
	private final static Logger log = Logger.getLogger(RedisTest.class);
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	JedisPool jedisPool = context.getBean("jedisStoragePool", JedisPool.class);
	@Test
	public void test(){
		Jedis jedis = jedisPool.getResource();
		try{
			Assert.assertEquals("1",jedis.get("test"));
		}finally{
			jedis.close();
		}
	}
}
