package com.experisacademy.dao;

import com.experisacademy.model.Track;

import java.util.ArrayList;

public interface TrackDao {

    ArrayList<String> select(String table, int limit);

    Track selectTrack(String trackName);

}
