package objects;

public class SmartAppliance extends SmartObject {
    public SmartAppliance(String name, int consumptionRate) {
        super(name, consumptionRate);
    }

    @Override
    public void simulate() {
        System.out.println(getName() + " is consuming " + getConsumptionRate() + " energy units.");
    }
}