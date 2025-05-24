package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StadeService {
    private final StadeRepository stadeRepository;

    @Autowired
    public StadeService(StadeRepository stadeRepository) {
        this.stadeRepository = stadeRepository;
    }

    public List<Stade> getAll() { return stadeRepository.findAll(); }
    public Optional<Stade> getById(Long id) { return stadeRepository.findById(id); }
    public Stade save(Stade stade) { return stadeRepository.save(stade); }
    public void delete(Long id) { stadeRepository.deleteById(id); }
} 