package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.Directivo;
import com.example.ColegioMongo.Service.DirectivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/directivos")
public class DirectivoController {
    @Autowired
    private DirectivoService directivoService;

    @GetMapping
    public ResponseEntity<List<Directivo>> obtenerTodos(){
        List<Directivo> directivos = directivoService.obtenerTodos();
        return new ResponseEntity<>(directivos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Directivo> obtenerPorId(@PathVariable("id")String id){
        Optional<Directivo> directivo = directivoService.obtenerPorId(id);
        if (directivo.isPresent()){
            return new ResponseEntity<>(directivo.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody Directivo directivo){
        try{
            Directivo nuevoDirectivo = directivoService.guardarDirectivo(directivo);
            return ResponseEntity.status(HttpStatus.CREATED).body("Directivo registrado y usuario creado exitosamente");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el alumno: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") String id){
        directivoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Directivo> actualizar(@PathVariable("id") String id, @RequestBody Directivo nuevoDirectivo){
        Optional<Directivo> directivoActualizado = directivoService.actualizar(id, nuevoDirectivo);
        if (directivoActualizado.isPresent()){
            return new ResponseEntity<>(directivoActualizado.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}