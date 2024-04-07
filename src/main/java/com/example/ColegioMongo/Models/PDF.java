package com.example.ColegioMongo.Models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PDF {
    private ObjectId id;
    private String nombre;
    private byte[] contenidoPdf;
    private String alumnoId;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public PDF(ObjectId id, String nombre, byte[] contenidoPdf, String alumnoId) {
        this.id = id;
        this.nombre = nombre;
        this.contenidoPdf = contenidoPdf;
        this.alumnoId = alumnoId;
    }
}
