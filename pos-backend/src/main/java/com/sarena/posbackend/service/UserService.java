package com.sarena.posbackend.service;

import com.sarena.posbackend.entity.User;
import com.sarena.posbackend.repository.UserRepository;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
