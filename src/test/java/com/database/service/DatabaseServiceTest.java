package com.database.service;

import com.database.entity.Patient;
import com.database.service.Database;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseServiceTest {

    @Test
    public void testAddPatient() {
        // Create a new database instance
        Database db = new Database();

        // Add a new patient to the database
        boolean result = db.addPatient(123, "John Doe");

        // Verify that the patient was added successfully
        assertTrue(result);
        assertTrue(db.getPatientTable().containsKey(123));
        assertEquals("John Doe", db.getPatientTable().get(123).getName());

        // Edge case
        // Attempt to add the same patientID again with different name
        result = db.addPatient(123, "John Snow");

        // Verify that the second attempt failed and the patient table was not modified
        assertFalse(result);
        assertEquals("John Doe", db.getPatientTable().get(123).getName());
    }

    @Test
    public void testAddExam() {
        // Create a new database instance
        Database db = new Database();

        // Add a patient to the database
        db.addPatient(123, "John Doe");

        // Add a new exam for the patient
        boolean result = db.addExam(444, 123);

        // Verify that the exam was added successfully
        assertTrue(result);
        assertTrue(db.getExamTable().containsKey(444));
        assertEquals(123, db.getExamTable().get(444).getPatientID());
        assertEquals(1, db.getPatientTable().get(123).getExams().size());
        assertTrue(db.getPatientTable().get(123).getExams().contains(444));

        // Attempt to add the same exam for the same patient again
        result = db.addExam(444, 123);

        // Verify that the second attempt failed and the exam was not modified
        assertFalse(result);
        assertEquals(1, db.getPatientTable().get(123).getExams().size());

        // Attempt to add an exam for a non-existent patient
        result = db.addExam(555, 456);

        // Verify that the third attempt failed and no exam or patient was modified
        assertFalse(result);
        assertFalse(db.getExamTable().containsKey(555));
        assertFalse(db.getPatientTable().containsKey(456));
    }

    @Test
    public void testDeleteExistingPatient() {
        // Create a new database instance
        Database db = new Database();

        // Add a patient to the database
        db.addPatient(123, "John Doe");

        // Add an exam for the patient
        db.addExam(444, 123);

        // Delete the patient from the database
        boolean result = db.deletePatient(123);

        // Verify that the patient and exam were deleted successfully
        assertTrue(result);
        assertFalse(db.getPatientTable().containsKey(123));
        assertFalse(db.getExamTable().containsKey(444));
        assertEquals(0, db.getPatientTable().size());
        assertEquals(0, db.getExamTable().size());

        // Attempt to delete the same patient again
        result = db.deletePatient(123);

        // Verify that the second attempt failed and the database was not modified
        assertFalse(result);
        assertFalse(db.getPatientTable().containsKey(123));
        assertFalse(db.getExamTable().containsKey(444));
        assertEquals(0, db.getPatientTable().size());
        assertEquals(0, db.getExamTable().size());


    }

    @Test
    public void testDeleteNonExistingPatient(){
        Database db = new Database();
        // Attempt to delete non existing patient
        boolean result = db.deletePatient(456);

        // Verify that the attempt failed and the database was not modified
        assertFalse(result);
        assertFalse(db.getPatientTable().containsKey(456));
        assertEquals(0, db.getPatientTable().size());
    }

    @Test
    public void testDeleteExistingExam() {
        Database database = new Database();
        database.addPatient(123, "John Doe");
        database.addExam(444, 123);

        boolean result = database.deleteExam(444);
        assertTrue(result);
        assertFalse(database.getExamTable().containsKey(444));
        assertFalse(database.getPatientTable().get(123).getExams().contains(444));
        assertEquals(0, database.getPatientTable().get(123).getExams().size());
    }

    @Test
    public void testDeleteNonExistingExam() {
        Database database = new Database();
        database.addPatient(123, "John Doe");
        database.addExam(444, 123);

        boolean result = database.deleteExam(555);
        assertFalse(result);
        assertTrue(database.getExamTable().containsKey(444));
        assertEquals(1, database.getPatientTable().get(123).getExams().size());
    }
}

