package cn.parabola.ooki.core.model;

/**
 * Created by litGame on 2016/5/11.
 */
public class MonsterEgg {
    private Long eggId;
    private int rareLevel;

    public Long getEggId() {
        return eggId;
    }

    public void setEggId(Long eggId) {
        this.eggId = eggId;
    }

    public int getRareLevel() {
        return rareLevel;
    }

    public void setRareLevel(int rareLevel) {
        this.rareLevel = rareLevel;
    }

    @Override
    public String toString() {
        return "MonsterEgg{" +
                "eggId=" + eggId +
                ", rareLevel=" + rareLevel +
                '}';
    }
}
