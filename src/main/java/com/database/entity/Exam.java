package com.database.entity;

public class Exam {

    private int examID;
    int patientID;

    public Exam(int examID, int patientID) {
        this.examID = examID;
        this.patientID = patientID;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examID=" + examID +
                ", patientID=" + patientID +  //TODO
                '}';
    }
}
