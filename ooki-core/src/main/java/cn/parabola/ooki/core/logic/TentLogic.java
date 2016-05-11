package cn.parabola.ooki.core.logic;

import cn.parabola.ooki.core.model.Tent;
import cn.parabola.ooki.core.mapper.TentMapper;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 熊纪元 on 2016/5/9.
 */
public class TentLogic {
    @Resource(name = "TentMapper")
    private TentMapper TentMapper;

    public int createTent(Tent Tent) {
        return TentMapper.insert(Tent);
    }

    public int deleteTent(Long TentId) {
        return TentMapper.delete(TentId);
    }

    public int updateTent(Tent Tent) {
        return TentMapper.update(Tent);
    }

    public List<Tent> getAllTents() {
        return TentMapper.selectAll();
    }

    public Tent getTentById(Long TentId) {
        return TentMapper.select(TentId);
    }

    public List<Tent> getTentByRange(int start, int length) {
        return TentMapper.selectByRange(start, length);
    }

    public int count() {
        return TentMapper.count();
    }
}