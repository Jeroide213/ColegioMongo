package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.Subject;
import com.example.ColegioMongo.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materias")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<Subject>> getAll() {
        List<Subject> subjects = subjectService.getAll();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getById(@PathVariable("id") String id) {
        Optional<Subject> subject = subjectService.getById(id);
        if (subject.isPresent()) {
            return new ResponseEntity<>(subject.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Subject> save(@RequestBody Subject subject) {
        Subject newSubject = subjectService.saveSubject(subject);
        return new ResponseEntity<>(newSubject, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> update(@PathVariable("id") String id, @RequestBody Subject newSubject) {
        Optional<Subject> updatedSubject = subjectService.updateSubject(id, newSubject);
        if (updatedSubject.isPresent()) {
            return new ResponseEntity<>(updatedSubject.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

