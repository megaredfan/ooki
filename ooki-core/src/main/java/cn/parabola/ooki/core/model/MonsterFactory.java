package cn.parabola.ooki.core.model;

import java.sql.Timestamp;

/**
 * Created by 熊纪元 on 2016/5/1.
 */
public class MonsterFactory {
    private Long factoryId;
    private int level;
    private int capacity;
    private double speed;
    private int position;
    private int status;
    private Timestamp nextMonsterTime;
    private long playerId;

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
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

    public Timestamp getNextMonsterTime() {
        return nextMonsterTime;
    }

    public void setNextMonsterTime(Timestamp nextMonsterTime) {
        this.nextMonsterTime = nextMonsterTime;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "MonsterFactory{" +
                "factoryId=" + factoryId +
                ", level=" + level +
                ", capacity=" + capacity +
                ", speed=" + speed +
                ", position=" + position +
                ", status=" + status +
                ", nextMonsterTime=" + nextMonsterTime +
                ", playerId=" + playerId +
                '}';
    }
}
