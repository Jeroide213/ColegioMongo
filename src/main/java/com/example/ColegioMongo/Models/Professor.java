package com.example.ColegioMongo.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Period;

@Document
public class Professor {
    @Id
    private String id;
    private Long dni;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private int antiquity;
    private byte[] registrationTag;
    public int getOld() {
        if (birthDate != null) {
            LocalDate ahora = LocalDate.now();
            return Period.between(birthDate, ahora).getYears();
        } else {
            return 0;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getAntiquity() {
        return antiquity;
    }

    public void setAntiquity(int antiquity) {
        this.antiquity = antiquity;
    }

    public byte[] getRegistrationTag() {
        return registrationTag;
    }

    public void setRegistrationTag(byte[] registrationTag) {
        this.registrationTag = registrationTag;
    }

    public Professor() {
    }

    public Professor(String id, Long dni, String name, String lastName, LocalDate birthDate, int antiquity, byte[] registrationTag) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.antiquity = antiquity;
        this.registrationTag = registrationTag;
    }
}
