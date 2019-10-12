package com.sda.uk.javalon1.bart.projects.movies.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String saveUser(UserDTO userToSave) {
        //TODO sprawdz czy uzytkownik z takim loginem juz istnieje
        UserDocument userDocumentToSave = modelMapper.map(userToSave, UserDocument.class);
        UserDocument savedUserDocument = userRepository.save(userDocumentToSave);
        return savedUserDocument.getId();
    }
}
