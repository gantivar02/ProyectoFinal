package model;


public class DietPlan {
    private String planId;
    private String patientId;
    private String dietitianId;
    private int dailyCalories;
    private String macronutrientDistribution;
    private String specificRecommendations;

    public DietPlan(String planId, String patientId, String dietitianId, int dailyCalories, String macronutrientDistribution, String specificRecommendations) {
        this.planId = planId;
        this.patientId = patientId;
        this.dietitianId = dietitianId;
        this.dailyCalories = dailyCalories;
        this.macronutrientDistribution = macronutrientDistribution;
        this.specificRecommendations = specificRecommendations;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDietitianId() {
        return dietitianId;
    }

    public void setDietitianId(String dietitianId) {
        this.dietitianId = dietitianId;
    }

    public int getDailyCalories() {
        return dailyCalories;
    }

    public void setDailyCalories(int dailyCalories) {
        this.dailyCalories = dailyCalories;
    }

    public String getMacronutrientDistribution() {
        return macronutrientDistribution;
    }

    public void setMacronutrientDistribution(String macronutrientDistribution) {
        this.macronutrientDistribution = macronutrientDistribution;
    }

    public String getSpecificRecommendations() {
        return specificRecommendations;
    }

    public void setSpecificRecommendations(String specificRecommendations) {
        this.specificRecommendations = specificRecommendations;
    }

    @Override
    public String toString() {
        return String.format(" ______________________________________ \n Plan ID: %s \n Patient ID: %s \n Dietitian ID: %s \n Daily Calories: %d \n Macronutrient Distribution: %s \n Specific Recommendations: %s \n",
                planId, patientId, dietitianId, dailyCalories, macronutrientDistribution, specificRecommendations);
    }
}
