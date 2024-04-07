package com.example.ColegioMongo.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Materia {
    @Id
    private Long id;
    private String nombre;
    private List<Horario> horarios;
    private List<Profesor> profesores;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    public Materia() {
    }

    public Materia(Long id, String nombre, List<Horario> horarios, List<Profesor> profesores) {
        this.id = id;
        this.nombre = nombre;
        this.horarios = horarios;
        this.profesores = profesores;
    }
}
