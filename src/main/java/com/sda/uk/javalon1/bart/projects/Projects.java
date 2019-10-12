package com.sda.uk.javalon1.bart.projects;

import com.sda.uk.javalon1.bart.projects.movies.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.sda.uk.javalon1.bart.projects.movies",
        "com.sda.uk.javalon1.bart.projects.pokemon"})

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class Projects {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(Projects.class, args);
    }

}
