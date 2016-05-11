package cn.parabola.ooki.core.mapper;

import cn.parabola.ooki.core.model.Player;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.List;

public class PlayerMapperTest {
	private final static Logger log = Logger.getLogger(PlayerMapperTest.class);
	
	ApplicationContext context = new ClassPathXmlApplicationContext(  
            "classpath*:application-config.xml"); 
	PlayerMapper pm = context.getBean(PlayerMapper.class);

	private long playerId = 10104L;

	@After
	public void after(){
		Assert.assertEquals(1, pm.delete(playerId));
	}
	
	@Test
	public void test(){
		
		Player player = new Player();
		player.setPlayerId(playerId);
		player.setPlayerName("player");
		
		player.setCreateTime(new Timestamp(System.currentTimeMillis()));
		Assert.assertEquals(1, pm.insert(player));

		Player p = pm.select(playerId);
		Assert.assertEquals("player", p.getPlayerName());
		Assert.assertEquals(playerId, (long) p.getPlayerId());


		p.setFeed(12);
		Assert.assertEquals(1, pm.update(p));
		List<Player> players = pm.selectAll();
		
		int length = players.size();
		
		List<Player> anotherPlayers = pm.selectByRange(0, pm.count());
		
		Assert.assertEquals(length, anotherPlayers.size());
		
	}
}
