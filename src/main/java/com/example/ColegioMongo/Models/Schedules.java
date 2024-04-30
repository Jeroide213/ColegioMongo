package com.example.ColegioMongo.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Document
public class Schedules {
    @Id
    private String id;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endingTime;
    private Subject subject;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getHoraInicio() {
        return startTime;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.startTime = horaInicio;
    }

    public LocalTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalTime endingTime) {
        this.endingTime = endingTime;
    }

    public Subject getMateria() {
        return subject;
    }

    public void setMateria(Subject subject) {
        this.subject = subject;
    }

    public Schedules() {
    }

    public Schedules(String id, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endingTime, Subject subject) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.subject = subject;
    }
}
