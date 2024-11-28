package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import management.EnergyManager;
import management.LogSystem;
import objects.*;
import energy.*;

import java.util.List;

public class EnergyManagerTest {

    @Test
    public void testDistributeEnergy() {
        LogSystem logger = new LogSystem();
        SmartObject light = new Light("Test Light", 20);
        SmartObject heater = new Heater("Test Heater", 50);

        EnergySource solarPanel = new SolarPanel("Test Solar Panel", 100);
        EnergySource windTurbine = new WindTurbine("Test Wind Turbine", 50);

        EnergyManager manager = new EnergyManager(
            List.of(light, heater),
            List.of(solarPanel, windTurbine),
            logger
        );

        light.turnOn();
        heater.turnOn();

        assertDoesNotThrow(manager::distributeEnergy, "Energy distribution should succeed.");
    }

    @Test
    public void testEnergyDistributionSufficient() {
        LogSystem logger = new LogSystem();
        SmartObject light = new Light("Light", 10);
        EnergySource solarPanel = new SolarPanel("Solar Panel", 50);

        EnergyManager manager = new EnergyManager(List.of(light), List.of(solarPanel), logger);

        solarPanel.activate();
        manager.distributeEnergy();
        assertTrue(light.isPowered(), "Light should be powered when energy is sufficient.");
    }

    @Test
    public void testEnergyDistributionInsufficient() {
        LogSystem logger = new LogSystem();
        SmartObject light = new Light("Light", 60);
        EnergySource solarPanel = new SolarPanel("Solar Panel", 50);

        EnergyManager manager = new EnergyManager(List.of(light), List.of(solarPanel), logger);

        solarPanel.activate();
        manager.distributeEnergy();
        assertFalse(light.isPowered(), "Light should not be powered when energy is insufficient.");
    }

    @Test
    public void testEnergySourceActivation() {
        LogSystem logger = new LogSystem();
        EnergySource solarPanel = new SolarPanel("Solar Panel", 50);
        EnergyManager manager = new EnergyManager(List.of(), List.of(solarPanel), logger);

        solarPanel.activate();
        assertTrue(solarPanel.isActive(), "Solar Panel should be active after activation.");
    }

    @Test
    public void testLoggingDuringDistribution() {
        LogSystem logger = new LogSystem();
        SmartObject light = new Light("Light", 10);
        EnergySource solarPanel = new SolarPanel("Solar Panel", 50);

        EnergyManager manager = new EnergyManager(List.of(light), List.of(solarPanel), logger);

        solarPanel.activate();
        light.turnOn();
        manager.distributeEnergy();

        // Ensure logs are created (basic validation)
        assertDoesNotThrow(logger::readLogs, "Logs should be readable after energy distribution.");
    }
}
