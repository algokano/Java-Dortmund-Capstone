package management;

import objects.SmartObject;

import java.util.ArrayList;
import java.util.List;

public class DeviceManager {
    private List<SmartObject> smartObjects;

    public DeviceManager(List<SmartObject> smartObjects) {
        this.smartObjects = new ArrayList<>(smartObjects);
    }

    public void addDevice(SmartObject device) {
        smartObjects.add(device);
        System.out.println(device.getName() + " added successfully.");
    }

    public void removeDevice(String deviceName) {
        boolean removed = smartObjects.removeIf(device -> device.getName().equals(deviceName));
        if (removed) {
            System.out.println(deviceName + " removed successfully.");
        } else {
            System.out.println("Device '" + deviceName + "' not found.");
        }
    }

    public void turnAllOn() {
        for (SmartObject device : smartObjects) {
            device.turnOn();
            System.out.println(device.getName() + " is now ON.");
        }
    }

    public void turnAllOff() {
        for (SmartObject device : smartObjects) {
            device.turnOff();
            System.out.println(device.getName() + " is now OFF.");
        }
    }

    public void showStatus() {
        for (SmartObject device : smartObjects) {
            System.out.println(device.getName() + " is currently " + (device.isOn() ? "ON" : "OFF") + ".");
        }
    }
    public int getDeviceCount() {
        return smartObjects.size();
    }
}