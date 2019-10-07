package com.sda.uk.javalon1.bart.projects.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoveDTO {
    @JsonProperty("name")
    private String name;
    private String url;
    public MoveDTO() {
    }
    public String getMoveName() {
        return name;
    }
    public void setMoveName(String moveName) {
        this.name = moveName;
    }
    public String getMoveUrl() {
        return url;
    }
    public void setMoveUrl(String moveUrl) {
        this.url = moveUrl;
    }
}

