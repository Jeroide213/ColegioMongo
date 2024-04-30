package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Subject;
import com.example.ColegioMongo.Models.Professor;
import com.example.ColegioMongo.Repository.SubjectRepository;
import com.example.ColegioMongo.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getById(String id) {
        return subjectRepository.findById(id);
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Optional<Subject> updateSubject(String id, Subject newSubject) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            newSubject.setId(id);
            return Optional.of(subjectRepository.save(newSubject));
        } else {
            return Optional.empty();
        }
    }

    public void deleteSubject(String id) {
        subjectRepository.deleteById(id);
    }

    public void assignProfessor(String subjectId, String professorId) {
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
        Optional<Professor> optionalProfessor = professorRepository.findById(professorId);
        if (optionalSubject.isPresent() && optionalProfessor.isPresent()) {
            Subject subject = optionalSubject.get();
            Professor professor = optionalProfessor.get();
            List<Professor> professorSubject = subject.getProfessors();
            if (professorSubject == null) {
                professorSubject = new ArrayList<>();
            }
            professorSubject.add(professor);
            subject.setProfessors(professorSubject);
            subjectRepository.save(subject);
        } else {
            throw new IllegalArgumentException("No se encontró la materia o el profesor.");
        }
    }
}