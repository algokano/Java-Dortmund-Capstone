package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import management.EnergyManager;
import objects.*;
import energy.*;

import java.util.List;

public class EnergyManagerTest {

    @Test
    public void testDistributeEnergy() {
        SmartObject light = new Light("Test Light", 20);
        SmartObject heater = new Heater("Test Heater", 50);

        EnergySource solarPanel = new SolarPanel("Test Solar Panel", 100);
        EnergySource windTurbine = new WindTurbine("Test Wind Turbine", 50);

        EnergyManager manager = new EnergyManager(
            List.of(light, heater),
            List.of(solarPanel, windTurbine)
        );

        light.turnOn();
        heater.turnOn();

        assertDoesNotThrow(manager::distributeEnergy, "Energy distribution should succeed.");
    }
    
    @Test
    public void testEnergyDistributionSufficient() {
        SmartObject light = new Light("Light", 10);
        EnergySource solarPanel = new SolarPanel("Solar Panel", 50);
        EnergyManager manager = new EnergyManager(List.of(light), List.of(solarPanel));
        
        solarPanel.activate();
        manager.distributeEnergy();
        assertTrue(light.isOn());
    }

    @Test
    public void testEnergyDistributionInsufficient() {
        SmartObject light = new Light("Light", 60);
        EnergySource solarPanel = new SolarPanel("Solar Panel", 50);
        EnergyManager manager = new EnergyManager(List.of(light), List.of(solarPanel));
        
        solarPanel.activate();
        manager.distributeEnergy();
        assertFalse(light.isOn());
    }
}
