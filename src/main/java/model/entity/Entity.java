package model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;

    public Entity() {

    }

    public Entity(long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
