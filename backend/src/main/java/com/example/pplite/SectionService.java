package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public List<Section> getAll() { return sectionRepository.findAll(); }
    public Optional<Section> getById(Long id) { return sectionRepository.findById(id); }
    public Section save(Section section) { return sectionRepository.save(section); }
    public void delete(Long id) { sectionRepository.deleteById(id); }
} 