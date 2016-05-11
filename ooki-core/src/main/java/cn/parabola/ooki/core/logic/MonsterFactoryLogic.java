package cn.parabola.ooki.core.logic;

import cn.parabola.ooki.core.mapper.MonsterFactoryMapper;
import cn.parabola.ooki.core.model.MonsterFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 熊纪元 on 2016/5/9.
 */
public class MonsterFactoryLogic {
    @Resource(name = "MonsterFactoryMapper")
    private MonsterFactoryMapper MonsterFactoryMapper;

    public int createMonsterFactory(MonsterFactory MonsterFactory) {
        return MonsterFactoryMapper.insert(MonsterFactory);
    }

    public int deleteMonsterFactory(Long MonsterFactoryId) {
        return MonsterFactoryMapper.delete(MonsterFactoryId);
    }

    public int updateMonsterFactory(MonsterFactory MonsterFactory) {
        return MonsterFactoryMapper.update(MonsterFactory);
    }

    public List<MonsterFactory> getAllMonsterFactorys() {
        return MonsterFactoryMapper.selectAll();
    }

    public MonsterFactory getMonsterFactoryById(Long MonsterFactoryId) {
        return MonsterFactoryMapper.select(MonsterFactoryId);
    }

    public List<MonsterFactory> getMonsterFactoryByRange(int start, int length) {
        return MonsterFactoryMapper.selectByRange(start, length);
    }

    public int count() {
        return MonsterFactoryMapper.count();
    }
}
