package com.database.service;

import com.database.entity.Exam;
import com.database.entity.Patient;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
ASSUMPTIONS:
    1. ADD EXAM PatientID ExamID: One ExamID is associated with one patient. No two patient can have same ExamID
    2. DEL EXAM ID: Delete ExamID from the database and from the patient's exam list for whom the exam was created
    3. Output in printed in ascending order of PatientID
 */

public class Database {

    private Map<Integer, Patient> patientTable; // patientID, Patient obj
    private HashMap<Integer, Exam> examTable; //examID, Exam obj  //(OR eid, patientIDs)


    public Database() {
        this.patientTable = new TreeMap<>();
        this.examTable = new HashMap<>();
    }

    public boolean addPatient(int patientID, String patientName) {
        if (patientTable.containsKey(patientID)) {
//            System.out.println("Patient with ID " + patientID + " already exists");
            return false;
        }
        try {
            patientTable.put(patientID, new Patient(patientID, patientName));
        } catch (Exception e) {
            System.out.println("Error adding patient ID: " + patientID);
            return false;
        }
        return true;
    }

    public boolean addExam(int examID, int patientID) {
        //Check if Patient with the given ID exists
        if (!patientTable.containsKey(patientID)) {
//            System.out.println("No patient with ID: " + patientID);
            return false;
        }

        //Check if Exam with the given ID exists
        if (examTable.containsKey(examID)) {
//            System.out.println("Exam with ID " + examID + " already added for Patient ID " + patientID);
            return false;
        } else {
            try {
                examTable.put(examID, new Exam(examID, patientID));
                patientTable.get(patientID).getExams().add(examID);
            } catch (Exception e) {
                System.out.println("Error adding exam record " + examID + " for patient ID " + patientID);
                return false;
            }
        }
        return true;
    }

    public boolean deletePatient(int patientID) {
        if (patientTable.containsKey(patientID)) {
            Patient patient = patientTable.get(patientID);

            //remove all exams created for the given patient ID
            try {
                for (int examID : patient.getExams()) {
                    examTable.remove(examID);
                }
                //remove patient
                patientTable.remove(patientID);
            } catch (Exception e) {
//                System.out.println("Error removing patient ID:" + patientID);
                return false;
            }
//            System.out.println("Patient with ID " + patientID + " has been deleted.");
            return true;
        } else {
//            System.out.println("Patient with ID " + patientID + " does not exist in the database.");
            return false;
        }
    }


    //ASSUMPTION : Deleting exam id implies deleting it from the patient's exam list
    public boolean deleteExam(int examID) {

        //check if examID exists to delete
        if (examTable.containsKey(examID)) {

            //remove examID from Patient having the given exam
            int patientID = examTable.get(examID).getPatientID();
            try {
                patientTable.get(patientID).getExams().remove(examID);
                examTable.remove(examID);
            } catch (Exception e) {
                System.out.println("Error deleting exam ID: " + examID);
                return false;
            }
            return true;
        } else {
//            System.out.println("No exam with ID " + examID + " to delete.");
            return false;
        }
    }


    public void printDatabase() {
        for (Patient patient : patientTable.values()) {
            System.out.println("Name: " + patient.getName() + ", Id:" + patient.getPatientId() + ", Exam Count:" + patient.getExams().size());
        }
    }

    public Map<Integer, Patient> getPatientTable() {
        return patientTable;
    }

    public void setPatientTable(Map<Integer, Patient> patientTable) {
        this.patientTable = patientTable;
    }

    public HashMap<Integer, Exam> getExamTable() {
        return examTable;
    }

    public void setExamTable(HashMap<Integer, Exam> examTable) {
        this.examTable = examTable;
    }
}
