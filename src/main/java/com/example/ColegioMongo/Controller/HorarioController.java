package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.Horario;
import com.example.ColegioMongo.Service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/horarios")
public class HorarioController {
    @Autowired
    private HorarioService horarioService;

    @GetMapping
    public ResponseEntity<List<Horario>> obtenerTodos() {
        List<Horario> horarios = horarioService.obtenerTodos();
        return new ResponseEntity<>(horarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario> obtenerPorId(@PathVariable("id") Long id) {
        Optional<Horario> horario = horarioService.obtenerPorId(id);
        if (horario.isPresent()) {
            return new ResponseEntity<>(horario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Horario> guardar(@RequestBody Horario horario) {
        Horario nuevoHorario = horarioService.guardar(horario);
        return new ResponseEntity<>(nuevoHorario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horario> actualizar(@PathVariable("id") Long id, @RequestBody Horario nuevoHorario) {
        Optional<Horario> horarioActualizado = horarioService.actualizar(id, nuevoHorario);
        if (horarioActualizado.isPresent()) {
            return new ResponseEntity<>(horarioActualizado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        horarioService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
