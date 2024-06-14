
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import model.DietPlan;
import model.Dietitian;
import model.Meal;
import model.Patient;
import repository.DietPlanRepository;
import repository.DietitianRepository;
import repository.MealRepository;
import repository.PatientRepository;

//import Console;

public class MainMenu {

    public void show(){
        while (true){
            Console.writeLine("MENU PRINCIPAL");
            Console.writeLine("1. Agregar Paciente");
            Console.writeLine("2. Actualizar Paciente");
            Console.writeLine("3. Eliminar Paciente");
            Console.writeLine("4. Mostrar Paciente"); // falta
            //Nutricionista
            Console.writeLine("5. Agregar Nutricionista");
            Console.writeLine("6. Actualizar Nutricionista");
            Console.writeLine("7. Eliminar Nutricionista");
            Console.writeLine("8. Mostrar Nutricionista"); // falta
            // Plan de Alimentación
            Console.writeLine("9. Agregar Plan De Alimentación");
            Console.writeLine("10. Actualizar Plan De Alimentación");
            Console.writeLine("11. Eliminar Plan De Alimentación");
            Console.writeLine("12. Mostrar Plan De Alimentación");// falta
            // Comida
            Console.writeLine("13. Agregar Comida ");
            Console.writeLine("14. Actualizar Comida ");
            Console.writeLine("15. Eliminar Comida");
            Console.writeLine("16. Mostrar Comida ");
            
            Console.writeLine("0. Salir");
            System.out.print("Ingrese la opcion: ");

            int opcion = Integer.parseInt(Console.readLine());
            Console.writeLine();  // Consume newline

            switch (opcion) {
                case 1:
                   addPatient();
                    break;
                case 2:
                    updatePatient();
                    break;
                case 3:
                    deletePatient();
                    break;
                case 4:
                    showAllPatients();
                    break;
                case 5:
                    addDietitian();
                    break;
                case 6:
                    updateDietitian();
                    break;
                case 7:
                    deleteDietitian();
                    break;
                case 8:
                    showAllDietitians();
                    break;
                case 9:
                    addDietPlan();
                    break;
                case 10:
                    updateDietPlan();
                    break;
                case 11:
                    deleteDietPlan();
                    break; 
                case 12:
                    showAllDietPlans();
                    break; 
                case 13:
                    addMeal();
                    break; 
                case 14:
                    updateMeal();
                    break;
                case 15:
                    deleteMeal();
                    break; 
                case 16:
                    showAllMeals();
                    break;                
                case 0:
                    Console.writeLine("Salir");
                    System.exit(0);
                default:
                    Console.writeLine("Opción invalida, Por favor intenta nuevamente");
            }
        }

    }
        

        
    

  //____________________________________________________________paciente_______________________________________________________________________

    private static void addPatient() { // agregar paciente
        Console.writeLine("El ID del Paciente es: ");
        Random random = new Random();
        int randomNumber = random.nextInt(1000) + 1;
        String patientId = String.valueOf(randomNumber);
        
        System.out.println(patientId);
        Console.writeLine("Ingrese el nombre: ");
        String name = Console.readLine();
        Console.writeLine("Ingrese la edad: ");
        int age = Integer.parseInt(Console.readLine());
        Console.writeLine("Ingrese el Peso: ");
        int weight = Integer.parseInt(Console.readLine());
        Console.writeLine("Ingrese la Altura en cm: ");
        int height = Integer.parseInt(Console.readLine());
        Console.writeLine("Ingrese si tiene alguna Enfermedad o Condicion Medica: ");
        String preexistingConditions = Console.readLine();

        Patient patient = new Patient(patientId, name, age, weight, height, preexistingConditions);
        try {
            PatientRepository.saveToCSV(patient, "./data/patients.csv");
            Console.writeLine("El Paciente fue Registrado.");
        } catch (IOException e) {
            Console.writeLine("Ocurrio un error al Registrar al Paciente.");
            e.printStackTrace();
        }
    }
// No funciona el update(si se cambia uno se alteran los demas)
   public static void updatePatient() {
    Console.writeLine("Ingrese la ID del Paciente que desea actualizar: ");
    String patientId = Console.readLine();
    File inputFile = new File("./data/patients.csv");
    File tempFile = new File("./data/tempPatients.csv");

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String currentLine;
        boolean found = false;

        while ((currentLine = reader.readLine()) != null) {
            String[] patientData = currentLine.split(",");
            if (patientData[0].equals(patientId)) {
                // Actualizar solo los campos que el usuario proporcionó
                Console.writeLine("Ingrese el nuevo nombre: ");
                String newName = Console.readLine();
                if (!newName.isEmpty()) {
                    patientData[1] = newName;
                }

                Console.writeLine("Ingrese la nueva edad: ");
                String ageInput = Console.readLine();
                if (!ageInput.isEmpty()) {
                    patientData[2] = ageInput;
                }

                Console.writeLine("Ingrese el nuevo peso: ");
                String weightInput = Console.readLine();
                if (!weightInput.isEmpty()) {
                    patientData[3] = weightInput;
                }

                Console.writeLine("Ingrese la nueva altura en cm: ");
                String heightInput = Console.readLine();
                if (!heightInput.isEmpty()) {
                    patientData[4] = heightInput;
                }

                Console.writeLine("Ingrese las nuevas condiciones médicas: ");
                String newConditions = Console.readLine();
                if (!newConditions.isEmpty()) {
                    patientData[5] = newConditions;
                }

                // Eliminar el último campo de la línea
                // patientData = Arrays.copyOf(patientData, patientData.length - 1);

                currentLine = String.join(",", patientData);
                found = true; // Indicar que se encontró y actualizó el paciente
            }
            writer.write(currentLine + System.lineSeparator());
        }

        if (!found) {
            Console.writeLine("No se encontró ningún paciente con la ID proporcionada.");
        } else {
            Console.writeLine("El paciente se ha actualizado correctamente.");
        }
    } catch (IOException e) {
        Console.writeLine("Ocurrió un error al actualizar el paciente");
        e.printStackTrace();
    }

    // Reemplazar el archivo original con el archivo temporal
    if (!inputFile.delete()) {
        Console.writeLine("No se pudo eliminar el archivo original");
        return;
    }
    if (!tempFile.renameTo(inputFile)) {
        Console.writeLine("No se pudo renombrar el archivo temporal");
    }
}

    private static void deletePatient() { // eliminar
        Console.writeLine("Ingrese la ID del paciente que desea Borrar: ");
        String patientId = Console.readLine();
        try {
            PatientRepository.deletePatient(patientId, "./data/patients.csv");
            Console.writeLine("El Paciente se ha borrado Correctamente");
        } catch (IOException e) {
            Console.writeLine("Ocurrió un erro al borrar al Paciente");
            e.printStackTrace();
        }
    }

    private static void showAllPatients() { // mostrar todos los pacientes
        try {
            List<Patient> patients = PatientRepository.loadAll("./data/patients.csv").stream()
                    .filter(person -> person instanceof Patient)
                    .map(person -> (Patient) person)
                    .toList();
            if (patients.isEmpty()) {
                Console.writeLine("No hay pacientes registrados.");
            } else {
                Console.writeLine("Lista de Pacientes:");
                for (Patient patient : patients) {
                    Console.writeLine(patient.toString());
                }
            }
        } catch (IOException e) {
            Console.writeLine("Ocurrió un error al cargar los datos de los pacientes.");
            e.printStackTrace();
        }
    }
    //___________________________________________________________-nutricionista_________________________________________________________________________

    private static void addDietitian() {
        Console.writeLine(" ID del nutricionista es : ");
        Random random = new Random();
        int randomNumber = random.nextInt(1000) + 1;
        String dietitianId = String.valueOf(randomNumber);
        
        System.out.println(dietitianId);
        Console.writeLine("Ingrese el Nombre: ");
        String name = Console.readLine();
        Console.writeLine("Ingrese la Especialidad: ");
        String specialty = Console.readLine();
    
        Dietitian dietitian = new Dietitian(dietitianId, name, specialty);
        try {
            DietitianRepository.saveToCSV(dietitian, "./data/dietitians.csv");
            Console.writeLine("El nutricionista fue Registrado correctamente.");
        } catch (IOException e) {
            Console.writeLine("Ocurrio un error al Registrar al Nutricionista");
            e.printStackTrace();
        }
    }
    /// No funciona el update(si se cambia uno se alteran los demas)
    private static void updateDietitian() {
        Console.writeLine("Ingrese la  ID del Nutricionista que desea actualizar: ");
        String dietitianId = Console.readLine();
        Map<String, String> updatedInfo = new HashMap<>();
        Console.writeLine("Ingrese el nuevo Nombre del Nutricionista: ");
        String name = Console.readLine();
        if (!name.isEmpty()) {
            updatedInfo.put("name", name);
        }
        Console.writeLine("Ingrese la nueva Especialidad del Nutricionista: ");
        String specialty = Console.readLine();
        if (!specialty.isEmpty()) {
            updatedInfo.put("specialty", specialty);
        }
    
        try {
            DietitianRepository.updateDietitian(dietitianId, updatedInfo, "./data/dietitians.csv");
            Console.writeLine("El Nutricionista se ha Actualizado Correctamente");
        } catch (IOException e) {
            Console.writeLine("Ocurrio un Error al Actualizar el Nutricionista");
            e.printStackTrace();
        }
    }
    
    private static void deleteDietitian() {
        Console.writeLine("Ingrese la ID del Nutricionista que desea Borrar: ");
        String dietitianId = Console.readLine();
        try {
            DietitianRepository.deleteDietitian(dietitianId, "./data/dietitians.csv");
            Console.writeLine("El Nutricionista fue Borrado.");
        } catch (IOException e) {
            Console.writeLine("Ocurrio un Error al Borrar al Nutricionista");
            e.printStackTrace();
        }
    }
    
    private static void showAllDietitians() {
        try {
            List<Dietitian> dietitians = DietitianRepository.loadAll("./data/dietitians.csv");
            if (dietitians.isEmpty()) {
                Console.writeLine("No se ha encontrado algun Nutricionista.");
            } else {
                Console.writeLine("Lista de Nutricionistas:");
                for (Dietitian dietitian : dietitians) {
                    Console.writeLine(dietitian.toString());
                }
            }
        } catch (IOException e) {
            Console.writeLine("Ocurrio un Error al cargar la lista de Nutricionistas.");
            e.printStackTrace();
        }
    }
    
//_______________________________________________________________________________________plan de alimentacion________________________________________________________-
    private static void addDietPlan() { // agregar
        Console.writeLine("El ID del plan de dieta es: ");
        Random random = new Random();
        int randomNumber = random.nextInt(1000) + 1;
        String planId = String.valueOf(randomNumber);
        
        System.out.println(planId);
        Console.writeLine("Ingrese la ID del Paciente: ");
        String patientId = Console.readLine();
        Console.writeLine("Ingrese la ID del Nutricionista: ");
        String dietitianId = Console.readLine();
        Console.writeLine("Ingrese la cantidad de Calorias diarias: ");
        int dailyCalories = Integer.parseInt(Console.readLine());
        Console.writeLine(); 
        Console.writeLine("Ingrese la Distribuicon de Macronutrientes: ");
        String macronutrientDistribution = Console.readLine();
        Console.writeLine("Ingrese las Recomendaciones Especificas: ");
        String specificRecommendations = Console.readLine();

        DietPlan dietPlan = new DietPlan(planId, patientId, dietitianId, dailyCalories, macronutrientDistribution, specificRecommendations);
        try {
            DietPlanRepository.saveToCSV(dietPlan, "./data/dietplans.csv");
            Console.writeLine("El plan de Alimentacion se Registro Correctamente");
        } catch (IOException e) {
            Console.writeLine("Ocurrio un Error al guardar el Plan de Alimentacion.");
            e.printStackTrace();
        }
    }
// No funciona el update(si se cambia uno se alteran los demas)
    private static void updateDietPlan() { // actualizar
        Console.writeLine("Ingrese la ID del Plan de Alimentacion que desea Actualizar: ");
        String planId = Console.readLine();
        Map<String, String> updatedInfo = new HashMap<>();
        Console.writeLine("Ingrese la Nueva Cantidad de Calorias Diarias:");
        String dailyCalories = Console.readLine();
        if (!dailyCalories.isEmpty()) {
            updatedInfo.put("dailyCalories", dailyCalories);
        }
        Console.writeLine("Ingrese la Nueva Distribucion de Macronutrientes: ");
        String macronutrientDistribution = Console.readLine();
        if (!macronutrientDistribution.isEmpty()) {
            updatedInfo.put("macronutrientDistribution", macronutrientDistribution);
        }
        Console.writeLine("Ingrese Las Nuevas Recomendaciones Especificas: ");
        String specificRecommendations = Console.readLine();
        if (!specificRecommendations.isEmpty()) {
            updatedInfo.put("specificRecommendations", specificRecommendations);
        }

        try {
            DietPlanRepository.updateDietPlan(planId, updatedInfo, "./data/dietplans.csv");
            Console.writeLine("El Plan de Alimentacion fue Actualizado Correctamente.");
        } catch (IOException e) {
            Console.writeLine("Error al Actualizar el Plan de Alimentacion.");
            e.printStackTrace();
        }
    }

    private static void deleteDietPlan() { // eliminar
        Console.writeLine("Ingrese la ID del PLan de Alimentacion que desea Borrar: ");
        String planId = Console.readLine();
        try {
            DietPlanRepository.deleteDietPlan(planId, "./data/dietplans.csv");
            Console.writeLine("eL Plan de Alimentacion fue borrado Correctamente.");
        } catch (IOException e) {
            Console.writeLine("Ocurrio un error al Borrar el Plan de Alimentacion.");
            e.printStackTrace();
        }
    }
//No funciona, ya que solo muestra el primero pero da error en los siguientes)
    private static void showAllDietPlans() { //mostrar
        try {
            List<DietPlan> dietPlans = DietPlanRepository.loadAll("./data/dietplans.csv");
            if (dietPlans.isEmpty()) {
                Console.writeLine("No se ha encontrado Plan de Alimentacion.");
            } else {
                Console.writeLine("Lista de Plan de Alimentacion:");
                for (DietPlan dietPlan : dietPlans) {
                    Console.writeLine(dietPlan.toString());
                }
            }
        } catch (IOException e) {
            Console.writeLine("Ocurrio un Error al Cargar Plan de Alimentacion.");
            e.printStackTrace();
        }
    }
    
   //_______________________________________________________Comida_________________________________________________________________________________
    
   private static void addMeal() { // agregar
        Console.writeLine("Ingrese El nombre de la Comida: ");
        String name = Console.readLine();
        Console.writeLine("Ingrese los Macronutrientes: ");
        String macronutrients = Console.readLine();
        Console.writeLine("Ingrese las Calorias: ");
        int calories = Integer.parseInt(Console.readLine());
        Console.writeLine();  
        Console.writeLine("Ingrese la hora del Dia (Dia/Tarde/Noche): ");
        String timeOfDay = Console.readLine();

        Meal meal = new Meal(name, macronutrients, calories, timeOfDay);
        try {
            MealRepository.saveToCSV(meal, "./data/meals.csv");
            Console.writeLine("La comida se ha Registrado.");
        } catch (IOException e) {
            Console.writeLine("Ocurrio un Error al guardar la Comida.");
            e.printStackTrace();
        }
    }
/// No funciona el update(si se cambia uno se alteran los demas)
    private static void updateMeal() { // actualizar
        Console.writeLine("Ingrese el nombre de  Comida: ");
        String name = Console.readLine();
        Map<String, String> updatedInfo = new HashMap<>();
        Console.writeLine("Ingrese los Nuevos Macronutrientes: ");
        String macronutrients = Console.readLine();
        if (!macronutrients.isEmpty()) {
            updatedInfo.put("macronutrients", macronutrients);
        }
        Console.writeLine("Ingrese la Nueva Cantidad de Calorias: ");
        String calories = Console.readLine();
        if (!calories.isEmpty()) {
            updatedInfo.put("calories", calories);
        }
        Console.writeLine("Ingrese la Nueva hora del Dia (Dia/Tarde/Noche): ");
        String timeOfDay = Console.readLine();
        if (!timeOfDay.isEmpty()) {
            updatedInfo.put("timeOfDay", timeOfDay);
        }

        try {
            MealRepository.updateMeal(name, updatedInfo, "./data/meals.csv");
            Console.writeLine("La Comida se ha Actualizado.");
        } catch (IOException e) {
            Console.writeLine("Error al Actualizar la Comida.");
            e.printStackTrace();
        }
    }

    private static void deleteMeal() { // eliminar
        Console.writeLine("Ingrese el nombre de la Comida que desea Borrar: ");
        String name = Console.readLine();
        try {
            MealRepository.deleteMeal(name, "./data/meals.csv");
            Console.writeLine("La Comida fue Borrada.");
        } catch (IOException e) {
            Console.writeLine("Error al Borrar la Comida.");
            e.printStackTrace();
        }
    }

    private static void showAllMeals() { // mostrar
        try {
            List<Meal> meals = MealRepository.loadAll("./data/meals.csv");
            if (meals.isEmpty()) {
                Console.writeLine("No se ha encontrado Ninguna Comida.");
            } else {
                Console.writeLine("Lista de Comidas:");
                for (Meal meal : meals) {
                    Console.writeLine(meal.toString());
                }
            }
        } catch (IOException e) {
            Console.writeLine("Ocurrio un Error Al Cargar las comidas.");
            e.printStackTrace();
        }
    }
    
}