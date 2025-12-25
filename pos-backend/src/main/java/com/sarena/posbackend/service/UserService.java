package com.sarena.posbackend.service;

import com.sarena.posbackend.entity.User;
import com.sarena.posbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String username, String rawPass) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        // hash password
        String hashedPassword = passwordEncoder.encode(rawPass);
        User user = new User(username, hashedPassword);
        return userRepository.save(user);
    }

    public boolean login(String username, String rawPass) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if(userOpt.isEmpty()) {
            return false;
        }
        User user = userOpt.get();
        return passwordEncoder.matches(rawPass, user.getPassword());
    }
    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
