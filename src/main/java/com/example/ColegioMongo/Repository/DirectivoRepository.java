package com.example.ColegioMongo.Repository;

import com.example.ColegioMongo.Models.Alumno;
import com.example.ColegioMongo.Models.Directivo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectivoRepository extends MongoRepository<Directivo, String> {
    List<Directivo> findByNombre(String nombre);
    Optional<Directivo> findByDni(Long dni);
}
