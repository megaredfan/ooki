package cn.parabola.ooki.core.mapper;

import cn.parabola.ooki.core.model.MonsterFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 熊纪元 on 2016/5/2.
 */
public class MonsterFactoryMapperTest {
    private final static Logger log = Logger.getLogger(MonsterFactoryMapperTest.class);

    ApplicationContext context = new ClassPathXmlApplicationContext(
            "classpath*:application-config.xml");

    MonsterFactoryMapper mm = context.getBean("monsterFactoryMapper", MonsterFactoryMapper.class);
    long factoryId = 1L;
    long playerId = 10104L;

    @After
    public void after() {
        Assert.assertEquals(1, mm.delete(factoryId));
    }
    @Test
    public void test() {
        MonsterFactory mf = new MonsterFactory();
        mf.setFactoryId(factoryId);
        mf.setPlayerId(playerId);
        Assert.assertEquals(1, mm.insert(mf));

        MonsterFactory monsterFactory = mm.select(factoryId);
        Assert.assertEquals(playerId, monsterFactory.getPlayerId());

        monsterFactory.setCapacity(1000);
        monsterFactory.setNextMonsterTime(new Timestamp(System.currentTimeMillis()+1000*60));
        Assert.assertEquals(1, mm.update(monsterFactory));

        List<MonsterFactory> monsterFactories = mm.selectAll();

        int length = monsterFactories.size();

        List<MonsterFactory> anotherFactories = mm.selectByRange(0, mm.count());

        Assert.assertEquals(length, anotherFactories.size());
    }
}
