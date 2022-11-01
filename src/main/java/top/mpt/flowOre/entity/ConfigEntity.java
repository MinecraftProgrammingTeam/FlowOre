package top.mpt.flowOre.entity;

import java.util.Objects;
/**
 * @author : YouM
 */
public class ConfigEntity {
    private String key;
    private Double poll;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getPoll() {
        return poll;
    }

    public void setPoll(Double poll) {
        this.poll = poll;
    }

    public ConfigEntity(String key, Double poll) {
        this.key = key;
        this.poll = poll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigEntity that = (ConfigEntity) o;
        return Objects.equals(key, that.key) && Objects.equals(poll, that.poll);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, poll);
    }

    @Override
    public String toString() {
        return "ConfigEntity{" +
                "key='" + key + '\'' +
                ", poll=" + poll +
                '}';
    }
}
