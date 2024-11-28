package ui;

import management.DeviceManager;
import management.EnergyManager;
import objects.Heater;
import objects.Light;
import objects.SmartAppliance;
import objects.SmartObject;
import energy.Battery;
import energy.EnergySource;
import energy.SolarPanel;
import energy.WindTurbine;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SmartHomeSwingGUI {
    private final DeviceManager deviceManager;
    private final EnergyManager energyManager;
    private JTextArea resultArea; // Keeps logs of all actions

    public SmartHomeSwingGUI(DeviceManager deviceManager, EnergyManager energyManager) {
        this.deviceManager = deviceManager;
        this.energyManager = energyManager;
    }

    public void start() {
        // Create the main frame
        JFrame frame = new JFrame("Smart Home System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 2, 10, 10));

        // Text area for result display
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Buttons for actions
        JButton btnTurnAllOn = new JButton("Turn All Devices ON");
        JButton btnTurnAllOff = new JButton("Turn All Devices OFF");
        JButton btnShowDeviceStatus = new JButton("Show Device Status");
        JButton btnActivateSources = new JButton("Activate All Energy Sources");
        JButton btnDistributeEnergy = new JButton("Distribute Energy");
        JButton btnAddDevice = new JButton("Add Device");
        JButton btnRemoveDevice = new JButton("Remove Device");
        JButton btnAddEnergySource = new JButton("Add Energy Source");
        JButton btnRemoveEnergySource = new JButton("Remove Energy Source");
        JButton btnShowEnergySources = new JButton("Show Energy Sources");
        JButton btnShowAllInfo = new JButton("Show All Information"); // New button for combined info

        // Add buttons to the panel
        buttonPanel.add(btnTurnAllOn);
        buttonPanel.add(btnTurnAllOff);
        buttonPanel.add(btnShowDeviceStatus);
        buttonPanel.add(btnActivateSources);
        buttonPanel.add(btnDistributeEnergy);
        buttonPanel.add(btnAddDevice);
        buttonPanel.add(btnRemoveDevice);
        buttonPanel.add(btnAddEnergySource);
        buttonPanel.add(btnRemoveEnergySource);
        buttonPanel.add(btnShowEnergySources);
        buttonPanel.add(btnShowAllInfo);

        // Add action listeners to buttons
        btnTurnAllOn.addActionListener(e -> {
            deviceManager.turnAllOn();
            appendLog("All devices turned ON.");
        });

        btnTurnAllOff.addActionListener(e -> {
            deviceManager.turnAllOff();
            appendLog("All devices turned OFF.");
        });

        btnShowDeviceStatus.addActionListener(e -> showDeviceStatus());

        btnActivateSources.addActionListener(e -> {
            energyManager.activateAllSources();
            appendLog("All energy sources activated.");
        });

        btnDistributeEnergy.addActionListener(e -> {
            energyManager.distributeEnergy();
            appendLog("Energy distributed successfully.");
        });

        btnAddDevice.addActionListener(e -> addDevice());
        btnRemoveDevice.addActionListener(e -> removeDevice());
        btnAddEnergySource.addActionListener(e -> addEnergySource());
        btnRemoveEnergySource.addActionListener(e -> removeEnergySource());
        btnShowEnergySources.addActionListener(e -> showEnergySources());
        btnShowAllInfo.addActionListener(e -> showAllInformation());

        // Add components to frame
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);
    }

    private void appendLog(String message) {
        resultArea.append(message + "\n");
    }

    private void showDeviceStatus() {
        StringBuilder status = new StringBuilder("--- Device Status ---\n");
        for (SmartObject device : deviceManager.getDevices()) {
            status.append(device.getName())
                  .append(" - Consumption: ")
                  .append(device.getConsumptionRate())
                  .append(" - Status: ")
                  .append(device.isOn() ? "ON" : "OFF")
                  .append("\n");
        }
        appendLog(status.toString());
    }

    private void showEnergySources() {
        StringBuilder status = new StringBuilder("--- Energy Sources ---\n");
        for (EnergySource source : energyManager.getEnergySources()) {
            status.append(source.getName())
                  .append(" - Supply Rate: ")
                  .append(source.getSupplyRate())
                  .append(" - Status: ")
                  .append(source.isActive() ? "Active" : "Inactive")
                  .append("\n");
        }
        appendLog(status.toString());
    }

    private void showAllInformation() {
        StringBuilder info = new StringBuilder();
        info.append("--- Device Information ---\n");
        for (SmartObject device : deviceManager.getDevices()) {
            info.append(device.getName())
                .append(" - Consumption: ")
                .append(device.getConsumptionRate())
                .append(" - Status: ")
                .append(device.isOn() ? "ON" : "OFF")
                .append("\n");
        }

        info.append("\n--- Energy Source Information ---\n");
        for (EnergySource source : energyManager.getEnergySources()) {
            info.append(source.getName())
                .append(" - Supply Rate: ")
                .append(source.getSupplyRate())
                .append(" - Status: ")
                .append(source.isActive() ? "Active" : "Inactive")
                .append("\n");
        }
        appendLog(info.toString());
    }

    private void addDevice() {
        String deviceName = JOptionPane.showInputDialog("Enter device name:");
        if (deviceName == null || deviceName.trim().isEmpty()) {
            appendLog("Device name cannot be empty.");
            return;
        }

        String[] deviceTypes = {"Light", "Heater", "Smart Appliance"};
        String deviceType = (String) JOptionPane.showInputDialog(null, "Select device type:",
                "Add Device", JOptionPane.QUESTION_MESSAGE, null, deviceTypes, deviceTypes[0]);

        if (deviceType == null) return;

        int consumptionRate;
        try {
            consumptionRate = Integer.parseInt(JOptionPane.showInputDialog("Enter consumption rate:"));
        } catch (NumberFormatException e) {
            appendLog("Invalid consumption rate.");
            return;
        }

        SmartObject newDevice;
        switch (deviceType) {
            case "Light" -> newDevice = new Light(deviceName, consumptionRate);
            case "Heater" -> newDevice = new Heater(deviceName, consumptionRate);
            case "Smart Appliance" -> newDevice = new SmartAppliance(deviceName, consumptionRate);
            default -> {
                appendLog("Invalid device type.");
                return;
            }
        }

        deviceManager.addDevice(newDevice);
        appendLog("Device added: " + newDevice.getName() + " with consumption rate: " + consumptionRate);
    }

    private void removeDevice() {
        String deviceName = JOptionPane.showInputDialog("Enter device name to remove:");
        if (deviceName == null || deviceName.trim().isEmpty()) {
            appendLog("Device name cannot be empty.");
            return;
        }
        deviceManager.removeDevice(deviceName);
        appendLog("Device removed: " + deviceName);
    }

    private void addEnergySource() {
        String sourceName = JOptionPane.showInputDialog("Enter energy source name:");
        if (sourceName == null || sourceName.trim().isEmpty()) {
            appendLog("Energy source name cannot be empty.");
            return;
        }

        String[] sourceTypes = {"Solar Panel", "Wind Turbine", "Battery"};
        String sourceType = (String) JOptionPane.showInputDialog(null, "Select energy source type:",
                "Add Energy Source", JOptionPane.QUESTION_MESSAGE, null, sourceTypes, sourceTypes[0]);

        if (sourceType == null) return;

        int supplyRate;
        try {
            supplyRate = Integer.parseInt(JOptionPane.showInputDialog("Enter supply rate:"));
        } catch (NumberFormatException e) {
            appendLog("Invalid supply rate.");
            return;
        }

        EnergySource newSource = switch (sourceType) {
            case "Solar Panel" -> new SolarPanel(sourceName, supplyRate);
            case "Wind Turbine" -> new WindTurbine(sourceName, supplyRate);
            case "Battery" -> {
                int capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter battery capacity:"));
                yield new Battery(sourceName, supplyRate, capacity);
            }
            default -> null;
        };

        if (newSource != null) {
            energyManager.addEnergySource(newSource);
            appendLog("Energy source added: " + newSource.getName() + " with supply rate: " + supplyRate);
        }
    }

    private void removeEnergySource() {
        String sourceName = JOptionPane.showInputDialog("Enter energy source name to remove:");
        if (sourceName == null || sourceName.trim().isEmpty()) {
            appendLog("Energy source name cannot be empty.");
            return;
        }
        energyManager.removeEnergySource(sourceName);
        appendLog("Energy source removed: " + sourceName);
    }
}
