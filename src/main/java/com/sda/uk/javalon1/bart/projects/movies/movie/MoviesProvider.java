package com.sda.uk.javalon1.bart.projects.movies.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MoviesProvider {

    private MoviePriceProvider moviePriceProvider;

    public MoviesProvider(@Autowired MoviePriceProvider moviePriceProvider) {
        this.moviePriceProvider = moviePriceProvider;
    }

    private static final String MOVIE_API_URL = "http://www.omdbapi.com/?apikey=b58d7dc5&s=";

    public List<Movie> getMovies(String searchQuery) {
        RestTemplate restTemplate = new RestTemplate();
        SearchMovieResponse forObject = restTemplate
                .getForObject(MOVIE_API_URL + searchQuery, SearchMovieResponse.class);

        List<Movie> search = forObject.getSearch();

        search.forEach(movie ->
                movie.setPrice(moviePriceProvider.getPrice(movie.getImdbID())));

        return search;
    }
}