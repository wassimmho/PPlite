package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SiegeService {
    private final SiegeRepository siegeRepository;

    @Autowired
    public SiegeService(SiegeRepository siegeRepository) {
        this.siegeRepository = siegeRepository;
    }

    public List<Siege> getAll() { return siegeRepository.findAll(); }
    public Optional<Siege> getById(Long id) { return siegeRepository.findById(id); }
    public Siege save(Siege siege) { return siegeRepository.save(siege); }
    public void delete(Long id) { siegeRepository.deleteById(id); }
} 