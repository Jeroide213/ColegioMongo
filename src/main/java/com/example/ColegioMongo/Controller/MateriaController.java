package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.Materia;
import com.example.ColegioMongo.Service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materias")
public class MateriaController {
    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<Materia>> obtenerTodos() {
        List<Materia> materias = materiaService.obtenerTodos();
        return new ResponseEntity<>(materias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> obtenerPorId(@PathVariable("id") Long id) {
        Optional<Materia> materia = materiaService.obtenerPorId(id);
        if (materia.isPresent()) {
            return new ResponseEntity<>(materia.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Materia> guardar(@RequestBody Materia materia) {
        Materia nuevaMateria = materiaService.guardar(materia);
        return new ResponseEntity<>(nuevaMateria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> actualizar(@PathVariable("id") Long id, @RequestBody Materia nuevaMateria) {
        Optional<Materia> materiaActualizada = materiaService.actualizar(id, nuevaMateria);
        if (materiaActualizada.isPresent()) {
            return new ResponseEntity<>(materiaActualizada.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        materiaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

