package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EquipeService {
    private final EquipeRepository equipeRepository;

    @Autowired
    public EquipeService(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    public List<Equipe> getAll() { return equipeRepository.findAll(); }
    public Optional<Equipe> getById(Long id) { return equipeRepository.findById(id); }
    public Equipe save(Equipe equipe) { return equipeRepository.save(equipe); }
    public void delete(Long id) { equipeRepository.deleteById(id); }
    public boolean existsByNomEquipe(String nom) { return equipeRepository.existsByNomEquipe(nom); }
} 