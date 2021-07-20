package com.experisacademy.model;

public class CustomerGenre extends Customer {
    private String genre;

    public CustomerGenre(long id, String firstName, String lastName, String country,
                         int postalCode, String phoneNumber, String email,String genre){
        super(id,firstName,lastName,country,postalCode,phoneNumber,email);
        this.genre = genre;


    }

    public String getGenre() {
        return genre;
    }

}
