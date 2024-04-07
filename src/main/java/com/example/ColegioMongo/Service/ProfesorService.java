package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Profesor;
import com.example.ColegioMongo.Repository.ProfesorRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {
    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> obtenerTodos() {
        return profesorRepository.findAll();
    }
    public Optional<Profesor> obtenerPorId(String id) {
        return profesorRepository.findById(id);
    }
    public Profesor guardar(Profesor profesor) {
        validarProfesor(profesor);
        return profesorRepository.save(profesor);
    }

    private void validarProfesor(Profesor profesor) {
        if (profesor.getNombre() == null || profesor.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del profesor no puede estar vacío");
        }
        if (profesor.getApellido() == null || profesor.getApellido().isEmpty()) {
            throw new IllegalArgumentException("El apellido del profesor no puede estar vacío");
        }
        if (profesor.getFechaDeNacimiento() != null && profesor.getFechaDeNacimiento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento del profesor no es valida");
        }
        if (profesor.getEdad() <= 0) {
            throw new IllegalArgumentException("La edad del profesor no es válida");
        }
    }
    public void eliminar(String id) {
        profesorRepository.deleteById(id);

    }
    public Optional<Profesor> actualizar(String id, Profesor nuevoProfesor) {
        Optional<Profesor> profesorOptional = profesorRepository.findById(id);
        if (profesorOptional.isPresent()) {
            nuevoProfesor.setId(id);
            return Optional.of(profesorRepository.save(nuevoProfesor));
        } else {
            return Optional.empty();
        }
    }
}
