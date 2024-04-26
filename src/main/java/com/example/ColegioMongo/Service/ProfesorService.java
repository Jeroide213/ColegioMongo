package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Alumno;
import com.example.ColegioMongo.Models.Profesor;
import com.example.ColegioMongo.Repository.AlumnoRepository;
import com.example.ColegioMongo.Repository.ProfesorRepository;
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
public class ProfesorService {
    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private RestTemplate restTemplate;

    public List<Profesor> obtenerTodos() {
        return profesorRepository.findAll();
    }

    public Optional<Profesor> obtenerPorId(String id) {
        return profesorRepository.findById(id);
    }

    public Profesor guardar(Profesor profesor) {
        validarProfesor(profesor);
        Optional<Alumno> alumnoConDni = alumnoRepository.findByDni(profesor.getDni());
        if (alumnoConDni.isPresent()) {
            throw new IllegalArgumentException("El DNI ya está registrado para un alumno.");
        }
        Optional<Profesor> profesorExistente = profesorRepository.findByDni(profesor.getDni());
        if (profesorExistente.isPresent()) {
            throw new IllegalArgumentException("El DNI ya está registrado para otro profesor.");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\"username\": \"" + profesor.getDni() + "\", \"password\": \"" + profesor.getDni() + "\", \"rol\": \"PROFESOR\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Enviar la solicitud para crear el usuario en la API externa
        restTemplate.postForObject("http://localhost:8081/api/crearUsuario", requestEntity, String.class);
        return profesorRepository.save(profesor);
    }

    private void validarProfesor(Profesor profesor) {
        if (profesor.getDni() == null || profesor.getDni() == 0) {
            throw new IllegalArgumentException("El DNI del profesor no puede estar vacío");
        }
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
