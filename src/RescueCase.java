package wildliferescosystem;

public abstract class RescueCase implements RescueOperations {
    // Encapsulated common fields
    private String caseId;
    private String animalName;
    private String species;
    private String rescueLocation;
    private String assignedRanger;
    private int rescueDays;
    private double dailyCareCost;
    private String currentStatus;

    public RescueCase(String caseId, String animalName, String species, String rescueLocation, 
                      String assignedRanger, int rescueDays, double dailyCareCost, String currentStatus) {
        this.caseId = caseId;
        this.animalName = animalName;
        this.species = species;
        this.rescueLocation = rescueLocation;
        this.assignedRanger = assignedRanger;
        this.rescueDays = rescueDays;
        this.dailyCareCost = dailyCareCost;
        this.currentStatus = currentStatus;
    }

    // Abstract methods for subclasses to implement (Polymorphism & Overriding)
    public abstract double calculateTotalCost();
    public abstract String determinePriority();
    public abstract void displaySpecificInfo();

    // Getters and Setters for Encapsulation
    public String getCaseId() { return caseId; }
    public void setCaseId(String caseId) { this.caseId = caseId; }
    
    public String getAnimalName() { return animalName; }
    public String getSpecies() { return species; }
    public String getRescueLocation() { return rescueLocation; }
    public String getAssignedRanger() { return assignedRanger; }
    public int getRescueDays() { return rescueDays; }
    public double getDailyCareCost() { return dailyCareCost; }
    
    public String getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(String currentStatus) { this.currentStatus = currentStatus; }
    
    public double getBaseCost() {
        return rescueDays * dailyCareCost;
    }

    @Override
    public void startRescue() {
        this.currentStatus = "Rescue in Progress";
    }

    @Override
    public void completeRescue() {
        this.currentStatus = "Completed";
    }
}