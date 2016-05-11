package cn.parabola.ooki.core.mapper;

import cn.parabola.ooki.core.model.Monster;

import java.util.List;

/**
 * Created by 熊纪元 on 2016/5/2.
 */
public interface MonsterMapper {
    int insert(Monster monster);
    int update(Monster monster);
    int delete(long monsterId);
    Monster select(long monsterId);
    List<Monster> selectAll();
    List<Monster> selectByRange(int start, int length);
    int count();
}
