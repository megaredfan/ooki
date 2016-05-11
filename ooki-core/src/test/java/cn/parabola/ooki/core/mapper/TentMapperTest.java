package cn.parabola.ooki.core.mapper;

import cn.parabola.ooki.core.model.Tent;
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
public class TentMapperTest {
    private static final Logger log = Logger.getLogger(TentMapper.class);
    ApplicationContext context = new ClassPathXmlApplicationContext(
            "classpath*:application-config.xml");

    TentMapper tm = context.getBean("tentMapper", TentMapper.class);
    long tentId = 1L;
    long playerId = 10104L;

    @After
    public void after(){
        Assert.assertEquals(1, tm.delete(tentId));
    }

    @Test
    public void test(){
        Tent t = new Tent();
        t.setTentId(tentId);
        t.setPlayerId(playerId);
        Assert.assertEquals(1, tm.insert(t));

        Tent tent = tm.select(tentId);
        Assert.assertEquals(playerId, tent.getPlayerId());

        tent.setCapacity(24);
        Assert.assertEquals(1, tm.update(tent));

        List<Tent> tents = tm.selectAll();

        int length = tents.size();

        List<Tent> anotherTents = tm.selectByRange(0, tm.count());

        Assert.assertEquals(length, anotherTents.size());
    }
}
