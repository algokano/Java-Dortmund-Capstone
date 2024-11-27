package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import management.DeviceManager;
import objects.*;

import java.util.ArrayList;
import java.util.List;

public class DeviceManagerTest {

    @Test
    public void testTurnAllOn() {
        SmartObject light = new Light("Test Light", 10);
        SmartObject heater = new Heater("Test Heater", 50);

        DeviceManager manager = new DeviceManager(List.of(light, heater));
        manager.turnAllOn();

        assertTrue(light.isOn(), "Light should be ON.");
        assertTrue(heater.isOn(), "Heater should be ON.");
    }

    @Test
    public void testTurnAllOff() {
        SmartObject light = new Light("Test Light", 10);
        SmartObject heater = new Heater("Test Heater", 50);

        DeviceManager manager = new DeviceManager(List.of(light, heater));
        manager.turnAllOff();

        assertFalse(light.isOn(), "Light should be OFF.");
        assertFalse(heater.isOn(), "Heater should be OFF.");
    }
    @Test
    public void testAddDevice() {
        DeviceManager manager = new DeviceManager(new ArrayList<>());
        SmartObject light = new Light("Test Light", 10);
        manager.addDevice(light);
        assertEquals(1, manager.getDeviceCount());
    }

    @Test
    public void testRemoveDevice() {
        SmartObject light = new Light("Test Light", 10);
        DeviceManager manager = new DeviceManager(List.of(light));
        manager.removeDevice("Test Light");
        assertEquals(0, manager.getDeviceCount());
    }
}
