package com.sda.uk.javalon1.bart.projects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.sda.uk.javalon1.bart.projects.movies",
        "com.sda.uk.javalon1.bart.projects.pokemon"})

public class Projects {

    public static void main(String[] args) {
        SpringApplication.run(Projects.class, args);
    }

}
