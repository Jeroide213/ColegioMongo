package com.example.ColegioMongo.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Curso {
    @Id
    private String id;
    private String nombre;
    private List<Alumno> alumnos;
    private List<Materia> materias;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public Curso() {
    }

    public Curso(String id, String nombre, List<Alumno> alumnos, List<Materia> materias) {
        this.id = id;
        this.nombre = nombre;
        this.alumnos = alumnos;
        this.materias = materias;
    }
}
