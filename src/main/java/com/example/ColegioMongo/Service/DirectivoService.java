package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Alumno;
import com.example.ColegioMongo.Models.Directivo;
import com.example.ColegioMongo.Repository.DirectivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DirectivoService {
    @Autowired
    private DirectivoRepository directivoRepository;

    public List<Directivo> obtenerTodos() {
        return directivoRepository.findAll();
    }

    public Optional<Directivo> obtenerPorId(String id) {
        return directivoRepository.findById(id);
    }

    public void eliminar(String id) {
        directivoRepository.deleteById(id);
    }

    public Optional<Directivo> actualizar(String id, Directivo nuevoDirectivo) {
        Optional<Directivo> directivoOptional = directivoRepository.findById(id);
        if (directivoOptional.isPresent()) {
            nuevoDirectivo.setId(id);
            return Optional.of(directivoRepository.save(nuevoDirectivo));
        } else {
            return Optional.empty();
        }
    }

    public Directivo guardarDirectivo(Directivo directivo){
        validarDirectivo(directivo);
        Optional<Directivo> directivoExistente = directivoRepository.findByDni(directivo.getDni());
        if (directivoExistente.isPresent()) {
            throw new IllegalArgumentException("El DNI ya está registrado para otro alumno.");
        }
        return directivoRepository.save(directivo);
    }

    private void validarDirectivo(Directivo directivo){
        if (directivo.getDni() == null || directivo.getDni() == 0){
            throw new IllegalArgumentException("El DNI del directivo no puede estar vacío");
        }

        if (directivo.getNombre() == null || directivo.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del directivo no puede estar vacío");
        }
        if (directivo.getFechaDeNacimiento() == null || directivo.getFechaDeNacimiento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento del directivo no es válida");
        }
    }

    public List<Directivo> buscarPorNombre(String nombre) {
        return directivoRepository.findByNombre(nombre);
    }
}
