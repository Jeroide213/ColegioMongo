package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.Subject;
import com.example.ColegioMongo.Service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materias")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @Operation(summary = "Obtener todas las materias")
    @GetMapping
    public ResponseEntity<List<Subject>> getAll() {
        List<Subject> subjects = subjectService.getAll();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @Operation(summary = "Obtener una materia por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado la materia"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la materia")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getById(@PathVariable("id") String id) {
        Optional<Subject> subject = subjectService.getById(id);
        if (subject.isPresent()) {
            return new ResponseEntity<>(subject.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Guardar una nueva materia")
    @PostMapping
    public ResponseEntity<Subject> save(@RequestBody Subject subject) {
        Subject newSubject = subjectService.saveSubject(subject);
        return new ResponseEntity<>(newSubject, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una materia existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Materia actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la materia")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Subject> update(@PathVariable("id") String id, @RequestBody Subject newSubject) {
        Optional<Subject> updatedSubject = subjectService.updateSubject(id, newSubject);
        if (updatedSubject.isPresent()) {
            return new ResponseEntity<>(updatedSubject.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar una materia por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Materia eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la materia")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

