package model;


public class Meal {
    private String name;
    private String macronutrients;
    private int calories;
    private String timeOfDay;

    public Meal(String name, String macronutrients, int calories, String timeOfDay) {
        this.name = name;
        this.macronutrients = macronutrients;
        this.calories = calories;
        this.timeOfDay = timeOfDay;
    }
     // Getters y Setters name
     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // macronutrients
    public String getMacronutrients() {
        return macronutrients;
    }

    public void setMacronutrients(String macronutrients) {
        this.macronutrients = macronutrients;
    }
    // Calories
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
    // Time of day
    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
    @Override
    public String toString() {
        return String.format("_________________________ \n Meal Name: %s \n Macronutrients: %s \n  Calories: %d \n Time of Day: %s \n",
                name, macronutrients, calories, timeOfDay);
    }
}
