package com.example.ColegioMongo.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Subject {
    @Id
    private String id;
    private String name;
    private List<Schedules> schedules;
    private List<Professor> professors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Schedules> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedules> schedules) {
        this.schedules = schedules;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public Subject() {
    }

    public Subject(String id, String name, List<Schedules> schedules, List<Professor> professors) {
        this.id = id;
        this.name = name;
        this.schedules = schedules;
        this.professors = professors;
    }
}
