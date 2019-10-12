package com.sda.uk.javalon1.bart.projects.movies.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MoviesRestController {

    private final MoviesProvider moviesProvider;

    public MoviesRestController(@Autowired MoviesProvider moviesProvider) {
        this.moviesProvider = moviesProvider;
    }

    @RequestMapping("/movie")
    public List<Movie> searchMovie() {
        String searchWord = "Star";
        return moviesProvider.getMovies(searchWord);

    }

}