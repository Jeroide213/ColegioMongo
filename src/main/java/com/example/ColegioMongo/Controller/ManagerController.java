package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.Managers;
import com.example.ColegioMongo.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/directivos")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping
    public ResponseEntity<List<Managers>> getAll(){
        List<Managers> managers = managerService.getAll();
        return new ResponseEntity<>(managers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Managers> getById(@PathVariable("id")String id){
        Optional<Managers> managers = managerService.getById(id);
        if (managers.isPresent()){
            return new ResponseEntity<>(managers.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> saveManager(@RequestBody Managers managers){
        try{
            Managers newManagers = managerService.saveManagers(managers);
            return ResponseEntity.status(HttpStatus.CREATED).body("Directivo registrado y usuario creado exitosamente");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el directivo: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        managerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Managers> update(@PathVariable("id") String id, @RequestBody Managers newManagers){
        Optional<Managers> updatedManager = managerService.update(id, newManagers);
        if (updatedManager.isPresent()){
            return new ResponseEntity<>(updatedManager.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}