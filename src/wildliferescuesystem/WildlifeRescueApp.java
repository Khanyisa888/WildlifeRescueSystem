package wildliferescosystem;

import java.util.ArrayList;
import java.util.Scanner;

public class WildlifeRescueApp {
    private static ArrayList<RescueCase> rescueList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = getValidIntInput("Select an option (1-6): ", 1, 6);

            switch (choice) {
                case 1:
                    createRescueCase();
                    break;
                case 2:
                    searchRescueCase();
                    break;
                case 3:
                    updateRescueStatus();
                    break;
                case 4:
                    displayAllCases();
                    break;
                case 5:
                    generateRescueReport();
                    break;
                case 6:
                    System.out.println("Exiting Wildlife Rescue System. Thank you!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 6.");
            }
        } while (choice != 6);
    }

    private static void printMenu() {
        System.out.println("\n==========================================");
        System.out.println("     WILDLIFE RESCUE OPERATIONS SYSTEM    ");
        System.out.println("==========================================");
        System.out.println("1. Create Rescue Case");
        System.out.println("2. Search Rescue Case");
        System.out.println("3. Update Rescue Status (Start/Complete)");
        System.out.println("4. Display All Rescue Cases");
        System.out.println("5. Rescue Report");
        System.out.println("6. Exit");
    }

    private static void createRescueCase() {
        System.out.println("\n--- Create New Rescue Case ---");
        
        // Validation: Rescue Case ID must not be blank and must be unique
        String caseId;
        while (true) {
            System.out.print("Enter Rescue Case ID (e.g., WR101): ");
            caseId = scanner.nextLine().trim();
            if (caseId.isEmpty()) {
                System.out.println("Error: Case ID cannot be blank.");
                continue;
            }
            if (findCaseById(caseId) != null) {
                System.out.println("Error: A rescue case with ID " + caseId + " already exists. ID must be unique.");
            } else {
                break;
            }
        }

        System.out.print("Enter Animal Name: ");
        String animalName = scanner.nextLine().trim();

        // Validation: Species not blank
        String species;
        while (true) {
            System.out.print("Enter Species: ");
            species = scanner.nextLine().trim();
            if (!species.isEmpty()) break;
            System.out.println("Error: Species cannot be blank.");
        }

        // Validation: Rescue Location not blank
        String location;
        while (true) {
            System.out.print("Enter Rescue Location: ");
            location = scanner.nextLine().trim();
            if (!location.isEmpty()) break;
            System.out.println("Error: Rescue Location cannot be blank.");
        }

        // Validation: Assigned Ranger not blank[cite: 1]
        String ranger;
        while (true) {
            System.out.print("Enter Assigned Ranger: ");
            ranger = scanner.nextLine().trim();
            if (!ranger.isEmpty()) break;
            System.out.println("Error: Assigned Ranger cannot be blank.");
        }

        int rescueDays = getValidPositiveInt("Enter Number of Rescue Days (>0): ");
        double dailyCost = getValidPositiveDouble("Enter Daily Care Cost (>0): R");

        System.out.println("\nSelect Rescue Type:");
        System.out.println("1. Injured Animal Rescue");
        System.out.println("2. Orphaned Animal Rescue");
        System.out.println("3. Endangered Species Rescue");
        int typeChoice = getValidIntInput("Enter choice (1-3): ", 1, 3);

        RescueCase newCase = null;

        if (typeChoice == 1) {
            System.out.print("Enter Injury Description: ");
            String injury = scanner.nextLine().trim();
            double vetCost = getValidPositiveDouble("Enter Veterinary Treatment Cost: R");
            boolean surgery = getYesNoInput("Is surgery required? (y/n): ");
            
            newCase = new InjuredAnimalRescue(caseId, animalName, species, location, ranger, 
                                               rescueDays, dailyCost, "Under Observation", injury, vetCost, surgery);
        } else if (typeChoice == 2) {
            int age = getValidPositiveInt("Enter Estimated Age (Months): ");
            double feedingCost = getValidPositiveDouble("Enter Feeding Cost: R");
            boolean foster = getYesNoInput("Is foster care required? (y/n): ");
            
            newCase = new OrphanedAnimalRescue(caseId, animalName, species, location, ranger, 
                                                rescueDays, dailyCost, "Under Observation", age, feedingCost, foster);
        } else if (typeChoice == 3) {
            System.out.print("Enter Conservation Classification: ");
            String classification = scanner.nextLine().trim();
            double securityCost = getValidPositiveDouble("Enter Security Cost: R");
            boolean specialist = getYesNoInput("Is a specialist team required? (y/n): ");
            
            newCase = new EndangeredSpeciesRescue(caseId, animalName, species, location, ranger, 
                                                  rescueDays, dailyCost, "Under Observation", classification, securityCost, specialist);
        }

        if (newCase != null) {
            rescueList.add(newCase);
            System.out.println("Successfully created and saved rescue case: " + caseId);
        }
    }

    private static void searchRescueCase() {
        System.out.print("\nEnter Rescue Case ID to search: ");
        String searchId = scanner.nextLine().trim();
        RescueCase found = findCaseById(searchId);

        if (found != null) {
            System.out.println("\n--- Case Found ---");
            System.out.println(found.generateSummary());
            found.displaySpecificInfo();
        } else {
            System.out.println("No rescue case found with ID: " + searchId);
        }
    }

    private static void updateRescueStatus() {
        System.out.print("\nEnter Rescue Case ID to update operation status: ");
        String searchId = scanner.nextLine().trim();
        RescueCase found = findCaseById(searchId);

        if (found != null) {
            System.out.println("Current Status: " + found.getCurrentStatus());
            System.out.println("1. Start Rescue Operation");
            System.out.println("2. Complete Rescue Operation");
            int opChoice = getValidIntInput("Select operation (1-2): ", 1, 2);

            if (opChoice == 1) {
                found.startRescue();
                System.out.println("Rescue operation started. Status updated to: " + found.getCurrentStatus());
            } else {
                found.completeRescue();
                System.out.println("Rescue operation completed. Status updated to: " + found.getCurrentStatus());
            }
        } else {
            System.out.println("Rescue case ID not found.");
        }
    }

    private static void displayAllCases() {
        if (rescueList.isEmpty()) {
            System.out.println("\nNo rescue cases recorded yet.");
            return;
        }
        System.out.println("\n--- All Recorded Rescue Cases ---");
        for (RescueCase c : rescueList) {
            System.out.println(c.generateSummary());
        }
    }

    private static void generateRescueReport() {
        if (rescueList.isEmpty()) {
            System.out.println("\nNo cases available to generate report.");
            return;
        }

        System.out.println("\n==========================================");
        System.out.println("          WILDLIFE RESCUE REPORT          ");
        System.out.println("==========================================");

        double totalCostAll = 0.0;
        for (RescueCase c : rescueList) {
            // Determine type string dynamically
            String typeStr = "Unknown";
            if (c instanceof InjuredAnimalRescue) typeStr = "Injured Animal Rescue";
            else if (c instanceof OrphanedAnimalRescue) typeStr = "Orphaned Animal Rescue";
            else if (c instanceof EndangeredSpeciesRescue) typeStr = "Endangered Species Rescue";

            System.out.println("Case ID     : " + c.getCaseId());
            System.out.println("Type        : " + typeStr);
            System.out.println("Species     : " + c.getSpecies());
            System.out.println("Priority    : " + c.determinePriority());
            System.out.println("Status      : " + c.getCurrentStatus());
            System.out.printf("Total Cost  : R%.2f\n", c.calculateTotalCost());
            System.out.println("------------------------------------------");

            totalCostAll += c.calculateTotalCost();
        }

        System.out.println("Total Rescue Cases : " + rescueList.size());
        System.out.printf("Total Rescue Cost  : R%.2f\n", totalCostAll);
        System.out.println("==========================================");
    }

    private static RescueCase findCaseById(String id) {
        for (RescueCase c : rescueList) {
            if (c.getCaseId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    // Helper validation methods to secure validation marks[cite: 1]
    private static int getValidIntInput(String prompt, int min, int max) {
        int val;
        while (true) {
            System.out.print(prompt);
            try {
                val = Integer.parseInt(scanner.nextLine().trim());
                if (val >= min && val <= max) return val;
                System.out.println("Error: Enter a number between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            }
        }
    }

    private static int getValidPositiveInt(String prompt) {
        int val;
        while (true) {
            System.out.print(prompt);
            try {
                val = Integer.parseInt(scanner.nextLine().trim());
                if (val > 0) return val;
                System.out.println("Error: Value must be greater than zero.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid whole number.");
            }
        }
    }

    private static double getValidPositiveDouble(String prompt) {
        double val;
        while (true) {
            System.out.print(prompt);
            try {
                val = Double.parseDouble(scanner.nextLine().trim());
                if (val > 0) return val;
                System.out.println("Error: Amount must be greater than zero.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }

    private static boolean getYesNoInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) return true;
            if (input.equals("n") || input.equals("no")) return false;
            System.out.println("Error: Please enter 'y' or 'n'.");
        }
    }
}