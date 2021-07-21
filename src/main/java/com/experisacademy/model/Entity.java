package com.experisacademy.model;

public abstract class Entity {

    private long id;

    public Entity(long id) {
        this.id = id;
    }

    public Entity() {

    }

    public long getId() {
        return id;
    }
}
