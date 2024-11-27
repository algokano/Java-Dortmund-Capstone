package objects;

public class Heater extends SmartObject {
    public Heater(String name, int consumptionRate) {
        super(name, consumptionRate);
    }

    @Override
    public void simulate() {
        System.out.println(getName() + " is consuming " + getConsumptionRate() + " energy units.");
    }
}