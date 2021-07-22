package com.experisacademy.model;

import java.util.ArrayList;

public class CustomerGenre extends Entity {
    private String firstName, lastName;
    private ArrayList<String> genres = new ArrayList<>();

    public CustomerGenre(long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<String> getGenre() {
        return genres;
    }

    public void addGenre(String genre) {
        genres.add(genre);
    }

}
