package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {
    private final PaiementRepository paiementRepository;

    @Autowired
    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    public List<Paiement> getAll() { return paiementRepository.findAll(); }
    public Optional<Paiement> getById(Long id) { return paiementRepository.findById(id); }
    public Paiement save(Paiement paiement) { return paiementRepository.save(paiement); }
    public void delete(Long id) { paiementRepository.deleteById(id); }
} 