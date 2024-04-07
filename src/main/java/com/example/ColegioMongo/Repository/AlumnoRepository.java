package com.example.ColegioMongo.Repository;

import com.example.ColegioMongo.Models.Alumno;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface AlumnoRepository extends MongoRepository<Alumno, String> {
    List<Alumno> findByNombre(String nombre);
}
