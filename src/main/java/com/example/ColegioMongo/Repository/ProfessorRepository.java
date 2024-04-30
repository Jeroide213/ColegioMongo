package com.example.ColegioMongo.Repository;

import com.example.ColegioMongo.Models.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends MongoRepository<Professor, String> {
    List<Professor> findByName(String name);
    Optional<Professor> findByDni(Long dni);
}
