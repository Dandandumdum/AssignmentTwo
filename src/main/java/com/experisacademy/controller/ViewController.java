package com.experisacademy.controller;

import com.experisacademy.model.Track;
import com.experisacademy.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/tracks")
public class ViewController {

    private static final String FIRST_TABLE = "Artist";
    private static final String SECOND_TABLE = "Track";
    private static final String THIRD_TABLE = "Genre";
    private static final int LIMIT = 5;

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

        model.addAttribute("greeting", "Welcome to the past with Thymeleaf");
        return "home";
    }

    @GetMapping("/results")
    public String displayResults(Model model, @RequestParam String name) {
        model.addAttribute("name", trackService.getTrack(name));
        return "results";
    }


}
