package com.example.ColegioMongo.Repository;

import com.example.ColegioMongo.Models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findByName(String name);
    Optional<Student> findByDni(Long dni);
}
