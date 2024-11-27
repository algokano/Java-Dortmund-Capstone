package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import energy.*;

public class EnergySourceTest {

    @Test
    public void testActivateAndDeactivate() {
        EnergySource solarPanel = new SolarPanel("Test Solar Panel", 100);

        // Test initial state
        assertFalse(solarPanel.isActive(), "The solar panel should initially be inactive.");

        // Activate the solar panel
        solarPanel.activate();
        assertTrue(solarPanel.isActive(), "The solar panel should be active after calling activate.");

        // Deactivate the solar panel
        solarPanel.deactivate();
        assertFalse(solarPanel.isActive(), "The solar panel should be inactive after calling deactivate.");
    }

    @Test
    public void testSupplyRate() {
        EnergySource windTurbine = new WindTurbine("Test Wind Turbine", 50);

        // Test supply rate when inactive
        assertEquals(0, windTurbine.getSupplyRate(), "Supply rate should be 0 when the wind turbine is inactive.");

        // Test supply rate when active
        windTurbine.activate();
        assertEquals(50, windTurbine.getSupplyRate(), "Supply rate should match the set rate when the wind turbine is active.");
    }

    @Test
    public void testGenerateEnergy() {
        EnergySource battery = new Battery("Test Battery", 50, 200);

        // Activate and test energy generation
        battery.activate();
        assertDoesNotThrow(battery::generateEnergy, "Energy generation should not throw any exceptions.");
    }
}
