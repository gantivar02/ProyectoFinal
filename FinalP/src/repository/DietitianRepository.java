package repository;



import java.io.*;
import java.util.*;

import model.Dietitian;

public class DietitianRepository {

    public static void saveToCSV(Dietitian dietitian, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(String.format("%s,%s,%s\n", dietitian.getDietitianId(), dietitian.getName(), dietitian.getSpecialty()));
        }
    }

    public static void updateDietitian(String dietitianId, Map<String, String> updatedInfo, String filename) throws IOException {
        List<Dietitian> dietitians = loadAll(filename);
        for (Dietitian dietitian : dietitians) {
            if (dietitian.getDietitianId().equals(dietitianId)) {
                dietitian.setName(updatedInfo.getOrDefault("name", dietitian.getName()));
                dietitian.setSpecialty(updatedInfo.getOrDefault("specialty", dietitian.getSpecialty()));
            }
        }
        saveAll(dietitians, filename);
    }

    public static void deleteDietitian(String dietitianId, String filename) throws IOException {
        List<Dietitian> dietitians = loadAll(filename);
        dietitians.removeIf(dietitian -> dietitian.getDietitianId().equals(dietitianId));
        saveAll(dietitians, filename);
    }

    public static List<Dietitian> loadAll(String filename) throws IOException {
        List<Dietitian> dietitians = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                dietitians.add(new Dietitian(fields[0], fields[1], fields[2]));
            }
        }
        return dietitians;
    }

    public static void saveAll(List<Dietitian> dietitians, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Dietitian dietitian : dietitians) {
                writer.write(String.format("%s,%s,%s\n", dietitian.getDietitianId(), dietitian.getName(), dietitian.getSpecialty()));
            }
        }
    }
}
