package com.sda.uk.javalon1.bart.projects.movies.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public UserService(
            @Autowired UserRepository userRepository,
            @Autowired ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

    }

    public Optional<String> saveUser(UserDTO userToSave) {

        if (userRepository.existsByUsername(userToSave.getUsername())) {
            return Optional.empty();
        }

        UserDocument userDocumentToSave = modelMapper.map(userToSave, UserDocument.class);
        UserDocument savedUserDocument = userRepository.save(userDocumentToSave);
        return Optional.of(savedUserDocument.getId());
    }

    public List<UserDocument> getAllUsers() {
        return userRepository.findAll();
    }
}
