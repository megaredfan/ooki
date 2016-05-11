package cn.parabola.ooki.core.model;

/**
 * Created by 熊纪元 on 2016/5/1.
 */
public class Monster {
    private Long monsterId;
    private String name;
    private int type;
    private int level;
    private int exp;
    private boolean active;
    private int hungry;
    private int intimacy;
    private int status;
    private int actionPoint;
    private long playerId;

    public Long getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(Long monsterId) {
        this.monsterId = monsterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getHungry() {
        return hungry;
    }

    public void setHungry(int hungry) {
        this.hungry = hungry;
    }

    public int getIntimacy() {
        return intimacy;
    }

    public void setIntimacy(int intimacy) {
        this.intimacy = intimacy;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getActionPoint() {
        return actionPoint;
    }

    public void setActionPoint(int actionPoint) {
        this.actionPoint = actionPoint;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "monsterId=" + monsterId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", level=" + level +
                ", exp=" + exp +
                ", active=" + active +
                ", hungry=" + hungry +
                ", intimacy=" + intimacy +
                ", status=" + status +
                ", actionPoint=" + actionPoint +
                ", playerId=" + playerId +
                '}';
    }
}
