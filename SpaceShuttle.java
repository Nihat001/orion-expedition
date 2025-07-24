
/**
 * @author Nihat Masimli
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Ties together navigation, engine, fuel tank, and orbiter.
 * Now supports loading from both 2-column (name, distance) files
 * and 4-column (name, distance, scientificValue, consumptionRate) files.
 * 
 * @author Nihat Masimli DNGKKG
 */
public class SpaceShuttle {
    private final NavigationSystem navigation;
    private final FuelTank fuelTank;
    private final Engine engine;
    private Orbiter orbiter;
    private double progress;

    /**
     * @param maxFuel maximum fuel capacity
     */
    public SpaceShuttle(double maxFuel) {
        this.navigation = new NavigationSystem();
        this.fuelTank = new FuelTank(maxFuel);
        this.engine = new Engine();
        this.progress = 0.0;
    }

    /** 
     * Adds a destination with its scientific value and consumption rate. 
     */
    public void addDestination(
        String name, double distance, double sciValue, double rate
    ) {
        navigation.addDestination(
            new Destination(name, distance, sciValue, rate)
        );
    }

    /**
     * Reads destinations from a file.
     * Supports lines of the form:
     *   Name Distance
     * or
     *   Name Distance SciValue ConsumptionRate
     * 
     * @param filename path to the file
     * @throws IOException if file read fails
     */
    public void loadDestinationFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 2) {
                    // old format: assign defaults
                    String name = parts[0];
                    double distance = Double.parseDouble(parts[1]);
                    double defaultSciValue = 0.0;
                    double defaultRate     = 10.0;
                    addDestination(name, distance, defaultSciValue, defaultRate);
                }
                else if (parts.length == 4) {
                    // new format
                    String name = parts[0];
                    double distance      = Double.parseDouble(parts[1]);
                    double sciValue      = Double.parseDouble(parts[2]);
                    double rate          = Double.parseDouble(parts[3]);
                    addDestination(name, distance, sciValue, rate);
                }
                // ignore any lines that don’t match
            }
        }
    }

    /** Sets the navigation mode (STANDARD, EFFICIENT, or EXPLORATION). */
    public void setNavigationMode(NavigationMode mode) {
        navigation.setMode(mode);
    }

    public void incrementProgress(double amount) {
        this.progress += amount;
    }
    public double getProgress() { return progress; }
    public NavigationSystem getNavigationSystem() { return navigation; }
    public FuelTank getFuelTank() { return fuelTank; }
    public Engine getEngine() { return engine; }

    /** Creates the orbiter and kicks off the expedition. */
    public void launch() {
        System.out.println("Launching Orion Expedition...");
        this.orbiter = new Orbiter(this);
        orbiter.startCourse();
    }
}
