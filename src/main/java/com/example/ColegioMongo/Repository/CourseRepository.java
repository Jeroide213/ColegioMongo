package com.example.ColegioMongo.Repository;

import com.example.ColegioMongo.Models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
    List<Course> findByName(String name);
}
