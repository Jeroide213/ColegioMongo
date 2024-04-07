package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Curso;
import com.example.ColegioMongo.Models.Materia;
import com.example.ColegioMongo.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MateriaService materiaService;

    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> obtenerPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso guardar(Curso curso) {
        if (curso.getNombre() == null || curso.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del curso es obligatorio");
        }
        return cursoRepository.save(curso);
    }

    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }

    public Optional<Curso> actualizar(Long id, Curso nuevoCurso) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);
        if (cursoOptional.isPresent()) {
            nuevoCurso.setId(id);
            return Optional.of(cursoRepository.save(nuevoCurso));
        } else {
            return Optional.empty();
        }
    }
    public Materia agregarMateriaACurso(Long cursoId, Long materiaId) {
        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
        if (cursoOptional.isPresent()) {
            Optional<Materia> materiaOptional = materiaService.obtenerPorId(materiaId);
            if (materiaOptional.isPresent()) {
                Curso curso = cursoOptional.get();
                Materia materia = materiaOptional.get();
                if (!curso.getMaterias().contains(materia)) {
                    curso.getMaterias().add(materia);
                    cursoRepository.save(curso);
                }
                return materia;
            } else {
                throw new NoSuchElementException("La materia con ID " + materiaId + " no existe");
            }
        } else {
            throw new NoSuchElementException("El curso con ID " + cursoId + " no existe");
        }
    }
}
