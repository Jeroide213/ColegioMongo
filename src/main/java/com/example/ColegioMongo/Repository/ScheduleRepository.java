package com.example.ColegioMongo.Repository;

import com.example.ColegioMongo.Models.Schedules;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedules, String> {
}
