package com.example.ColegioMongo.Models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PDF {
    private String id;
    private String nombre;
    private byte[] contenidoPdf;
    private String alumnoId;

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

    public byte[] getContenidoPdf() {
        return contenidoPdf;
    }

    public void setContenidoPdf(byte[] contenidoPdf) {
        this.contenidoPdf = contenidoPdf;
    }

    public String getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(String alumnoId) {
        this.alumnoId = alumnoId;
    }

    public PDF() {
    }

    public PDF(String id, String nombre, byte[] contenidoPdf, String alumnoId) {
        this.id = id;
        this.nombre = nombre;
        this.contenidoPdf = contenidoPdf;
        this.alumnoId = alumnoId;
    }
}
