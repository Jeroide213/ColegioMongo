package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Alumno;
import com.example.ColegioMongo.Models.Directivo;
import com.example.ColegioMongo.Repository.DirectivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectivoService {
    @Autowired
    private DirectivoRepository directivoRepository;

    public List<Directivo> obtenerTodos() {
        return directivoRepository.findAll();
    }

    public Optional<Directivo> obtenerPorId(String id) {
        return directivoRepository.findById(id);
    }
    public void eliminar(String id) {
        directivoRepository.deleteById(id);
    }
    public Optional<Directivo> actualizar(String id, Directivo nuevoDirectivo) {
        Optional<Directivo> directivoOptional = directivoRepository.findById(id);
        if (directivoOptional.isPresent()) {
            nuevoDirectivo.setId(id);
            return Optional.of(directivoRepository.save(nuevoDirectivo));
        } else {
            return Optional.empty();
        }
    }
}
