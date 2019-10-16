package com.sda.uk.javalon1.bart.projects.movies.order;

import com.sda.uk.javalon1.bart.projects.movies.movie.Movie;
import com.sda.uk.javalon1.bart.projects.movies.movie.MoviesProvider;
import com.sda.uk.javalon1.bart.projects.movies.movie.SearchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {

    private static final String CHAR_SESSION_LIST = "charSessionList";
    private static final String LAST_SEARCH_QUERY = "lastSearchQuery";

    private final MoviesProvider moviesProvider;

    public OrderController(@Autowired MoviesProvider moviesProvider) {
        this.moviesProvider = moviesProvider;
    }

    private List<String> getMovieListFromSessionOrEmpty(HttpSession session) {
        List<String> movieList = (List<String>) session.getAttribute(CHAR_SESSION_LIST);
        if (movieList == null) {
            return new ArrayList<>();

        }
        return movieList;
    }

    @PostMapping
    @RequestMapping("/addMovieToChart/{movieId}")
    public String addMovieToChart(
            @PathVariable("movieId") String movieId,
            HttpSession httpSession,
            Model model) {

        //dołożenie nowego ID filmu do sesji
        List<String> movieListFromSessionOrEmpty = getMovieListFromSessionOrEmpty(httpSession);
        movieListFromSessionOrEmpty.add(movieId);
        httpSession.setAttribute(CHAR_SESSION_LIST, movieListFromSessionOrEmpty);

        //wyszukanie filmów na podstawie ostatniej fazy wyszukania
        String lastSearchQuery = (String) httpSession.getAttribute(LAST_SEARCH_QUERY);
        List<Movie> movies = moviesProvider.getMovies(lastSearchQuery);

        //ustawienie modułu
        model.addAttribute("movies", movies);
        model.addAttribute("searchQuery", new SearchQuery(lastSearchQuery));

        return "showMoviesPage";
    }

    @RequestMapping("/userChartPage")
    public String userChartPage(HttpSession httpSession, Model model) {

        List<String> movieIds = getMovieListFromSessionOrEmpty(httpSession);
        List<Movie> movies = movieIds.stream()
                .map(moviesProvider::getMovieById)
                .peek(movie -> movie.setPrice(10d))
                .collect(Collectors.toList());

        double sum = movies.stream().mapToDouble(Movie::getPrice).sum();

        model.addAttribute("movies", movies);
        model.addAttribute("sum", sum);
        return "userChartPage";

    }
}
