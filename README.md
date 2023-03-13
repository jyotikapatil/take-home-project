# In-Memory Patient and Exam Database

## Description

This project creates an in-memory database that stores patient details and exam records for patients.

## Commands

The database supports the following commands:

### `ADD PATIENT patientid patientname`

Adds a patient with the given patientid and patientname to the database if a patient with given patientid does not already exist.

### `ADD EXAM patientid examid`

Adds an exam with the given examid to the specified patient's record if the patient with patientid exists and the exam record with given examid does not already exists.

### `DEL PATIENT patientid`

Deletes the patient with given patientid from the database along with all their exam records.

### `DEL EXAM examid`

Deletes the exam with given examid from the database and removes it from the patient's exam list for whom the exam was created.

## Assumptions

In developing this database, follwoing assumptions have been made.

1. Each exam ID is associated with only one patient. No two patients can have the same exam ID.
2. When deleting an exam, it is removed from the database as well as patient's exam list.
3. When printing the output, the list of patients is sorted in ascending order by patient ID.

## Usage

To use this database, simply clone the repository and run the `main.java` file. It line by line runs the commands listed in instructions.txt file. You can add, delete, and query patients and exams using the commands listed above.
Commands must follow the format listed now.
