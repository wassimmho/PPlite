package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stades")
@CrossOrigin(origins = "*")
public class StadeController {
    private final StadeService stadeService;

    @Autowired
    public StadeController(StadeService stadeService) {
        this.stadeService = stadeService;
    }

    @GetMapping
    public List<Stade> getAll() { return stadeService.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Stade> getById(@PathVariable Long id) {
        return stadeService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Stade create(@RequestBody Stade stade) { return stadeService.save(stade); }

    @PutMapping("/{id}")
    public ResponseEntity<Stade> update(@PathVariable Long id, @RequestBody Stade stade) {
        if (!stadeService.getById(id).isPresent()) return ResponseEntity.notFound().build();
        stade.setId(id);
        return ResponseEntity.ok(stadeService.save(stade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!stadeService.getById(id).isPresent()) return ResponseEntity.notFound().build();
        stadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 