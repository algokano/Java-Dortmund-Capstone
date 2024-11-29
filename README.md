# NextGen Smart Home System - GROUP 09

DEMO VIDEO with GUI - https://drive.google.com/file/d/1-7u-1H1Mu3iMveaU1AFqGuJDNxwnqYd_/view?usp=sharing

PRESENTATION - https://docs.google.com/presentation/d/17FdkOfhVPDeb_f_qxdiZqn0EgTrDMXao/edit?usp=sharing&ouid=117661936818154380366&rtpof=true&sd=true

The **NextGen Smart Home System** is a console-based application that allows users to manage smart devices and energy sources dynamically. The system simulates energy distribution to devices based on energy supply and demand, prioritizing efficient resource management.

OLD DEMO VIDEO with CONSOLE UI - https://drive.google.com/file/d/1GXEESzPcmqxv6IbD2CGFIzfI1R65Die2/view?usp=drive_link

<img width="402" alt="Screenshot 2024-11-28 at 12 52 29" src="https://github.com/user-attachments/assets/63c4e490-3a90-42ed-8464-6a2f8f536c04">

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
