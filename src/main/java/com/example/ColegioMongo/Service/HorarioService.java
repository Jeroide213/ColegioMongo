package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Horario;
import com.example.ColegioMongo.Models.Materia;
import com.example.ColegioMongo.Repository.HorarioRepository;
import com.example.ColegioMongo.Repository.MateriaRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {
    @Autowired
    private HorarioRepository horarioRepository;
    @Autowired
    private MateriaRepository materiaRepository;

    public List<Horario> obtenerTodos() {
        return horarioRepository.findAll();
    }

    public Optional<Horario> obtenerPorId(String id) {
        return horarioRepository.findById(id);
    }

    public Horario guardar(Horario horario) {
        if (horario == null) {
            throw new IllegalArgumentException("El horario proporcionado es nulo");
        }
        if (horario.getDiaSemana() == null || horario.getHoraInicio() == null || horario.getHoraFin() == null || horario.getMateria() == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios: día de la semana, hora de inicio, hora de fin y materia");
        }
        if (horario.getDiaSemana() == DayOfWeek.SUNDAY) {
            throw new IllegalArgumentException("No se pueden programar horarios para los domingos");
        }
        return horarioRepository.save(horario);
    }
    public void asignarMateria(String idHorario, String idMateria) {
        Optional<Horario> horarioOptional = horarioRepository.findById(idHorario);
        Optional<Materia> materiaOptional = materiaRepository.findById(idMateria);

        if (horarioOptional.isPresent() && materiaOptional.isPresent()) {
            Horario horario = horarioOptional.get();
            Materia materia = materiaOptional.get();
            horario.setMateria(materia);
            horarioRepository.save(horario);
        } else {
            throw new IllegalArgumentException("No se encontró el horario o la materia.");
        }
    }

    public Optional<Horario> actualizar(String id, Horario nuevoHorario) {
        Optional<Horario> horarioOptional = horarioRepository.findById(id);
        if (horarioOptional.isPresent()) {
            nuevoHorario.setId(id);
            return Optional.of(horarioRepository.save(nuevoHorario));
        } else {
            return Optional.empty();
        }
    }
    public void eliminar(String id) {
        horarioRepository.deleteById(id);
    }
}
