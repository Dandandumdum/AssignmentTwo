package com.experisacademy.model;

public class CustomerSpender extends Entity {

    private int spent;
    private String firstName;

    public CustomerSpender(long id, int spent) {
        super(id);
        this.spent = spent;
    }

    public int getSpent() {
        return spent;
    }
}
