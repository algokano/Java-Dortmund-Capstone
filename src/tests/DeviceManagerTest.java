package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import management.DeviceManager;
import management.LogSystem;
import objects.*;

import java.util.ArrayList;
import java.util.List;

public class DeviceManagerTest {

    @Test
    public void testTurnAllOn() {
        LogSystem logger = new LogSystem(); // Initialize logger
        SmartObject light = new Light("Test Light", 10);
        SmartObject heater = new Heater("Test Heater", 50);

        DeviceManager manager = new DeviceManager(List.of(light, heater), logger); // Pass logger
        manager.turnAllOn();

        assertTrue(light.isOn(), "Light should be ON.");
        assertTrue(heater.isOn(), "Heater should be ON.");
    }

    @Test
    public void testTurnAllOff() {
        LogSystem logger = new LogSystem(); // Initialize logger
        SmartObject light = new Light("Test Light", 10);
        SmartObject heater = new Heater("Test Heater", 50);

        DeviceManager manager = new DeviceManager(List.of(light, heater), logger); // Pass logger
        manager.turnAllOff();

        assertFalse(light.isOn(), "Light should be OFF.");
        assertFalse(heater.isOn(), "Heater should be OFF.");
    }

    @Test
    public void testAddDevice() {
        LogSystem logger = new LogSystem(); // Initialize logger
        DeviceManager manager = new DeviceManager(new ArrayList<>(), logger); // Pass logger
        SmartObject light = new Light("Test Light", 10);
        manager.addDevice(light);

        assertEquals(1, manager.getDeviceCount(), "Device count should be 1 after adding a device.");
    }

    @Test
    public void testRemoveDevice() {
        LogSystem logger = new LogSystem(); // Initialize logger
        SmartObject light = new Light("Test Light", 10);
        DeviceManager manager = new DeviceManager(List.of(light), logger); // Pass logger
        manager.removeDevice("Test Light");

        assertEquals(0, manager.getDeviceCount(), "Device count should be 0 after removing the device.");
    }

    @Test
    public void testShowStatus() {
        LogSystem logger = new LogSystem(); // Initialize logger
        SmartObject light = new Light("Test Light", 10);
        SmartObject heater = new Heater("Test Heater", 50);

        DeviceManager manager = new DeviceManager(List.of(light, heater), logger); // Pass logger
        light.turnOn();
        heater.turnOff();

        // Simulate show status (manual validation)
        manager.showStatus();

        assertTrue(light.isOn(), "Light should be ON.");
        assertFalse(heater.isOn(), "Heater should be OFF.");
    }
}
