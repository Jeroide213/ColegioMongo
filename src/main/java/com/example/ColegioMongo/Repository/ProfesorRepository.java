package com.example.ColegioMongo.Repository;

import com.example.ColegioMongo.Models.Profesor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends MongoRepository<Profesor, Long> {
    List<Profesor> findByNombre(String nombre);
}
