package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/billets")
@CrossOrigin(origins = "*")
public class BilletController {
    private final BilletService billetService;

    @Autowired
    public BilletController(BilletService billetService) {
        this.billetService = billetService;
    }

    @GetMapping
    public List<Billet> getAll() { return billetService.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Billet> getById(@PathVariable Long id) {
        return billetService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Billet create(@RequestBody Billet billet) { return billetService.save(billet); }

    @PutMapping("/{id}")
    public ResponseEntity<Billet> update(@PathVariable Long id, @RequestBody Billet billet) {
        if (!billetService.getById(id).isPresent()) return ResponseEntity.notFound().build();
        billet.setId(id);
        return ResponseEntity.ok(billetService.save(billet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!billetService.getById(id).isPresent()) return ResponseEntity.notFound().build();
        billetService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 