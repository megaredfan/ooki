package cn.parabola.ooki.core.logic;

import cn.parabola.ooki.core.mapper.MonsterEggMapper;
import cn.parabola.ooki.core.model.MonsterEgg;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by litGame on 2016/5/13.
 */
public class MonsterEggLogic {
    @Resource(name = "monsterEggMapper")
    private MonsterEggMapper monsterEggMapper;

    public int createMonsterEgg(MonsterEgg monsterEgg) {
        return monsterEggMapper.insert(monsterEgg);
    }

    public int deleteMonsterEgg(Long monsterEggId) {
        return monsterEggMapper.delete(monsterEggId);
    }

    public int updateMonsterEgg(MonsterEgg monsterEgg) {
        return monsterEggMapper.update(monsterEgg);
    }

    public List<MonsterEgg> getAllMonsterEggs() {
        return monsterEggMapper.selectAll();
    }

    public MonsterEgg getMonsterEggById(Long monsterEggId) {
        return monsterEggMapper.select(monsterEggId);
    }

    public List<MonsterEgg> getMonsterEggByRange(int start, int length) {
        return monsterEggMapper.selectByRange(start, length);
    }

    public int count() {
        return monsterEggMapper.count();
    }
}
