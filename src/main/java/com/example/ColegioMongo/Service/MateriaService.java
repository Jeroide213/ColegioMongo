package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Materia;
import com.example.ColegioMongo.Models.Profesor;
import com.example.ColegioMongo.Repository.MateriaRepository;
import com.example.ColegioMongo.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {
    @Autowired
    private MateriaRepository materiaRepository;
    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Materia> obtenerTodos() {
        return materiaRepository.findAll();
    }

    public Optional<Materia> obtenerPorId(Long id) {
        return materiaRepository.findById(id);
    }

    public Materia guardar(Materia materia) {
        return materiaRepository.save(materia);
    }
    public Optional<Materia> actualizar(Long id, Materia nuevaMateria) {
        Optional<Materia> materiaOptional = materiaRepository.findById(id);
        if (materiaOptional.isPresent()) {
            nuevaMateria.setId(id);
            return Optional.of(materiaRepository.save(nuevaMateria));
        } else {
            return Optional.empty();
        }
    }
    public void eliminar(Long id) {
        materiaRepository.deleteById(id);
    }
    public void asignarProfesor(Long idMateria, Long idProfesor) {
        Optional<Materia> materiaOptional = materiaRepository.findById(idMateria);
        Optional<Profesor> profesorOptional = profesorRepository.findById(idProfesor);
        if (materiaOptional.isPresent() && profesorOptional.isPresent()) {
            Materia materia = materiaOptional.get();
            Profesor profesor = profesorOptional.get();
            List<Profesor> profesoresMateria = materia.getProfesores();
            if (profesoresMateria == null) {
                profesoresMateria = new ArrayList<>();
            }
            profesoresMateria.add(profesor);
            materia.setProfesores(profesoresMateria);
            materiaRepository.save(materia);
        } else {
            throw new IllegalArgumentException("No se encontró la materia o el profesor.");
        }
    }
}