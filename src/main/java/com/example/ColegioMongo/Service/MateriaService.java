package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Materia;
import com.example.ColegioMongo.Repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {
    @Autowired
    private MateriaRepository materiaRepository;

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
}