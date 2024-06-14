package model;

public class Dietitian {
    private String dietitianId;
    private String name;
    private String specialty;

    public Dietitian(String dietitianId, String name, String specialty) {
        this.dietitianId = dietitianId;
        this.name = name;
        this.specialty = specialty;
    }

    public String getDietitianId() {
        return dietitianId;
    }

    public void setDietitianId(String dietitianId) {
        this.dietitianId = dietitianId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    @Override
    public String toString() {
        return String.format("________________\n ID = %s \n Name = %s \n Speciality = %s ", getDietitianId(), getName(), specialty);
    }
}
