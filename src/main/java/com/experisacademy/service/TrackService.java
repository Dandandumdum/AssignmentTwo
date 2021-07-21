package com.experisacademy.service;

import com.experisacademy.dao.TrackDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TrackService {

    private TrackDao trackDao;

    @Autowired
    public TrackService(TrackDao trackDao) {
        this.trackDao = trackDao;
    }

    public ArrayList<String> getArtistNames(String table, int limit) {
        if(!(table.equalsIgnoreCase("Artist")))
            throw new IllegalArgumentException("Must specify a correct table name");
        return trackDao.select(table, limit);
    }

    public ArrayList<String> getSongNames(String table, int limit) {
        if(!(table.equalsIgnoreCase("Track")))
            throw new IllegalArgumentException("Must specify a correct table name");
        return trackDao.select(table, limit);
    }

    public ArrayList<String> getGenreNames(String table, int limit) {
        if(!(table.equalsIgnoreCase("Genre")))
            throw new IllegalArgumentException("Must specify a correct table name");
        return trackDao.select(table, limit);
    }
    public ArrayList<String> searchMusic(String song){
        return trackDao.selectTrack(song);
    }

}
