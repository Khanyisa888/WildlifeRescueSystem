package wildliferescosystem;

public class EndangeredSpeciesRescue extends RescueCase {
    private String conservationClassification;
    private double securityCost;
    private boolean specialistTeamRequired;

    public EndangeredSpeciesRescue(String caseId, String animalName, String species, String rescueLocation,
                                   String assignedRanger, int rescueDays, double dailyCareCost, String currentStatus,
                                   String conservationClassification, double securityCost, boolean specialistTeamRequired) {
        super(caseId, animalName, species, rescueLocation, assignedRanger, rescueDays, dailyCareCost, currentStatus);
        this.conservationClassification = conservationClassification;
        this.securityCost = securityCost;
        this.specialistTeamRequired = specialistTeamRequired;
    }

    @Override
    public double calculateTotalCost() {
        double total = getBaseCost() + securityCost;
        if (specialistTeamRequired) {
            total += 8000.0;
        }
        return total;
    }

    @Override
    public String determinePriority() {
        return specialistTeamRequired ? "Critical" : "High";
    }

    @Override
    public void displaySpecificInfo() {
        System.out.println("Conservation Classification: " + conservationClassification);
        System.out.println("Security Cost: R" + securityCost);
        System.out.println("Specialist Team Required: " + (specialistTeamRequired ? "Yes" : "No"));
    }

    @Override
    public String generateSummary() {
        return String.format("ID: %s | Type: Endangered Species | Species: %s | Ranger: %s | Priority: %s | Status: %s | Total Cost: R%.2f",
                getCaseId(), getSpecies(), getAssignedRanger(), determinePriority(), getCurrentStatus(), calculateTotalCost());
    }
}