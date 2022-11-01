package top.mpt.mixingstone.entity;

import net.minecraft.block.Block;

import java.util.Objects;
import java.util.Random;

public class PrizeEntity {
    private Block key;
    private Double poll;

    public PrizeEntity() {
    }

    public PrizeEntity(Block key, Double poll) {
        this.key = key;
        this.poll = poll;
    }

    public Block getKey() {
        return key;
    }

    public void setKey(Block key) {
        this.key = key;
    }

    public Double getPoll() {
        return poll;
    }

    public void setPoll(Double poll) {
        this.poll = poll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrizeEntity that = (PrizeEntity) o;
        return Objects.equals(key, that.key) && Objects.equals(poll, that.poll);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, poll);
    }

    @Override
    public String toString() {
        return "PrizeEntity{" +
                "key=" + key +
                ", poll=" + poll +
                '}';
    }

    public static Random Rand = new Random();
}
