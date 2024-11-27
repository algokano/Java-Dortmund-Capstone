package energy;

public class WindTurbine extends EnergySource {
    public WindTurbine(String name, int supplyRate) {
        super(name, supplyRate);
    }

    @Override
    public void generateEnergy() {
        System.out.println(getName() + " is generating " + getSupplyRate() + " energy units from wind.");
    }
}