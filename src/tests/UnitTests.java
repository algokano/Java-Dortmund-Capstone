package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import objects.*;
import energy.*;
import management.*;

import java.util.List;

public class UnitTests {

    // SmartObject Tests
    @Test
    public void testSmartObjectTurnOnOff() {
        SmartObject light = new Light("Test Light", 20);
        light.turnOn();
        assertTrue(light.isOn(), "The light should be ON after calling turnOn.");
        
        light.turnOff();
        assertFalse(light.isOn(), "The light should be OFF after calling turnOff.");
    }

    @Test
    public void testSmartObjectConsumption() {
        SmartObject heater = new Heater("Test Heater", 50);
        assertEquals(0, heater.getConsumptionRate(), "Heater should consume 0 energy when OFF.");
        
        heater.turnOn();
        assertEquals(50, heater.getConsumptionRate(), "Heater should consume 50 energy when ON.");
    }

    // EnergySource Tests
    @Test
    public void testEnergySourceActivation() {
        EnergySource solarPanel = new SolarPanel("Test Solar Panel", 100);
        solarPanel.activate();
        assertTrue(solarPanel.isActive(), "The solar panel should be active after calling activate.");
        
        solarPanel.deactivate();
        assertFalse(solarPanel.isActive(), "The solar panel should be inactive after calling deactivate.");
    }

    @Test
    public void testEnergySourceSupplyRate() {
        EnergySource windTurbine = new WindTurbine("Test Wind Turbine", 50);
        assertEquals(0, windTurbine.getSupplyRate(), "Supply rate should be 0 when the wind turbine is inactive.");
        
        windTurbine.activate();
        assertEquals(50, windTurbine.getSupplyRate(), "Supply rate should match the set rate when active.");
    }

    // DeviceManager Tests
    @Test
    public void testDeviceManagerTurnAllOn() {
        SmartObject light = new Light("Test Light", 20);
        SmartObject heater = new Heater("Test Heater", 50);
        
        DeviceManager manager = new DeviceManager(List.of(light, heater));
        manager.turnAllOn();
        
        assertTrue(light.isOn(), "The light should be ON.");
        assertTrue(heater.isOn(), "The heater should be ON.");
    }

    @Test
    public void testDeviceManagerTurnAllOff() {
        SmartObject light = new Light("Test Light", 20);
        SmartObject heater = new Heater("Test Heater", 50);
        
        DeviceManager manager = new DeviceManager(List.of(light, heater));
        manager.turnAllOff();
        
        assertFalse(light.isOn(), "The light should be OFF.");
        assertFalse(heater.isOn(), "The heater should be OFF.");
    }

    // EnergyManager Tests
    @Test
    public void testEnergyDistribution() {
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

        assertDoesNotThrow(manager::distributeEnergy, "Energy distribution should succeed when total supply exceeds total consumption.");
    }
}
