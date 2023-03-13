package com.database.entity;

import java.util.Set;

public class Patient {

    private int patientId;
    private String name;
    private Set<Integer> exams;

    public Patient(int patientId, String name, Set<Integer> exams) {
        this.patientId = patientId;
        this.name = name;
        this.exams = exams;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Integer> getExams() {
        return exams;
    }

    public void setExams(Set<Integer> exams) {
        this.exams = exams;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", exams=" + exams +
                '}';
    }
}
