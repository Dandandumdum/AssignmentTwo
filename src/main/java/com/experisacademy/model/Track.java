package com.experisacademy.model;

public class Track {

    private String name, artist, album, genre;

    public Track(String name, String artist, String album, String genre) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }
}
