package cn.parabola.ooki.core.mapper;

import cn.parabola.ooki.core.model.Account;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 熊纪元 on 2016/5/2.
 */
public class AccountMapperTest {
    private final static Logger log = Logger.getLogger(MonsterFactoryMapperTest.class);

    ApplicationContext context = new ClassPathXmlApplicationContext(
            "classpath*:application-config.xml");
    AccountMapper am = context.getBean("accountMapper", AccountMapper.class);

    int id;
    long playerId = 10104L;

    @After
    public void after(){
        Assert.assertEquals(1, am.delAccount(id));
    }

    @Test
    public void test(){
        Account a = new Account("account", 1, playerId);
        Assert.assertEquals(1, am.addAccount(a));
        id = am.getAccount("account", 1).getId();
    }
}
