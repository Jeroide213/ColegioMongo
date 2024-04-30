package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.Course;
import com.example.ColegioMongo.Service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Operation(summary = "Obtener todos los cursos")
    @GetMapping
    public ResponseEntity<List<Course>> obtenerTodos() {
        List<Course> courses = courseService.getAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un curso por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado el curso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Course.class))}),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el curso")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable("id") String id) {
        Optional<Course> course = courseService.getById(id);
        return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Guardar un nuevo curso")
    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
        Course newCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un curso existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Course.class))}),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el curso")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") String id, @RequestBody Course nuevoCourse) {
        Optional<Course> updatedCourse = courseService.updateCourse(id, nuevoCourse);
        return updatedCourse.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Eliminar un curso por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el curso")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}