package cn.parabola.ooki.core.mapper;

import cn.parabola.ooki.core.model.MonsterEgg;

import java.util.List;

/**
 * Created by litGame on 2016/5/13.
 */
public interface MonsterEggMapper {
    int insert(MonsterEgg monsterEgg);
    int update(MonsterEgg monsterEgg);
    int delete(long eggId);
    MonsterEgg select(long eggId);
    List<MonsterEgg> selectAll();
    List<MonsterEgg> selectByRange(int start, int length);
    int count();
}
