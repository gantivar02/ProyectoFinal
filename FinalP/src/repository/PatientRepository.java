package repository;

import java.io.*;
import java.util.*;

import model.Patient;

public class PatientRepository {

    public static void saveToCSV(Patient patient, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(String.format("%s,%s,%d,%d,%d,%s\n",
                    patient.getPatientId(), patient.getName(), patient.getAge(),
                    patient.getWeight(), patient.getHeight(), patient.getPreexistingConditions()));
        }
    }

    public static void updatePatient(String patientId, Map<String, String> updatedInfo, String filename) throws IOException {
        List<Patient> patients = loadAll(filename);
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(patientId)) {
                patient.setName(updatedInfo.getOrDefault("name", patient.getName()));
                patient.setAge(Integer.parseInt(updatedInfo.getOrDefault("age", String.valueOf(patient.getAge()))));
                patient.setWeight(Integer.parseInt(updatedInfo.getOrDefault("weight", String.valueOf(patient.getWeight()))));
                patient.setHeight(Integer.parseInt(updatedInfo.getOrDefault("height", String.valueOf(patient.getHeight()))));
                patient.setPreexistingConditions(updatedInfo.getOrDefault("preexistingConditions", patient.getPreexistingConditions()));
            }
        }
        saveAll(patients, filename);
    }

    public static void deletePatient(String patientId, String filename) throws IOException {
        List<Patient> patients = loadAll(filename);
        patients.removeIf(patient -> patient.getPatientId().equals(patientId));
        saveAll(patients, filename);
    }
 

    public static List<Patient> loadAll(String filename) throws IOException {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 6) {
                    // Aseguramos que hay suficientes campos para crear un paciente
                    Patient patient = new Patient(fields[0].trim(), fields[1].trim(), 
                                                  Integer.parseInt(fields[2].trim()),
                                                  Integer.parseInt(fields[3].trim()),
                                                  Integer.parseInt(fields[4].trim()), 
                                                  fields[5].trim());
                    patients.add(patient);
                } else {
                    // Manejar caso donde la línea no tiene suficientes campos
                    System.err.println("Línea no válida: " + line);
                    // Puedes decidir ignorar la línea o manejar el error de otra manera
                }
            }
        }
        return patients;
    }

    public static void saveAll(List<Patient> patients, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Patient patient : patients) {
                writer.write(String.format("%s,%s,%d,%d,%d,%s\n",
                        patient.getPatientId(), patient.getName(), patient.getAge(),
                        patient.getWeight(), patient.getHeight(), patient.getPreexistingConditions()));
            }
        }
    }
}
