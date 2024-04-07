package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Horario;
import com.example.ColegioMongo.Repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {
    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> obtenerTodos() {
        return horarioRepository.findAll();
    }

    public Optional<Horario> obtenerPorId(Long id) {
        return horarioRepository.findById(id);
    }

    public Horario guardar(Horario horario) {
        return horarioRepository.save(horario);
    }
    public Optional<Horario> actualizar(Long id, Horario nuevoHorario) {
        Optional<Horario> horarioOptional = horarioRepository.findById(id);
        if (horarioOptional.isPresent()) {
            nuevoHorario.setId(id);
            return Optional.of(horarioRepository.save(nuevoHorario));
        } else {
            return Optional.empty();
        }
    }
    public void eliminar(Long id) {
        horarioRepository.deleteById(id);
    }

}
