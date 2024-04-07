package com.example.ColegioMongo.Repository;

import com.example.ColegioMongo.Models.Curso;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends MongoRepository<Curso, ObjectId> {
    List<Curso> findByNombre(String nombre);
}
