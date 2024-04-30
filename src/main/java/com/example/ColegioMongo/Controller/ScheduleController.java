package com.example.ColegioMongo.Controller;

import com.example.ColegioMongo.Models.Schedules;
import com.example.ColegioMongo.Service.ScheduleService;
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

    @GetMapping
    public ResponseEntity<List<Schedules>> getAll() {
        List<Schedules> schedules = scheduleService.getAll();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedules> getById(@PathVariable("id") String id) {
        Optional<Schedules> schedule = scheduleService.getById(id);
        if (schedule.isPresent()) {
            return new ResponseEntity<>(schedule.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Schedules> save(@RequestBody Schedules schedules) {
        Schedules newSchedules = scheduleService.saveSchedule(schedules);
        return new ResponseEntity<>(newSchedules, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedules> update(@PathVariable("id") String id, @RequestBody Schedules newSchedules) {
        Optional<Schedules> updatedSchedule = scheduleService.updateSchedule(id, newSchedules);
        if (updatedSchedule.isPresent()) {
            return new ResponseEntity<>(updatedSchedule.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
