package com.example.ColegioMongo.Repository;

import com.example.ColegioMongo.Models.Managers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepository extends MongoRepository<Managers, String> {
    List<Managers> findByName(String name);
    Optional<Managers> findByDni(Long dni);
}
