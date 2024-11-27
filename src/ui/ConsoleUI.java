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

import java.util.Scanner;

public class ConsoleUI {
    private final DeviceManager deviceManager;
    private final EnergyManager energyManager;

    public ConsoleUI(DeviceManager deviceManager, EnergyManager energyManager) {
        this.deviceManager = deviceManager;
        this.energyManager = energyManager;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Smart Home System ---");
            System.out.println("1. Turn all devices ON");
            System.out.println("2. Turn all devices OFF");
            System.out.println("3. Show device status");
            System.out.println("4. Activate all energy sources");
            System.out.println("5. Distribute energy");
            System.out.println("6. Add a device");
            System.out.println("7. Remove a device");
            System.out.println("8. Add an energy source");
            System.out.println("9. Remove an energy source");
            System.out.println("10. Show energy sources");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> deviceManager.turnAllOn();
                case 2 -> deviceManager.turnAllOff();
                case 3 -> deviceManager.showStatus();
                case 4 -> {
                    energyManager.activateAllSources();
                    System.out.println("All energy sources are now active.");
                }
                case 5 -> {
                	energyManager.distributeEnergy();
                }
                case 6 -> {
                    System.out.print("Enter device name: ");
                    String deviceName = scanner.nextLine().trim();
                    if (deviceName.isEmpty()) {
                        System.out.println("Device name cannot be empty.");
                        break;
                    }

                    System.out.println("Choose device type:");
                    System.out.println("1. Light");
                    System.out.println("2. Heater");
                    System.out.println("3. Smart Appliance");
                    System.out.print("Enter choice: ");
                    int typeChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter consumption rate: ");
                    int consumptionRate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    SmartObject newDevice = null;
                    switch (typeChoice) {
                        case 1 -> newDevice = new Light(deviceName, consumptionRate);
                        case 2 -> newDevice = new Heater(deviceName, consumptionRate);
                        case 3 -> newDevice = new SmartAppliance(deviceName, consumptionRate);
                        default -> System.out.println("Invalid choice. Device not added.");
                    }

                    if (newDevice != null) {
                        deviceManager.addDevice(newDevice);
                        System.out.println("Device added: " + newDevice.getName() + " with consumption rate: " + consumptionRate);
                    }
                }
                case 7 -> {
                    System.out.print("Enter device name to remove: ");
                    String deviceName = scanner.nextLine().trim();
                    if (deviceName.isEmpty()) {
                        System.out.println("Device name cannot be empty.");
                    } else {
                        deviceManager.removeDevice(deviceName);
                    }
                }
                case 8 -> {
                    System.out.print("Enter energy source name: ");
                    String sourceName = scanner.nextLine().trim();
                    if (sourceName.isEmpty()) {
                        System.out.println("Energy source name cannot be empty.");
                        break;
                    }

                    System.out.println("Choose energy source type:");
                    System.out.println("1. Solar Panel");
                    System.out.println("2. Wind Turbine");
                    System.out.println("3. Battery");
                    System.out.print("Enter choice: ");
                    int typeChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter supply rate: ");
                    int supplyRate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    EnergySource newSource = null;
                    switch (typeChoice) {
                        case 1 -> newSource = new SolarPanel(sourceName, supplyRate);
                        case 2 -> newSource = new WindTurbine(sourceName, supplyRate);
                        case 3 -> {
                            System.out.print("Enter battery capacity: ");
                            int capacity = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            newSource = new Battery(sourceName, supplyRate, capacity);
                        }
                        default -> System.out.println("Invalid choice. Energy source not added.");
                    }

                    if (newSource != null) {
                        energyManager.addEnergySource(newSource);
                        System.out.println("Energy source added: " + newSource.getName() + " with supply rate: " + supplyRate);
                    }
                }
                case 9 -> {
                    System.out.print("Enter energy source name to remove: ");
                    String sourceName = scanner.nextLine().trim();
                    if (sourceName.isEmpty()) {
                        System.out.println("Energy source name cannot be empty.");
                    } else {
                        energyManager.removeEnergySource(sourceName);
                    }
                }
                case 10 -> energyManager.showEnergySources();
                case 11 -> {
                    running = false;
                    System.out.println("Exiting Smart Home System. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
