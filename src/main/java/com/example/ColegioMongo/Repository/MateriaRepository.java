package com.example.ColegioMongo.Repository;

import com.example.ColegioMongo.Models.Materia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MateriaRepository extends MongoRepository<Materia, Long> {
    List<Materia> findByNombre(String nombre);
}
