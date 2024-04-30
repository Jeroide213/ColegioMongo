package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.Student;
import com.example.ColegioMongo.Service.StudentService;
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

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentsById(@PathVariable("id") String id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") String id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
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