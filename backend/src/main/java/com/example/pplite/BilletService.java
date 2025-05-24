package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BilletService {
    private final BilletRepository billetRepository;

    @Autowired
    public BilletService(BilletRepository billetRepository) {
        this.billetRepository = billetRepository;
    }

    public List<Billet> getAll() { return billetRepository.findAll(); }
    public Optional<Billet> getById(Long id) { return billetRepository.findById(id); }
    public Billet save(Billet billet) { return billetRepository.save(billet); }
    public void delete(Long id) { billetRepository.deleteById(id); }
} 