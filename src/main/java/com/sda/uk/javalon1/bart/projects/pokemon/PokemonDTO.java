package com.sda.uk.javalon1.bart.projects.pokemon;

import java.util.List;

public class PokemonDTO {
    private String name;
    private int height;
    private int weight;

    public List<PokemonMoveDTO> getMoves() {
        return moves;
    }

    private List<PokemonMoveDTO> moves;

    public PokemonDTO() {
    }

    public PokemonDTO(String name, int height, int weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}