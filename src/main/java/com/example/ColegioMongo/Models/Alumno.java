package com.example.ColegioMongo.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Alumno {
    @Id
    private Long id;
    private String nombre;
    private String Apellido;
    private LocalDate fechaDeNacimiento;
    private byte[] contenidoPdf;
    private String ciclo;
    private String especialidad;
    private int faltas;
    private int amonestaciones;
    private int edad;

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

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public byte[] getContenidoPdf() {
        return contenidoPdf;
    }

    public void setContenidoPdf(byte[] contenidoPdf) {
        this.contenidoPdf = contenidoPdf;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public int getAmonestaciones() {
        return amonestaciones;
    }

    public void setAmonestaciones(int amonestaciones) {
        this.amonestaciones = amonestaciones;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Alumno() {
    }

    public Alumno(Long id, String nombre, String apellido, LocalDate fechaDeNacimiento, byte[] contenidoPdf, String ciclo, String especialidad, int faltas, int amonestaciones, int edad) {
        this.id = id;
        this.nombre = nombre;
        Apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.contenidoPdf = contenidoPdf;
        this.ciclo = ciclo;
        this.especialidad = especialidad;
        this.faltas = faltas;
        this.amonestaciones = amonestaciones;
        this.edad = edad;
    }
}