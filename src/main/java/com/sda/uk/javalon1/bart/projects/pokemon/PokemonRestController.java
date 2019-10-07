package com.sda.uk.javalon1.bart.projects.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class PokemonRestController {

    private PokemonProvider pokemonProvider;

    public PokemonRestController(@Autowired PokemonProvider pokemonProvider) {
        this.pokemonProvider = pokemonProvider;
    }

    @GetMapping
    @RequestMapping("/getRandomPokemon")
    public PokemonDTO providerPokemon() {

        int randomId = new Random().nextInt(807) + 1;
        return pokemonProvider.getPokemon(randomId);
    }

    @GetMapping
    @RequestMapping("/pokemon/{id}")
    public PokemonDTO getPokemonById(
            @PathVariable("id") int id) {
        return pokemonProvider.getPokemon(id);

    }

    @GetMapping
    @RequestMapping("/pokemon")
    public PokemonDTO getPokemonByIdRequestParam(
            @RequestParam int id) {
        return pokemonProvider.getPokemon(id);

    }
}