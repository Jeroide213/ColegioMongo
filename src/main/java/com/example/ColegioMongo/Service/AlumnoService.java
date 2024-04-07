package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Alumno;
import com.example.ColegioMongo.Repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> obtenerTodos() {
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> obtenerPorId(Long id) {
        return alumnoRepository.findById(id);
    }

    public Alumno guardar(Alumno alumno) {
        validarAlumno(alumno);
        return alumnoRepository.save(alumno);
    }

    public void eliminar(Long id) {
        alumnoRepository.deleteById(id);
    }

    public List<Alumno> buscarPorNombre(String nombre) {
        return alumnoRepository.findByNombre(nombre);
    }
    private void validarAlumno(Alumno alumno) {
        if (alumno.getNombre() == null || alumno.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del alumno no puede estar vacío");
        }
        if (alumno.getFechaDeNacimiento() == null || alumno.getFechaDeNacimiento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento del alumno debe ser una fecha válida en el pasado");
        }
    }
    public Optional<Alumno> actualizar(Long id, Alumno nuevoAlumno) {
        Optional<Alumno> alumnoOptional = alumnoRepository.findById(id);
        if (alumnoOptional.isPresent()) {
            nuevoAlumno.setId(id);
            return Optional.of(alumnoRepository.save(nuevoAlumno));
        } else {
            return Optional.empty();
        }
    }
}
