package objects;

public class Light extends SmartObject {
    public Light(String name, int consumptionRate) {
        super(name, consumptionRate);
    }

    @Override
    public void simulate() {
        System.out.println(getName() + " is consuming " + getConsumptionRate() + " energy units.");
    }
}