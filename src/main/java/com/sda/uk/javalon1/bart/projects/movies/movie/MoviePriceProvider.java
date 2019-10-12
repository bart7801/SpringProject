package com.sda.uk.javalon1.bart.projects.movies.movie;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MoviePriceProvider {

    public double getPrice(String movieId) {
        return new Random().nextDouble() * 100;
    }

}