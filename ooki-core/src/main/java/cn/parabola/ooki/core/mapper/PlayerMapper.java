package cn.parabola.ooki.core.mapper;

import cn.parabola.ooki.core.model.Player;

import java.util.List;

public interface PlayerMapper {
	int insert(Player player);
	int update(Player player);
	int delete(long playerId);
	Player select(long playerId);
	List<Player> selectAll();
	List<Player> selectByRange(int start, int length);
	int count();
}
