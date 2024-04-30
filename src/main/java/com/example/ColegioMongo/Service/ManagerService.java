package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Managers;
import com.example.ColegioMongo.Repository.ManagerRepository;
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
public class ManagerService {
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private RestTemplate restTemplate;

    public List<Managers> getAll() {
        return managerRepository.findAll();
    }

    public Optional<Managers> getById(String id) {
        return managerRepository.findById(id);
    }

    public void delete(String id) {
        managerRepository.deleteById(id);
    }

    public Optional<Managers> update(String id, Managers newManagers) {
        Optional<Managers> optionalManagers = managerRepository.findById(id);
        if (optionalManagers.isPresent()) {
            newManagers.setId(id);
            return Optional.of(managerRepository.save(newManagers));
        } else {
            return Optional.empty();
        }
    }

    public Managers saveManagers(Managers managers){
        managerValidation(managers);
        Optional<Managers> existingManager = managerRepository.findByDni(managers.getDni());
        if (existingManager.isPresent()) {
            throw new IllegalArgumentException("El DNI ya está registrado para otro alumno.");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\"username\": \"" + managers.getDni() + "\", \"password\": \"" + managers.getDni() + "\", \"rol\": \"DIRECTIVO\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Enviar la solicitud para crear el usuario en la API externa
        restTemplate.postForObject("http://localhost:8081/api/crearUsuario", requestEntity, String.class);
        return managerRepository.save(managers);
    }

    private void managerValidation(Managers managers){
        if (managers.getDni() == null || managers.getDni() == 0){
            throw new IllegalArgumentException("El DNI del directivo no puede estar vacío");
        }

        if (managers.getName() == null || managers.getName().isEmpty()) {
            throw new IllegalArgumentException("El nombre del directivo no puede estar vacío");
        }
        if (managers.getBirthDate() == null || managers.getBirthDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento del directivo no es válida");
        }
    }

    public List<Managers> findByName(String name) {
        return managerRepository.findByName(name);
    }
}
