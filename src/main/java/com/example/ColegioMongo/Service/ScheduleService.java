package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Schedules;
import com.example.ColegioMongo.Models.Subject;
import com.example.ColegioMongo.Repository.ScheduleRepository;
import com.example.ColegioMongo.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public List<Schedules> getAll() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedules> getById(String id) {
        return scheduleRepository.findById(id);
    }

    public Schedules saveSchedule(Schedules schedules) {
        if (schedules == null) {
            throw new IllegalArgumentException("El horario proporcionado es nulo");
        }
        if (schedules.getDayOfWeek() == null || schedules.getHoraInicio() == null || schedules.getEndingTime() == null || schedules.getMateria() == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios: día de la semana, hora de inicio, hora de fin y materia");
        }
        if (schedules.getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new IllegalArgumentException("No se pueden programar horarios para los domingos");
        }
        return scheduleRepository.save(schedules);
    }
    public void assignSubject(String idHorario, String idMateria) {
        Optional<Schedules> optionalSchedules = scheduleRepository.findById(idHorario);
        Optional<Subject> optionalSubject = subjectRepository.findById(idMateria);

        if (optionalSchedules.isPresent() && optionalSubject.isPresent()) {
            Schedules schedules = optionalSchedules.get();
            Subject subject = optionalSubject.get();
            schedules.setMateria(subject);
            scheduleRepository.save(schedules);
        } else {
            throw new IllegalArgumentException("No se encontró el horario o la materia.");
        }
    }

    public Optional<Schedules> updateSchedule(String id, Schedules newSchedules) {
        Optional<Schedules> optionalSchedules = scheduleRepository.findById(id);
        if (optionalSchedules.isPresent()) {
            newSchedules.setId(id);
            return Optional.of(scheduleRepository.save(newSchedules));
        } else {
            return Optional.empty();
        }
    }
    public void delete(String id) {
        scheduleRepository.deleteById(id);
    }
}
