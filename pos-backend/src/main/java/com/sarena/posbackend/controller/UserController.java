package com.sarena.posbackend.controller;

import com.sarena.posbackend.entity.User;
import com.sarena.posbackend.security.JwtUtil;
import com.sarena.posbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    // register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            System.out.println("REGISTER HIT: " + user.getUsername());
            User savedUser = userService.register(user.getUsername(), user.getPassword());
            return ResponseEntity.ok(savedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        boolean success = userService.login(user.getUsername(), user.getPassword());
        if (success) {
            String token = JwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me() {
        String username = (String)
                Objects.requireNonNull(SecurityContextHolder.getContext()
                                .getAuthentication())
                        .getPrincipal();

        return ResponseEntity.ok(username);
    }

}