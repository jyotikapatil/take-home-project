package com.database.controller;

import com.database.service.Database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static Database db = new Database();

    public static void main(String[] args) throws IOException {
        processFile("instructions.txt");
        db.printDatabase();
    }

    public static void processFile(String fileName) throws IOException {
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(fileName));
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            return;
        }

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ", 4);
            if (parts.length > 0) {
                String command = parts[0];
                switch (command) {
                    case "ADD":
                        if (parts.length == 4) {
                            String type = parts[1];
                            int id = Integer.parseInt(parts[2]);
                            String value = parts[3];
                            if (type.equals("PATIENT")) {
                                db.addPatient(id, value);
                            } else if (type.equals("EXAM")) {
                                int examID = Integer.parseInt(value);
                                db.addExam(examID, id);
                            }
                        }
                        break;
                    case "DEL":
                        if (parts.length == 3) {
                            String type = parts[1];
                            int id = Integer.parseInt(parts[2]);
                            if (type.equals("PATIENT")) {
                                db.deletePatient(id);
                            } else if (type.equals("EXAM")) {
                                db.deleteExam(id);
                            }
                        }
                        break;
                    default:
                        System.out.println("Invalid command: " + command);
                }
            }
        }
        reader.close();
    }
}
