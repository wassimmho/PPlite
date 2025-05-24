package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<User> login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // BCrypt password check
            if (passwordEncoder.matches(password, user.getMotDePasse())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public User saveUser(User user) {
        System.out.println("[DEBUG] Tentative de création d'utilisateur : " + user.getEmail());
        // Vérifie si l'email existe déjà
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            System.out.println("[DEBUG] Email déjà utilisé : " + user.getEmail());
            throw new RuntimeException("Email déjà utilisé");
        }
        // Hash le mot de passe
        user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
        User saved = userRepository.save(user);
        System.out.println("[DEBUG] Utilisateur enregistré avec l'id : " + saved.getId());
        return saved;
    }

    public long countUsers() {
        return userRepository.count();
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
} 