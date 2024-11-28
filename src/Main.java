import energy.*;
import management.*;
import objects.*;
import ui.SmartHomeSwingGUI;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create smart objects
        SmartObject light = new Light("Living Room Light", 10);
        SmartObject heater = new Heater("Bedroom Heater", 50);
        SmartObject appliance = new SmartAppliance("Kitchen Appliance", 30);

        // Create energy sources
        EnergySource solarPanel = new SolarPanel("Solar Panel", 100);
        EnergySource windTurbine = new WindTurbine("Wind Turbine", 50);
        Battery battery = new Battery("Main Battery", 50, 200);

        // Initialize lists for dynamic management
        List<SmartObject> smartObjects = new ArrayList<>();
        smartObjects.add(light);
        smartObjects.add(heater);
        smartObjects.add(appliance);

        List<EnergySource> energySources = new ArrayList<>();
        energySources.add(solarPanel);
        energySources.add(windTurbine);
        energySources.add(battery);

        // Logger
        LogSystem logger = new LogSystem();

        // Managers with logger integration
        DeviceManager deviceManager = new DeviceManager(smartObjects, logger); // Pass logger
        EnergyManager energyManager = new EnergyManager(smartObjects, energySources, logger); // Pass logger

        // Log initial state
        logger.logEvent("Smart Home System initialized.");
        logger.logEvent("Smart Objects: " + smartObjects.size());
        logger.logEvent("Energy Sources: " + energySources.size());

        // Start Swing GUI
        SmartHomeSwingGUI swingUI = new SmartHomeSwingGUI(deviceManager, energyManager);
        swingUI.start();

        // Log simulation completion
        logger.logEvent("Simulation completed.");
        logger.archiveLogFile(); // Archive logs at the end
        logger.deleteOldLogs(); // Clean up old logs
    }
}
