package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MatchFootService {
    private final MatchFootRepository matchFootRepository;

    @Autowired
    public MatchFootService(MatchFootRepository matchFootRepository) {
        this.matchFootRepository = matchFootRepository;
    }

    public List<MatchFoot> getAll() { return matchFootRepository.findAll(); }
    public Optional<MatchFoot> getById(Long id) { return matchFootRepository.findById(id); }
    public MatchFoot save(MatchFoot match) { return matchFootRepository.save(match); }
    public void delete(Long id) { matchFootRepository.deleteById(id); }
} 