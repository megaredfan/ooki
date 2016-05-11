package cn.parabola.ooki.core.mapper;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

/**
 * Created by 熊纪元 on 2016/5/2.
 */
public class GameServerMapperTest {
    private final static Logger log = Logger.getLogger(MonsterFactoryMapperTest.class);

    ApplicationContext context = new ClassPathXmlApplicationContext(
            "classpath*:application-config.xml");
    GameServerMapper gsm = context.getBean("gameServerMapper", GameServerMapper.class);

    //@Test
    public void test(){
        HashMap<String, Object> args = new HashMap<>();
        args.put("key", "key");
        args.put("value", "value");
        gsm.insertConfig(args);
        Assert.assertEquals("value", gsm.getConfig("key"));
        gsm.updateConfig(args);
    }
}
