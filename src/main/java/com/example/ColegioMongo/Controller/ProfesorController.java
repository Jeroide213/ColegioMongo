package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.Profesor;
import com.example.ColegioMongo.Service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<Profesor>> obtenerTodos() {
        List<Profesor> profesores = profesorService.obtenerTodos();
        return new ResponseEntity<>(profesores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerPorId(@PathVariable("id") String id) {
        Optional<Profesor> profesor = profesorService.obtenerPorId(id);
        if (profesor.isPresent()) {
            return new ResponseEntity<>(profesor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Profesor> guardar(@RequestBody Profesor profesor) {
        Profesor nuevoProfesor = profesorService.guardar(profesor);
        return new ResponseEntity<>(nuevoProfesor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> actualizar(@PathVariable("id") String id, @RequestBody Profesor nuevoProfesor) {
        Optional<Profesor> profesorActualizado = profesorService.actualizar(id, nuevoProfesor);
        if (profesorActualizado.isPresent()) {
            return new ResponseEntity<>(profesorActualizado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") String id) {
        profesorService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}