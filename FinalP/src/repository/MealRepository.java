package repository;

import java.io.*;
import java.util.*;

import model.Meal;

public class MealRepository {

    public static void saveToCSV(Meal meal, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(String.format("%s,%s,%d,%s\n", meal.getName(), meal.getMacronutrients(), meal.getCalories(), meal.getTimeOfDay()));
        }
    }

    public static void updateMeal(String name, Map<String, String> updatedInfo, String filename) throws IOException {
        List<Meal> meals = loadAll(filename);
        for (Meal meal : meals) {
            if (meal.getName().equals(name)) {
                meal.setMacronutrients(updatedInfo.getOrDefault("macronutrients", meal.getMacronutrients()));
                meal.setCalories(Integer.parseInt(updatedInfo.getOrDefault("calories", String.valueOf(meal.getCalories()))));
                meal.setTimeOfDay(updatedInfo.getOrDefault("timeOfDay", meal.getTimeOfDay()));
            }
        }
        saveAll(meals, filename);
    }

    public static void deleteMeal(String name, String filename) throws IOException {
        List<Meal> meals = loadAll(filename);
        meals.removeIf(meal -> meal.getName().equals(name));
        saveAll(meals, filename);
    }

    public static List<Meal> loadAll(String filename) throws IOException {
        List<Meal> meals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                meals.add(new Meal(fields[0], fields[1], Integer.parseInt(fields[2]), fields[3]));
            }
        }
        return meals;
    }

    public static void saveAll(List<Meal> meals, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Meal meal : meals) {
                writer.write(String.format("%s,%s,%d,%s\n", meal.getName(), meal.getMacronutrients(), meal.getCalories(), meal.getTimeOfDay()));
            }
        }
    }
}
