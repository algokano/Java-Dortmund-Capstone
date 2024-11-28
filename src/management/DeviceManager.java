package management;

import objects.SmartObject;

import java.util.ArrayList;
import java.util.List;

public class DeviceManager {
    private List<SmartObject> smartObjects;
    private final LogSystem logger; // Added logger

    // Updated constructor to accept LogSystem
    public DeviceManager(List<SmartObject> smartObjects, LogSystem logger) {
        this.smartObjects = new ArrayList<>(smartObjects);
        this.logger = logger; // Initialize logger
    }

    public void addDevice(SmartObject device) {
        smartObjects.add(device);
        System.out.println(device.getName() + " added successfully.");
        logger.logEvent("Device added: " + device.getName()); // Log event
    }

    public void removeDevice(String deviceName) {
        boolean removed = smartObjects.removeIf(device -> device.getName().equals(deviceName));
        if (removed) {
            System.out.println(deviceName + " removed successfully.");
            logger.logEvent("Device removed: " + deviceName); // Log event
        } else {
            System.out.println("Device '" + deviceName + "' not found.");
            logger.logEvent("Failed to remove device: " + deviceName + " (not found)"); // Log event
        }
    }

    public void turnAllOn() {
        for (SmartObject device : smartObjects) {
            device.turnOn();
            System.out.println(device.getName() + " is now ON.");
            logger.logEvent(device.getName() + " turned ON."); // Log event
        }
    }

    public void turnAllOff() {
        for (SmartObject device : smartObjects) {
            device.turnOff();
            System.out.println(device.getName() + " is now OFF.");
            logger.logEvent(device.getName() + " turned OFF."); // Log event
        }
    }

    public void showStatus() {
        for (SmartObject device : smartObjects) {
            String status = device.getName() + " is currently " + (device.isOn() ? "ON" : "OFF") + ".";
            System.out.println(status);
            logger.logEvent(status); // Log event
        }
    }
    
    public String getDeviceStatus() {
        StringBuilder status = new StringBuilder("--- Device Status ---\n");
        if (smartObjects.isEmpty()) {
            status.append("No devices are currently connected.\n");
        } else {
            for (SmartObject device : smartObjects) {
                status.append(device.getName())
                      .append(" is currently ")
                      .append(device.isOn() ? "ON" : "OFF")
                      .append(".\n");
            }
        }
        return status.toString();
    }

    public int getDeviceCount() {
        return smartObjects.size();
    }
    
    public List<SmartObject> getDevices() {
        return new ArrayList<>(smartObjects);
    }
}
