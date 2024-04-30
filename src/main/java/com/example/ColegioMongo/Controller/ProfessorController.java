package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.Professor;
import com.example.ColegioMongo.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profesores")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private RestTemplate restTemplate; // Inyecta RestTemplate
    @Value("${api.externa.url}")
    private String apiExternaUrl;

    @GetMapping
    public ResponseEntity<List<Professor>> getAll() {
        List<Professor> professors = professorService.getAll();
        return new ResponseEntity<>(professors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getById(@PathVariable("id") String id) {
        Optional<Professor> professor = professorService.getById(id);
        if (professor.isPresent()) {
            return new ResponseEntity<>(professor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Professor> saveProfessor(@RequestBody Professor professor) {
        Professor newProfessor = professorService.saveProfessor(professor);
        return new ResponseEntity<>(newProfessor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable("id") String id, @RequestBody Professor newProfessor) {
        Optional<Professor> updatedProfessor = professorService.updateProfessor(id, newProfessor);
        if (updatedProfessor.isPresent()) {
            return new ResponseEntity<>(updatedProfessor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable("id") String id) {
        professorService.deleteProfessor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}