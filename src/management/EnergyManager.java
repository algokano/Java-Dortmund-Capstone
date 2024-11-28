package management;
import energy.EnergySource;
import objects.SmartObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EnergyManager {
    private List<EnergySource> energySources;
    private List<SmartObject> smartObjects;
    private final LogSystem logger; // Logger instance

    public EnergyManager(List<SmartObject> smartObjects, List<EnergySource> energySources, LogSystem logger) {
        this.smartObjects = new ArrayList<>(smartObjects);
        this.energySources = new ArrayList<>(energySources);
        this.logger = logger; // Initialize logger
    }

    public void addEnergySource(EnergySource source) {
        energySources.add(source);
        System.out.println(source.getName() + " added successfully.");
        logger.logEvent("Added energy source: " + source.getName());
    }

    public void removeEnergySource(String sourceName) {
        boolean removed = energySources.removeIf(source -> source.getName().equals(sourceName));
        if (removed) {
            System.out.println(sourceName + " removed successfully.");
            logger.logEvent("Removed energy source: " + sourceName);
        } else {
            System.out.println("Energy source '" + sourceName + "' not found.");
            logger.logEvent("Failed to remove energy source: " + sourceName + " (not found)");
        }
    }

    public void activateAllSources() {
        for (EnergySource source : energySources) {
            source.activate();
            logger.logEvent("Activated energy source: " + source.getName());
        }
    }

    public void distributeEnergy() {
        int totalSupply = energySources.stream()
                                       .filter(EnergySource::isActive)
                                       .mapToInt(EnergySource::getSupplyRate)
                                       .sum();

        int totalConsumption = smartObjects.stream()
                                           .filter(SmartObject::isOn)
                                           .mapToInt(SmartObject::getConsumptionRate)
                                           .sum();

        System.out.println("Total energy supply: " + totalSupply);
        System.out.println("Total energy required: " + totalConsumption);

        logger.logEvent("Energy distribution initiated. Total supply: " + totalSupply +
            ", Total consumption: " + totalConsumption);

        if (totalSupply >= totalConsumption) {
            System.out.println("Sufficient energy available. All devices will operate normally.");
            logger.logEvent("Sufficient energy available. All devices powered.");

            smartObjects.stream()
                        .filter(SmartObject::isOn)
                        .forEach(device -> {
                            device.setPowered(true);
                            System.out.println(device.getName() + " is fully powered (consumption: " + device.getConsumptionRate() + ").");
                            logger.logEvent(device.getName() + " is fully powered.");
                        });
        } else {
            System.out.println("Insufficient energy! Allocating energy with priority.");
            logger.logEvent("Insufficient energy! Allocating energy with priority.");
            allocateEnergyWithPriority(totalSupply);
        }
    }

    private void allocateEnergyWithPriority(int totalSupply) {
        AtomicInteger remainingSupply = new AtomicInteger(totalSupply);

        smartObjects.stream()
                    .filter(SmartObject::isOn)
                    .sorted((d1, d2) -> Integer.compare(d1.getConsumptionRate(), d2.getConsumptionRate()))
                    .forEach(device -> {
                        if (remainingSupply.get() >= device.getConsumptionRate()) {
                            remainingSupply.addAndGet(-device.getConsumptionRate());
                            device.setPowered(true);
                            System.out.println(device.getName() + " is powered (consumption: " + device.getConsumptionRate() + ").");
                            logger.logEvent(device.getName() + " is powered.");
                        } else {
                            device.setPowered(false);
                            System.out.println(device.getName() + " is not powered due to insufficient energy.");
                            logger.logEvent(device.getName() + " is not powered (insufficient energy).");
                        }
                    });

        System.out.println("Remaining supply after allocation: " + remainingSupply.get());
        logger.logEvent("Remaining supply after allocation: " + remainingSupply.get());
    }

    public void showEnergySources() {
        System.out.println("--- Energy Sources ---");
        if (energySources.isEmpty()) {
            System.out.println("No energy sources available.");
            logger.logEvent("No energy sources available.");
        } else {
            for (EnergySource source : energySources) {
                System.out.println(source.getName() + " - Supply Rate: " + source.getSupplyRate() +
                    " - Status: " + (source.isActive() ? "Active" : "Inactive"));
                logger.logEvent("Energy source: " + source.getName() +
                    ", Supply Rate: " + source.getSupplyRate() +
                    ", Status: " + (source.isActive() ? "Active" : "Inactive"));
            }
        }
    }
    public String getEnergySourceStatus() {
        StringBuilder status = new StringBuilder("--- Energy Source Status ---\n");
        if (energySources.isEmpty()) {
            status.append("No energy sources are currently available.\n");
        } else {
            for (EnergySource source : energySources) {
                status.append(source.getName())
                      .append(" - Supply Rate: ")
                      .append(source.getSupplyRate())
                      .append(" - Status: ")
                      .append(source.isActive() ? "Active" : "Inactive")
                      .append("\n");
            }
        }
        return status.toString();
    }
    public List<EnergySource> getEnergySources() {
        return new ArrayList<>(energySources);
    }
}
