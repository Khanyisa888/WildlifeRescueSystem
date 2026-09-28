package wildliferescosystem;

public class OrphanedAnimalRescue extends RescueCase {
    private int estimatedAgeMonths;
    private double feedingCost;
    private boolean fosterCareRequired;

    public OrphanedAnimalRescue(String caseId, String animalName, String species, 
                                String rescueLocation, String assignedRanger, 
                                int rescueDays, double dailyCareCost, String currentStatus,
                                int estimatedAgeMonths, double feedingCost, boolean fosterCareRequired) {
        super(caseId, animalName, species, rescueLocation, assignedRanger, rescueDays, dailyCareCost, currentStatus);
        this.estimatedAgeMonths = estimatedAgeMonths;
        this.feedingCost = feedingCost;
        this.fosterCareRequired = fosterCareRequired;
    }

    public int getEstimatedAgeMonths() { return estimatedAgeMonths; }
    public void setEstimatedAgeMonths(int estimatedAgeMonths) { this.estimatedAgeMonths = estimatedAgeMonths; }

    public double getFeedingCost() { return feedingCost; }
    public void setFeedingCost(double feedingCost) { this.feedingCost = feedingCost; }

    public boolean isFosterCareRequired() { return fosterCareRequired; }
    public void setFosterCareRequired(boolean fosterCareRequired) { this.fosterCareRequired = fosterCareRequired; }

    @Override
    public double calculateTotalCost() {
        double baseCost = getRescueDays() * getDailyCareCost();
        double total = baseCost + feedingCost;
        if (fosterCareRequired) {
            total += 500.0;
        }
        return total;
    }

    @Override
    public String determinePriority() {
        if (estimatedAgeMonths < 3 || fosterCareRequired) {
            return "High";
        } else if (estimatedAgeMonths <= 6) {
            return "Medium";
        } else {
            return "Low";
        }
    }

    @Override
    public void displaySpecificInfo() {
        System.out.println("Estimated Age (Months): " + estimatedAgeMonths);
        System.out.println("Feeding Cost: R" + feedingCost);
        System.out.println("Foster Care Required: " + (fosterCareRequired ? "Yes" : "No"));
    }

    @Override
    public String generateSummary() {
        return String.format("ID: %s | Type: Orphaned | Species: %s | Ranger: %s | Status: %s | Priority: %s | Total Cost: R%.2f",
                getCaseId(), getSpecies(), getAssignedRanger(), getCurrentStatus(), determinePriority(), calculateTotalCost());
    }
}