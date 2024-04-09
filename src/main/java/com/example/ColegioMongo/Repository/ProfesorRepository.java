package com.example.ColegioMongo.Repository;

import com.example.ColegioMongo.Models.Alumno;
import com.example.ColegioMongo.Models.Profesor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesorRepository extends MongoRepository<Profesor, String> {
    List<Profesor> findByNombre(String nombre);
    Optional<Profesor> findByDni(Long dni);
}
