package com.experisacademy.controller;

import com.experisacademy.model.Track;
import com.experisacademy.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
@RequestMapping("/api/v1/tracks")
public class ViewController {

    private static final String FIRST_TABLE = "Artist";
    private static final String SECOND_TABLE = "Track";
    private static final String THIRD_TABLE = "Genre";
    private static final int LIMIT = 6;

    private TrackService trackService;

    @Autowired
    public ViewController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("artists", trackService.getArtistNames(FIRST_TABLE, LIMIT));
        model.addAttribute("songs", trackService.getSongNames(SECOND_TABLE, LIMIT));
        model.addAttribute("genres", trackService.getGenreNames(THIRD_TABLE, LIMIT));

        return "home";
    }

    @GetMapping("/results")
    public String displayResults(Model model, @RequestParam String name) {
        name = capitalizeEachWord(name);
        model.addAttribute("name", trackService.getTrack(name));
        return "results";
    }

    private String capitalizeEachWord(String sentence) {
        String formattedName = "";
        String words[] = sentence.trim().replaceAll(" +", " ").split(" ");
        for(String word : words) {
            formattedName += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";
        }
        return formattedName.trim();
    }


}
