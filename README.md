# NextGen Smart Home System

DEMO VIDEO - https://drive.google.com/file/d/1GXEESzPcmqxv6IbD2CGFIzfI1R65Die2/view?usp=drive_link



PRESENTATION - https://docs.google.com/presentation/d/17FdkOfhVPDeb_f_qxdiZqn0EgTrDMXao/edit?usp=sharing&ouid=117661936818154380366&rtpof=true&sd=true

The **NextGen Smart Home System** is a console-based application that allows users to manage smart devices and energy sources dynamically. The system simulates energy distribution to devices based on energy supply and demand, prioritizing efficient resource management.

## Features
- **Dynamic Device Management**:
  - Add, remove, and control smart devices.
  - View device statuses and consumption rates.
- **Dynamic Energy Source Management**:
  - Add, remove, and activate energy sources.
  - View energy sources with their supply rates and statuses.
- **Energy Distribution**:
  - Calculate total energy supply and demand.
  - Efficiently distribute energy to devices based on availability.
  - Prioritize devices during energy shortages.
- **Interactive Console UI**:
  - User-friendly interface to manage the system.

## Project Structure
```plaintext
NextGenSmartHome/
├── src/
│   ├── energy/
│   │   ├── Battery.java         # Represents a battery energy source
│   │   ├── EnergySource.java    # Abstract class for energy sources
│   │   ├── SolarPanel.java      # Represents a solar panel energy source
│   │   ├── WindTurbine.java     # Represents a wind turbine energy source
│   ├── management/
│   │   ├── DeviceManager.java   # Manages smart devices
│   │   ├── EnergyManager.java   # Manages energy sources and distribution
│   ├── objects/
│   │   ├── Heater.java          # Represents a heater device
│   │   ├── Light.java           # Represents a light device
│   │   ├── SmartAppliance.java  # Represents a smart appliance device
│   │   ├── SmartObject.java     # Abstract class for smart devices
│   ├── ui/
│   │   ├── ConsoleUI.java       # Interactive user interface for the system
│   ├── Main.java                # Entry point of the application
├── tests/
│   ├── BatteryTest.java         # Unit tests for Battery class
│   ├── DeviceManagerTest.java   # Unit tests for DeviceManager class
│   ├── EnergyManagerTest.java   # Unit tests for EnergyManager class
│   ├── EnergySourceTest.java    # Unit tests for EnergySource class
│   ├── SmartObjectTest.java     # Unit tests for SmartObject class
│   ├── UnitTests.java           # Test suite for running all tests
├── README.md                    # Project documentation (this file)
