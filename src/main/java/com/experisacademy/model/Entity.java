package com.experisacademy.model;

public abstract class Entity {

    private long id;

    public Entity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
