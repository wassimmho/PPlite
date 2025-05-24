package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sections")
@CrossOrigin(origins = "*")
public class SectionController {
    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping
    public List<Section> getAll() { return sectionService.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Section> getById(@PathVariable Long id) {
        return sectionService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Section create(@RequestBody Section section) { return sectionService.save(section); }

    @PutMapping("/{id}")
    public ResponseEntity<Section> update(@PathVariable Long id, @RequestBody Section section) {
        if (!sectionService.getById(id).isPresent()) return ResponseEntity.notFound().build();
        section.setId(id);
        return ResponseEntity.ok(sectionService.save(section));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!sectionService.getById(id).isPresent()) return ResponseEntity.notFound().build();
        sectionService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 