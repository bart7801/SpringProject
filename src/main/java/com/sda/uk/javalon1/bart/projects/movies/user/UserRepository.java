package com.sda.uk.javalon1.bart.projects.movies.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDocument, String> {

    boolean existsByUsername(String username);

    UserDocument getById(String id);
}