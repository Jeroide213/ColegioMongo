package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.Professor;
import com.example.ColegioMongo.Service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profesores")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private RestTemplate restTemplate; // Inyecta RestTemplate
    @Value("${api.externa.url}")
    private String apiExternaUrl;

    @Operation(summary = "Obtener todos los profesores")
    @GetMapping
    public ResponseEntity<List<Professor>> getAll() {
        List<Professor> professors = professorService.getAll();
        return new ResponseEntity<>(professors, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un profesor por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado el profesor"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el profesor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Professor> getById(@PathVariable("id") String id) {
        Optional<Professor> professor = professorService.getById(id);
        if (professor.isPresent()) {
            return new ResponseEntity<>(professor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Guardar un nuevo profesor")
    @PostMapping
    public ResponseEntity<Professor> saveProfessor(@RequestBody Professor professor) {
        Professor newProfessor = professorService.saveProfessor(professor);
        return new ResponseEntity<>(newProfessor, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un profesor existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesor actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el profesor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable("id") String id, @RequestBody Professor newProfessor) {
        Optional<Professor> updatedProfessor = professorService.updateProfessor(id, newProfessor);
        if (updatedProfessor.isPresent()) {
            return new ResponseEntity<>(updatedProfessor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar un profesor por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Profesor eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el profesor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable("id") String id) {
        professorService.deleteProfessor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}