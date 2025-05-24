package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow all origins for now
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void logStartup() {
        System.out.println("[INFO] UserController loaded and /api/login endpoint is active.");
    }

    @GetMapping
    public ResponseEntity<String> apiRoot() {
        return ResponseEntity.ok("PPlite API is running");
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.login(loginRequest.getEmail(), loginRequest.getMotDePasse());
        return userOptional.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    // Endpoint to create a new user (for testing purposes)
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Email déjà utilisé")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> testApi() {
        return ResponseEntity.ok("/api/test is working");
    }

    @GetMapping("/dbtest")
    public ResponseEntity<String> dbTest() {
        try {
            long count = userService.countUsers();
            return ResponseEntity.ok("Connexion OK, utilisateurs en base: " + count);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur DB: " + e.getMessage());
        }
    }

    @PostMapping("/utilisateurs")
    public ResponseEntity<?> inscription(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Email déjà utilisé")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/utilisateurs/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        Optional<User> user = userService.getById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
} 