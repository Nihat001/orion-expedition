import java.io.IOException;

/**
 * Entry point. Usage:
 *   java Simulation [destinations.txt] [mode]
 * where mode is STANDARD, EFFICIENT, or EXPLORATION.
 * 
 * @author Nihat Masimli
 */
public class Simulation {
    public static void main(String[] args) {
        SpaceShuttle shuttle = new SpaceShuttle(1000.0);

        // Load destinations from file or add samples
        if (args.length > 0) {
            try {
                // Correct method name:
                shuttle.loadDestinationFromFile(args[0]);
            } catch (IOException e) {
                System.err.println(
                  "Failed to load " + args[0] + ": " + e.getMessage()
                );
                return;
            }
        } else {
            shuttle.addDestination("Mercury", 100, 7.5, 10.0);
            shuttle.addDestination("Jupiter", 1000, 9.2, 12.0);
            shuttle.addDestination("Saturn", 500, 8.1, 11.0);
            shuttle.addDestination("Neptune", 200, 9.8, 9.0);
            shuttle.addDestination("Mars", 4000, 8.7, 13.0);
        }

        // Optionally set nav mode
        if (args.length > 1) {
            try {
                NavigationMode mode = NavigationMode
                    .valueOf(args[1].toUpperCase());
                shuttle.setNavigationMode(mode);
            } catch (IllegalArgumentException e) {
                System.err.println(
                  "Unknown mode '" + args[1] +
                  "'. Using STANDARD."
                );
            }
        }

        shuttle.launch();
    }
}
