package cn.parabola.ooki.core.mapper;

import cn.parabola.ooki.core.model.Tent;

import java.util.List;

/**
 * Created by 熊纪元 on 2016/5/2.
 */
public interface TentMapper {
    int insert(Tent tent);
    int update(Tent tent);
    int delete(long tentId);
    Tent select(long tentId);
    List<Tent> selectAll();
    List<Tent> selectByRange(int start, int length);
    int count();
}
