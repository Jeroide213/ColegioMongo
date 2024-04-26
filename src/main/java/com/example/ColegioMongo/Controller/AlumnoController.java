package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.Alumno;
import com.example.ColegioMongo.Service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<Alumno>> obtenerTodos() {
        List<Alumno> alumnos = alumnoService.obtenerTodos();
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtenerPorId(@PathVariable("id") String id) {
        Optional<Alumno> alumno = alumnoService.obtenerPorId(id);
        if (alumno.isPresent()) {
            return new ResponseEntity<>(alumno.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody Alumno alumno) {
        try {
            // Guarda al alumno en la API interna
            Alumno nuevoAlumno = alumnoService.guardar(alumno);

            // Devuelve una respuesta con el alumno creado en la API interna
            return ResponseEntity.status(HttpStatus.CREATED).body("Alumno registrado y usuario creado exitosamente.");
        } catch (Exception e) {
            // Devuelve una respuesta con el error si ocurre algún problema
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el alumno: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") String id) {
        alumnoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizar(@PathVariable("id") String id, @RequestBody Alumno nuevoAlumno) {
        Optional<Alumno> alumnoActualizado = alumnoService.actualizar(id, nuevoAlumno);
        if (alumnoActualizado.isPresent()) {
            return new ResponseEntity<>(alumnoActualizado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}