package cn.parabola.ooki.core.model;

/**
 * Created by 熊纪元 on 2016/5/1.
 */
public class Tent {
    private Long tentId;
    private int level;
    private int capacity;
    private double recoverIndex;
    private int position;
    private int status;
    private long playerId;

    public Long getTentId() {
        return tentId;
    }

    public void setTentId(Long tentId) {
        this.tentId = tentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getRecoverIndex() {
        return recoverIndex;
    }

    public void setRecoverIndex(double recoverIndex) {
        this.recoverIndex = recoverIndex;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "Tent{" +
                "tentId=" + tentId +
                ", level=" + level +
                ", capacity=" + capacity +
                ", recoverIndex=" + recoverIndex +
                ", position=" + position +
                ", status=" + status +
                ", playerId=" + playerId +
                '}';
    }
}
