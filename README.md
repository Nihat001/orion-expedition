# Orion Expedition

## Java Object-Oriented Space Shuttle Simulation

### Overview

Orion Expedition is a comprehensive Java object-oriented simulation project developed as a university coursework for the Object-Oriented Programming program at Eötvös Loránd University BSc. The project models a realistic space shuttle mission where a shuttle travels between celestial destinations while managing critical systems including navigation, fuel consumption, engine state, orbiting behavior, and mission progress tracking.

This project showcases professional software engineering practices through thoughtful domain modeling, separation of concerns, and extensible architecture patterns.

### Key Features

- **Space Shuttle Mission Simulation** - Realistic simulation of interplanetary travel
- **Destination Management** - Add, track, and manage celestial destinations
- **Fuel Tank System** - Capacity management, consumption tracking, and low-fuel detection
- **Engine Control** - Start/stop behavior with state management
- **Orbiter Coordination** - Manages travel flow and destination interactions
- **Navigation System** - Intelligent destination selection and route planning
- **Mission Progress Tracking** - Real-time mission status monitoring
- **Multiple Navigation Strategies** - Standard, Efficient, and Exploration modes
- **State-Based Shuttle Behavior** - Docked, Cruising, Orbiting, and Emergency states
- **Emergency Fuel Management** - Handle critical low-fuel scenarios

### Object-Oriented Design Focus

This project demonstrates core OOP principles through:

- **Class Responsibility Separation** - Each class has a single, well-defined responsibility
- **Real-World Domain Modeling** - Entities (Shuttle, Destination, Engine, FuelTank) map to actual concepts
- **Behavior Encapsulation** - Business logic is contained within domain classes
- **Component Interaction** - Simulation orchestrates interactions between specialized components
- **Strategy Pattern** - Multiple navigation strategies provide flexible, extensible design
- **State Pattern** - Shuttle states (Docked, Cruising, Orbiting, Emergency) encapsulate state-specific behavior

### Main Components

| Component | Responsibility |
|-----------|-----------------|
| **SpaceShuttle** | Central coordinator connecting navigation, engine, orbiter, and fuel tank systems |
| **Destination** | Represents planets/celestial bodies with distance and scientific attributes |
| **NavigationSystem** | Manages destinations, route decisions, and navigation strategy selection |
| **FuelTank** | Handles fuel capacity, consumption tracking, refueling, and low-fuel alerts |
| **Engine** | Controls movement execution and fuel usage during travel |
| **Orbiter** | Coordinates travel flow, destination updates, and mission progression |
| **NavigationMode** | Defines available navigation strategies (Standard, Efficient, Exploration) |
| **ShuttleState** | Manages shuttle operational states (Docked, Cruising, Orbiting, Emergency) |

### Extended Functionality

The simulation includes advanced features for realistic mission modeling:

**Scientific Exploration:**
- Scientific value metric for each destination
- Observation completion percentage tracking
- Destination-specific fuel consumption rates

**Navigation Modes:**
- **Standard Mode** - Balanced approach to navigation and fuel usage
- **Efficient Mode** - Optimizes fuel consumption for longer missions
- **Exploration Mode** - Maximizes scientific value discovery

**Shuttle States:**
- **Docked** - Shuttle at rest at a destination
- **Cruising** - Traveling between destinations
- **Orbiting** - In orbit around a destination
- **Emergency** - Critical low-fuel state management

### How to Run

**Compilation:**
```bash
javac *.java
```

**Basic Simulation (with default destinations):**
```bash
java Simulation
```

**Load Destinations from File:**
```bash
java Simulation Documentation/destinations.txt
```

**Specify Navigation Mode:**
```bash
java Simulation Documentation/destinations.txt STANDARD
```

**Available Navigation Modes:** `STANDARD`, `EFFICIENT`, `EXPLORATION`

### Example Input Format

Destinations can be loaded from a text file with the following formats:

**Simple Format (Name and Distance):**
```txt
Mercury 100
Jupiter 1000
Saturn 500
Neptune 200
Mars 4000
```

**Extended Format (with Scientific Value and Consumption Rate):**
```txt
Mercury 100 7.5 10.0
Jupiter 1000 9.2 12.0
Saturn 500 8.1 11.0
Neptune 200 9.8 9.0
Mars 4000 8.7 13.0
```

### Technologies & Languages

- **Language:** Java
- **Paradigm:** Object-Oriented Programming
- **Simulation Logic** - Event-driven mission progression
- **State-Based Modeling** - Shuttle state transitions and management
- **Strategy-Style Navigation** - Multiple navigation mode implementations
- **Build:** Native Java compilation (javac)
- **Testing:** JUnit integration testing

### What This Project Shows

This coursework project illustrates:

- Designing structured Java applications with clear class hierarchies
- Applying OOP principles to domain modeling
- Modeling domain entities and their interactions
- Implementing simulation behavior with realistic logic
- Handling state transitions in complex systems
- Translating requirements into classes and methods
- File I/O and exception handling
- Building extensible systems through design patterns  

### Project Context

ThisAcademic Project Note

This is an academic project created for learning and demonstration purposes as part of the Object-Oriented Programming course at Eötvös Loránd University BSc. It is not a commercial or client project.

---

**Author:** Nihat Masimli  
**Institution:** Eötvös Loránd University Budapest