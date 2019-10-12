package com.sda.uk.javalon1.bart.projects.movies.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MovieController {
    private final MoviesProvider moviesProvider;

    public MovieController(@Autowired MoviesProvider moviesProvider) {
        this.moviesProvider = moviesProvider;
    }

    @GetMapping
    @RequestMapping("/showMoviesPage")
    public String showMoviesPage(Model model) {
        String searchQuery = "Star";
        List<Movie> movies = moviesProvider.getMovies(searchQuery);
        model.addAttribute("movies", movies);
        model.addAttribute("searchQuery", new SearchQuery());
        return "showMoviesPage";
    }

    @PostMapping
    @RequestMapping("/searchMoviesAction")
    public String searchMovies(@ModelAttribute SearchQuery searchQuery, Model model) {
        List<Movie> movies = moviesProvider.getMovies(searchQuery.getQuery());
        model.addAttribute("movies", movies);
        return "showMoviesPage";
    }


}