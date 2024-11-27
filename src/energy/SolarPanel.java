package energy;

public class SolarPanel extends EnergySource {
    public SolarPanel(String name, int supplyRate) {
        super(name, supplyRate);
    }

    @Override
    public void generateEnergy() {
        System.out.println(getName() + " is generating " + getSupplyRate() + " energy units.");
    }
}