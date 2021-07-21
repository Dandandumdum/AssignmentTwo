package com.experisacademy.dao;

import java.util.ArrayList;

public interface TrackDao {

    ArrayList<String> select(String table, int limit);

    ArrayList<String> selectTrack(String trackName);

}
