package com.experisacademy.model;

import java.util.ArrayList;

public class CustomerGenre extends Entity {
    private String firstName, lastName;
    private String genre;

    public CustomerGenre(long id, String firstName, String lastName, String genre) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.genre = genre;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGenre() {
        return genre;
    }

}
