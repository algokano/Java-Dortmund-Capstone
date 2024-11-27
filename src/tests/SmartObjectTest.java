package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import objects.*;

public class SmartObjectTest {

    @Test
    public void testTurnOnAndOff() {
        SmartObject light = new Light("Test Light", 20);

        // Test initial state
        assertFalse(light.isOn(), "The light should initially be OFF.");

        // Turn on the light
        light.turnOn();
        assertTrue(light.isOn(), "The light should be ON after calling turnOn.");

        // Turn off the light
        light.turnOff();
        assertFalse(light.isOn(), "The light should be OFF after calling turnOff.");
    }

    @Test
    public void testConsumptionRate() {
        SmartObject heater = new Heater("Test Heater", 50);

        // Test consumption when off
        assertEquals(0, heater.getConsumptionRate(), "Consumption should be 0 when the heater is OFF.");

        // Test consumption when on
        heater.turnOn();
        assertEquals(50, heater.getConsumptionRate(), "Consumption should match the set rate when the heater is ON.");
    }

    @Test
    public void testSimulate() {
        SmartObject appliance = new SmartAppliance("Test Appliance", 30);

        // Turn on and simulate usage
        appliance.turnOn();
        assertDoesNotThrow(appliance::simulate, "Simulation should not throw any exceptions.");
    }
}
