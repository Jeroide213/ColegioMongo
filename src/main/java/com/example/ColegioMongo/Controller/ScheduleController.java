package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.Schedules;
import com.example.ColegioMongo.Service.ScheduleService;
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
@RequestMapping("/horarios")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @Operation(summary = "Obtener todos los horarios")
    @GetMapping
    public ResponseEntity<List<Schedules>> getAll() {
        List<Schedules> schedules = scheduleService.getAll();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un horario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado el horario"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el horario")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Schedules> getById(@PathVariable("id") String id) {
        Optional<Schedules> schedule = scheduleService.getById(id);
        if (schedule.isPresent()) {
            return new ResponseEntity<>(schedule.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Guardar un nuevo horario")
    @PostMapping
    public ResponseEntity<Schedules> save(@RequestBody Schedules schedules) {
        Schedules newSchedules = scheduleService.saveSchedule(schedules);
        return new ResponseEntity<>(newSchedules, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un horario existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Horario actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el horario")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Schedules> update(@PathVariable("id") String id, @RequestBody Schedules newSchedules) {
        Optional<Schedules> updatedSchedule = scheduleService.updateSchedule(id, newSchedules);
        if (updatedSchedule.isPresent()) {
            return new ResponseEntity<>(updatedSchedule.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar un horario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Horario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el horario")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}