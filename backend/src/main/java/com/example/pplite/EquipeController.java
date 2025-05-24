package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipes")
@CrossOrigin(origins = "*")
public class EquipeController {
    private final EquipeService equipeService;

    @Autowired
    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @GetMapping
    public List<Equipe> getAll() { return equipeService.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Equipe> getById(@PathVariable Long id) {
        return equipeService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Equipe create(@RequestBody Equipe equipe) { return equipeService.save(equipe); }

    @PutMapping("/{id}")
    public ResponseEntity<Equipe> update(@PathVariable Long id, @RequestBody Equipe equipe) {
        if (!equipeService.getById(id).isPresent()) return ResponseEntity.notFound().build();
        equipe.setId(id);
        return ResponseEntity.ok(equipeService.save(equipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!equipeService.getById(id).isPresent()) return ResponseEntity.notFound().build();
        equipeService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 