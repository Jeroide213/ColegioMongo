package com.example.ColegioMongo.Repository;

import com.example.ColegioMongo.Models.PDF;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

@Document
public interface PDFRepository extends MongoRepository<PDF, Long> {
}
