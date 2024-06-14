package repository;


import java.io.*;
import java.util.*;

import model.DietPlan;

public class DietPlanRepository {

    public static void saveToCSV(DietPlan dietPlan, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(String.format("%s,%s,%s,%d,%s,%s\n", dietPlan.getPlanId(), dietPlan.getPatientId(), dietPlan.getDietitianId(),
                    dietPlan.getDailyCalories(), dietPlan.getMacronutrientDistribution(), dietPlan.getSpecificRecommendations()));
        }
    }

    public static void updateDietPlan(String planId, Map<String, String> updatedInfo, String filename) throws IOException {
        List<DietPlan> dietPlans = loadAll(filename);
        for (DietPlan dietPlan : dietPlans) {
            if (dietPlan.getPlanId().equals(planId)) {
                dietPlan.setDailyCalories(Integer.parseInt(updatedInfo.getOrDefault("dailyCalories", String.valueOf(dietPlan.getDailyCalories()))));
                dietPlan.setMacronutrientDistribution(updatedInfo.getOrDefault("macronutrientDistribution", dietPlan.getMacronutrientDistribution()));
                dietPlan.setSpecificRecommendations(updatedInfo.getOrDefault("specificRecommendations", dietPlan.getSpecificRecommendations()));
            }
        }
        saveAll(dietPlans, filename);
    }

    public static void deleteDietPlan(String planId, String filename) throws IOException {
        List<DietPlan> dietPlans = loadAll(filename);
        dietPlans.removeIf(dietPlan -> dietPlan.getPlanId().equals(planId));
        saveAll(dietPlans, filename);
    }

    public static List<DietPlan> loadAll(String filename) throws IOException {
        List<DietPlan> dietPlans = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                dietPlans.add(new DietPlan(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), fields[4], fields[5]));
            }
        }
        return dietPlans;
    }

    public static void saveAll(List<DietPlan> dietPlans, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) { // Cambio en el FileWriter
            for (DietPlan dietPlan : dietPlans) {
                writer.write(String.format("%s,%s,%s,%d,%s,%s\n", dietPlan.getPlanId(), dietPlan.getPatientId(), dietPlan.getDietitianId(),
                        dietPlan.getDailyCalories(), dietPlan.getMacronutrientDistribution(), dietPlan.getSpecificRecommendations()));
            }
        }
    }
}
