package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sieges")
@CrossOrigin(origins = "*")
public class SiegeController {
    private final SiegeService siegeService;

    @Autowired
    public SiegeController(SiegeService siegeService) {
        this.siegeService = siegeService;
    }

    @GetMapping
    public List<Siege> getAll() { return siegeService.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Siege> getById(@PathVariable Long id) {
        return siegeService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Siege create(@RequestBody Siege siege) { return siegeService.save(siege); }

    @PutMapping("/{id}")
    public ResponseEntity<Siege> update(@PathVariable Long id, @RequestBody Siege siege) {
        if (!siegeService.getById(id).isPresent()) return ResponseEntity.notFound().build();
        siege.setId(id);
        return ResponseEntity.ok(siegeService.save(siege));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!siegeService.getById(id).isPresent()) return ResponseEntity.notFound().build();
        siegeService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 