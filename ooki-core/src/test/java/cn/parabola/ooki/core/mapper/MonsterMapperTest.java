package cn.parabola.ooki.core.mapper;

import cn.parabola.ooki.core.model.Monster;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by 熊纪元 on 2016/5/2.
 */
public class MonsterMapperTest {
    private final static Logger log = Logger.getLogger(MonsterMapperTest.class);

    ApplicationContext context = new ClassPathXmlApplicationContext(
            "classpath*:application-config.xml");

    MonsterMapper mm = context.getBean("monsterMapper", MonsterMapper.class);
    long monsterId = 1L;
    long playerId = 10104L;

    @After
    public void after() {
        Assert.assertEquals(1, mm.delete(monsterId));
    }
    @Test
    public void test() {
        Monster m = new Monster();
        m.setMonsterId(monsterId);
        m.setPlayerId(playerId);
        m.setName("monster");
        Assert.assertEquals(1, mm.insert(m));

        Monster monster = mm.select(monsterId);
        Assert.assertEquals(playerId, monster.getPlayerId());
        Assert.assertEquals("monster", monster.getName());

        monster.setExp(1000);
        Assert.assertEquals(1, mm.update(monster));

        List<Monster> monsters = mm.selectAll();

        int length = monsters.size();

        List<Monster> anotherMonsters = mm.selectByRange(0, mm.count());

        Assert.assertEquals(length, anotherMonsters.size());
    }
}
