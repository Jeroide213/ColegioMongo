package com.example.ColegioMongo.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.Period;

@Document
public class Student {
    @Id
    private String id;
    private Long dni;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private byte[] Pdf;
    private String schoolCycle;
    private String speciality;
    private int faults;
    private int banns;
    public int getOld() {
        if (birthDate != null) {
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthDate, currentDate);
            return period.getYears();
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

    public byte[] getPdf() {
        return Pdf;
    }

    public void setPdf(byte[] pdf) {
        Pdf = pdf;
    }

    public String getSchoolCycle() {
        return schoolCycle;
    }

    public void setSchoolCycle(String schoolCycle) {
        this.schoolCycle = schoolCycle;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getFaults() {
        return faults;
    }

    public void setFaults(int faults) {
        this.faults = faults;
    }

    public int getBanns() {
        return banns;
    }

    public void setBanns(int banns) {
        this.banns = banns;
    }

    public Student() {
    }

    public Student(String id, Long dni, String name, String lastName, LocalDate birthDate, byte[] pdf, String schoolCycle, String speciality, int faults, int banns) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        Pdf = pdf;
        this.schoolCycle = schoolCycle;
        this.speciality = speciality;
        this.faults = faults;
        this.banns = banns;
    }
}