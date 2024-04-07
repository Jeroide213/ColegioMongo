package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Alumno;
import com.example.ColegioMongo.Models.Curso;
import com.example.ColegioMongo.Repository.AlumnoRepository;
import com.example.ColegioMongo.Repository.CursoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<Alumno> obtenerTodos() {
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> obtenerPorId(ObjectId id) {
        return alumnoRepository.findById(id);
    }

    public Alumno guardar(Alumno alumno) {
        validarAlumno(alumno);
        return alumnoRepository.save(alumno);
    }

    public void eliminar(ObjectId id) {
        alumnoRepository.deleteById(id);
    }

    public List<Alumno> buscarPorNombre(String nombre) {
        return alumnoRepository.findByNombre(nombre);
    }

    public Optional<Alumno> actualizar(ObjectId id, Alumno nuevoAlumno) {
        Optional<Alumno> alumnoOptional = alumnoRepository.findById(id);
        if (alumnoOptional.isPresent()) {
            nuevoAlumno.setId(id);
            return Optional.of(alumnoRepository.save(nuevoAlumno));
        } else {
            return Optional.empty();
        }
    }

    public void agregarAlumnoACurso(ObjectId idAlumno, ObjectId idCurso) {
        Optional<Alumno> alumnoOptional = alumnoRepository.findById(idAlumno);
        Optional<Curso> cursoOptional = cursoRepository.findById(idCurso);
        if (alumnoOptional.isPresent() && cursoOptional.isPresent()) {
            Alumno alumno = alumnoOptional.get();
            Curso curso = cursoOptional.get();
            List<Alumno> alumnosCurso = curso.getAlumnos();
            if (alumnosCurso == null) {
                alumnosCurso = new ArrayList<>();
            }
            alumnosCurso.add(alumno);
            curso.setAlumnos(alumnosCurso);
            cursoRepository.save(curso);
        } else {
            throw new IllegalArgumentException("No se encontró el alumno o el curso.");
        }
    }

    private void validarAlumno(Alumno alumno) {
        if (alumno.getNombre() == null || alumno.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del alumno no puede estar vacío");
        }

        if (alumno.getApellido() == null || alumno.getApellido().isEmpty()) {
            throw new IllegalArgumentException("El apellido del alumno no puede estar vacío");
        }

        if (alumno.getFechaDeNacimiento() == null || alumno.getFechaDeNacimiento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento del alumno no es válida");
        }

        if (alumno.getCiclo() == null || alumno.getCiclo().isEmpty()) {
            throw new IllegalArgumentException("El ciclo del alumno no puede estar vacío");
        }

        if (alumno.getEspecialidad() == null || alumno.getEspecialidad().isEmpty()) {
            throw new IllegalArgumentException("La especialidad del alumno no puede estar vacía");
        }
    }
}