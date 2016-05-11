package cn.parabola.ooki.core.logic;

import cn.parabola.ooki.core.mapper.MonsterMapper;
import cn.parabola.ooki.core.model.Monster;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 熊纪元 on 2016/5/9.
 */
public class MonsterLogic {
    @Resource(name = "monsterMapper")
    private MonsterMapper monsterMapper;

    public int createMonster(Monster monster) {
        return monsterMapper.insert(monster);
    }

    public int deleteMonster(Long monsterId) {
        return monsterMapper.delete(monsterId);
    }

    public int updateMonster(Monster monster) {
        return monsterMapper.update(monster);
    }

    public List<Monster> getAllMonsters() {
        return monsterMapper.selectAll();
    }

    public Monster getMonsterById(Long monsterId) {
        return monsterMapper.select(monsterId);
    }

    public List<Monster> getMonsterByRange(int start, int length) {
        return monsterMapper.selectByRange(start, length);
    }

    public int count() {
        return monsterMapper.count();
    }
}
