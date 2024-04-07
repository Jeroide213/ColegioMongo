package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Profesor;
import com.example.ColegioMongo.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {
    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> obtenerTodos() {
        return profesorRepository.findAll();
    }
    public Optional<Profesor> obtenerPorId(Long id) {
        return profesorRepository.findById(id);
    }
    public Profesor guardar(Profesor profesor) {
        return profesorRepository.save(profesor);
    }
    public void eliminar(Long id) {
        profesorRepository.deleteById(id);

    }
    public Optional<Profesor> actualizar(Long id, Profesor nuevoProfesor) {
        Optional<Profesor> profesorOptional = profesorRepository.findById(id);
        if (profesorOptional.isPresent()) {
            nuevoProfesor.setId(id);
            return Optional.of(profesorRepository.save(nuevoProfesor));
        } else {
            return Optional.empty();
        }
    }
}
