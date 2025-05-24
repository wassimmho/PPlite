package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/matchs")
@CrossOrigin(origins = "*")
public class MatchFootController {
    private final MatchFootService matchFootService;

    @Autowired
    public MatchFootController(MatchFootService matchFootService) {
        this.matchFootService = matchFootService;
    }

    @GetMapping
    public List<MatchFoot> getAll() { return matchFootService.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<MatchFoot> getById(@PathVariable Long id) {
        return matchFootService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public MatchFoot create(@RequestBody MatchFoot match) { return matchFootService.save(match); }

    @PutMapping("/{id}")
    public ResponseEntity<MatchFoot> update(@PathVariable Long id, @RequestBody MatchFoot match) {
        if (!matchFootService.getById(id).isPresent()) return ResponseEntity.notFound().build();
        match.setId(id);
        return ResponseEntity.ok(matchFootService.save(match));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!matchFootService.getById(id).isPresent()) return ResponseEntity.notFound().build();
        matchFootService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 