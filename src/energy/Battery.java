package energy;

public class Battery extends EnergySource {
    private int capacity;
    private int currentCharge;

    public Battery(String name, int supplyRate, int capacity) {
        super(name, supplyRate);
        this.capacity = capacity;
        this.currentCharge = 0;
    }

    public int getCurrentCharge() {
        return currentCharge;
    }

    public void chargeBattery(int amount) {
        currentCharge = Math.min(currentCharge + amount, capacity);
        System.out.println(getName() + " is charged to " + currentCharge + " units.");
    }

    @Override
    public void generateEnergy() {
        System.out.println(getName() + " supplies " + getSupplyRate() + " energy units.");
    }
}