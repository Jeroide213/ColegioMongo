package com.example.ColegioMongo.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Period;

@Document
public class Directivo {
    @Id
    private String id;
    private String nombre;
    private String apellido;
    private LocalDate fechaDeNacimiento;
    public int getEdad() {
        if (fechaDeNacimiento != null) {
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(fechaDeNacimiento, currentDate);
            return period.getYears();
        } else {
            return 0;
        }
    }
    private Long dni;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Directivo() {
    }

    public Directivo(String id, String nombre, String apellido, LocalDate fechaDeNacimiento, Long dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.dni = dni;
    }
}
