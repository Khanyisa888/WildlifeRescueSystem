package wildliferescosystem;

public class InjuredAnimalRescue extends RescueCase {
    // Additional fields specific to Injured Animal Rescue
    private String injuryDescription;
    private double vetTreatmentCost;
    private boolean surgeryRequired;

    public InjuredAnimalRescue(String caseId, String animalName, String species, String rescueLocation,
                               String assignedRanger, int rescueDays, double dailyCareCost, String currentStatus,
                               String injuryDescription, double vetTreatmentCost, boolean surgeryRequired) {
        super(caseId, animalName, species, rescueLocation, assignedRanger, rescueDays, dailyCareCost, currentStatus);
        this.injuryDescription = injuryDescription;
        this.vetTreatmentCost = vetTreatmentCost;
        this.surgeryRequired = surgeryRequired;
    }

    @Override
    public double calculateTotalCost() {
        double total = getBaseCost() + vetTreatmentCost;
        if (surgeryRequired) {
            total += 5000.0;
        }
        return total;
    }

    @Override
    public String determinePriority() {
        return surgeryRequired ? "Critical" : "High";
    }

    @Override
    public void displaySpecificInfo() {
        System.out.println("Injury Description: " + injuryDescription);
        System.out.println("Vet Treatment Cost: R" + vetTreatmentCost);
        System.out.println("Surgery Required: " + (surgeryRequired ? "Yes" : "No"));
    }

    @Override
    public String generateSummary() {
        return String.format("ID: %s | Type: Injured Animal | Species: %s | Ranger: %s | Priority: %s | Status: %s | Total Cost: R%.2f",
                getCaseId(), getSpecies(), getAssignedRanger(), determinePriority(), getCurrentStatus(), calculateTotalCost());
    }
}