package model;

public class Patient {
    private String patientId;
    private String name;
    private int age;
    private int weight;
    private int height;
    private String preexistingConditions;

    public Patient(String patientId, String name, int age, int weight, int height, String preexistingConditions) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.preexistingConditions = preexistingConditions;
    }

    // Getters and Setters
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPreexistingConditions() {
        return preexistingConditions;
    }

    public void setPreexistingConditions(String preexistingConditions) {
        this.preexistingConditions = preexistingConditions;
    }

    @Override
    public String toString() {
        return String.format("_______________________\n ID = %s \n Name = %s \n Age=%d \n Weight= %d \n Height= %d \n Preexisting Conditions = %s ",
                getPatientId(), getName(), age, weight, height, preexistingConditions);
    }
}
