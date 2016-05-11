package cn.parabola.ooki.core.mapper;

import cn.parabola.ooki.core.model.MonsterFactory;

import java.util.List;

/**
 * Created by 熊纪元 on 2016/5/2.
 */
public interface MonsterFactoryMapper {
    int insert(MonsterFactory monsterFactory);
    int update(MonsterFactory monsterFactory);
    int delete(long factoryId);
    MonsterFactory select(long factoryId);
    List<MonsterFactory> selectAll();
    List<MonsterFactory> selectByRange(int start, int length);
    int count();
}
