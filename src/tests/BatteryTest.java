package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import energy.Battery;

public class BatteryTest {

    @Test
    public void testChargeBattery() {
        Battery battery = new Battery("Test Battery", 50, 200);
        battery.chargeBattery(50);
        assertEquals(50, battery.getCurrentCharge(), "Battery should be charged to 50 units.");
    }

    @Test
    public void testOverchargeBattery() {
        Battery battery = new Battery("Test Battery", 50, 200);
        battery.chargeBattery(250);
        assertEquals(200, battery.getCurrentCharge(), "Battery should not exceed its capacity.");
    }
}
