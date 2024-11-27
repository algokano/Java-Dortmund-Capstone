package management;

import energy.EnergySource;
import objects.SmartObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EnergyManager {
    private List<EnergySource> energySources;
    private List<SmartObject> smartObjects;

    public EnergyManager(List<EnergySource> energySources) {
        this.energySources = new ArrayList<>(energySources);
    }
    
    public EnergyManager(List<SmartObject> smartObjects, List<EnergySource> energySources) {
        this.smartObjects = new ArrayList<>(smartObjects); 
        this.energySources = new ArrayList<>(energySources);
    }

    public void addEnergySource(EnergySource source) {
        energySources.add(source);
        System.out.println(source.getName() + " added successfully.");
    }

    public void removeEnergySource(String sourceName) {
        boolean removed = energySources.removeIf(source -> source.getName().equals(sourceName));
        if (removed) {
            System.out.println(sourceName + " removed successfully.");
        } else {
            System.out.println("Energy source '" + sourceName + "' not found.");
        }
    }
    public void activateAllSources() {
        for (EnergySource source : energySources) {
            source.activate();
        }
    }
    public void distributeEnergy() {
        // Calculate total available energy
        int totalSupply = energySources.stream()
                                       .filter(EnergySource::isActive) // Only active sources
                                       .mapToInt(EnergySource::getSupplyRate)
                                       .sum();

        System.out.println("Total energy supply: " + totalSupply);

        // Calculate total energy required
        int totalConsumption = smartObjects.stream()
                                           .filter(SmartObject::isOn) // Only ON devices
                                           .mapToInt(SmartObject::getConsumptionRate)
                                           .sum();

        System.out.println("Total energy required: " + totalConsumption);

        // Distribute energy
        if (totalSupply >= totalConsumption) {
            System.out.println("Sufficient energy available. All devices will operate normally.");
            smartObjects.stream()
                .filter(SmartObject::isOn)
                .forEach(device -> {
                    device.setPowered(true); // Mark device as powered
                    System.out.println(device.getName() + " is fully powered (consumption: " + device.getConsumptionRate() + ").");
                });
        } else {
            System.out.println("Insufficient energy! Allocating energy with priority.");
            allocateEnergyWithPriority(totalSupply); // Handle energy shortage
        }
    }
    private void allocateEnergyWithPriority(int totalSupply) {
        AtomicInteger remainingSupply = new AtomicInteger(totalSupply);

        smartObjects.stream()
                    .filter(SmartObject::isOn) // Only devices that are ON
                    .sorted((d1, d2) -> Integer.compare(d1.getConsumptionRate(), d2.getConsumptionRate())) // Prioritize lower consumption
                    .forEach(device -> {
                        if (remainingSupply.get() >= device.getConsumptionRate()) {
                            remainingSupply.addAndGet(-device.getConsumptionRate()); // Subtract consumption
                            device.setPowered(true); // Mark device as powered
                            System.out.println(device.getName() + " is powered (consumption: " + device.getConsumptionRate() + ").");
                        } else {
                            device.setPowered(false); // Not enough energy
                            System.out.println(device.getName() + " is not powered due to insufficient energy.");
                        }
                    });

        System.out.println("Remaining supply after allocation: " + remainingSupply.get());
    }
    public void showEnergySources() {
        System.out.println("--- Energy Sources ---");
        if (energySources.isEmpty()) {
            System.out.println("No energy sources available.");
        } else {
            for (EnergySource source : energySources) {
                System.out.println(source.getName() + " - Supply Rate: " + source.getSupplyRate() +
                    " - Status: " + (source.isActive() ? "Active" : "Inactive"));
            }
        }
    }
}
