package energy;

public abstract class EnergySource {
    private String name;
    private int supplyRate;
    private boolean isActive;

    public EnergySource(String name, int supplyRate) {
        this.name = name;
        this.supplyRate = supplyRate;
        this.isActive = false;
    }

    public String getName() {
        return name;
    }

    public int getSupplyRate() {
        return isActive ? supplyRate : 0;
    }

    public void activate() {
        isActive = true;
        System.out.println(name + " is now active.");
    }

    public void deactivate() {
        isActive = false;
        System.out.println(name + " is now inactive.");
    }

    public boolean isActive() {
        return isActive;
    }

    public abstract void generateEnergy();
    
    public void setSupplyRate(int supplyRate) {
        if (supplyRate >= 0) {
            this.supplyRate = supplyRate;
            System.out.println(name + " supply rate adjusted to: " + supplyRate);
        } else {
            System.out.println("Supply rate cannot be negative.");
        }
    }
}