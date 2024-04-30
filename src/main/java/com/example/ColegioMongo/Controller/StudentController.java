package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.Student;
import com.example.ColegioMongo.Service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Operation(summary = "Obtener todos los estudiantes")
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un estudiante por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado el estudiante"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el estudiante")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentsById(@PathVariable("id") String id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Registrar un nuevo estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estudiante registrado y usuario creado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error al registrar el estudiante")
    })
    @PostMapping
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        try {
            // Guarda al student en la API interna
            Student newStudent = studentService.saveStudent(student);

            // Devuelve una respuesta con el student creado en la API interna
            return ResponseEntity.status(HttpStatus.CREATED).body("Alumno registrado y usuario creado exitosamente.");
        } catch (Exception e) {
            // Devuelve una respuesta con el error si ocurre algún problema
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el alumno: " + e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un estudiante por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estudiante eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el estudiante")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") String id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Actualizar un estudiante por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el estudiante")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") String id, @RequestBody Student newStudent) {
        Optional<Student> updatedStudent = studentService.updateStudent(id, newStudent);
        if (updatedStudent.isPresent()) {
            return new ResponseEntity<>(updatedStudent.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}