package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Professor;
import com.example.ColegioMongo.Models.Student;
import com.example.ColegioMongo.Repository.ProfessorRepository;
import com.example.ColegioMongo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RestTemplate restTemplate;

    public List<Professor> getAll() {
        return professorRepository.findAll();
    }

    public Optional<Professor> getById(String id) {
        return professorRepository.findById(id);
    }

    public Professor saveProfessor(Professor professor) {
        professorValidation(professor);
        Optional<Student> studentWithDni = studentRepository.findByDni(professor.getDni());
        if (studentWithDni.isPresent()) {
            throw new IllegalArgumentException("El DNI ya está registrado para un alumno.");
        }
        Optional<Professor> existingProfessor = professorRepository.findByDni(professor.getDni());
        if (existingProfessor.isPresent()) {
            throw new IllegalArgumentException("El DNI ya está registrado para otro profesor.");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\"username\": \"" + professor.getDni() + "\", \"password\": \"" + professor.getDni() + "\", \"rol\": \"PROFESOR\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Enviar la solicitud para crear el usuario en la API externa
        restTemplate.postForObject("http://localhost:8081/api/crearUsuario", requestEntity, String.class);
        return professorRepository.save(professor);
    }

    private void professorValidation(Professor professor) {
        if (professor.getDni() == null || professor.getDni() == 0) {
            throw new IllegalArgumentException("El DNI del profesor no puede estar vacío");
        }
        if (professor.getName() == null || professor.getName().isEmpty()) {
            throw new IllegalArgumentException("El nombre del profesor no puede estar vacío");
        }
        if (professor.getLastName() == null || professor.getLastName().isEmpty()) {
            throw new IllegalArgumentException("El apellido del profesor no puede estar vacío");
        }
        if (professor.getBirthDate() != null && professor.getBirthDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento del profesor no es valida");
        }
        if (professor.getOld() <= 0) {
            throw new IllegalArgumentException("La edad del profesor no es válida");
        }
    }

    public void deleteProfessor(String id) {
        professorRepository.deleteById(id);

    }

    public Optional<Professor> updateProfessor(String id, Professor newProfessor) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);
        if (optionalProfessor.isPresent()) {
            newProfessor.setId(id);
            return Optional.of(professorRepository.save(newProfessor));
        } else {
            return Optional.empty();
        }
    }
}
