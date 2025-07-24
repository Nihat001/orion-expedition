// NavigationSystem.java
/**
 * @author Nihat Masimli
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Maintains a list of destinations and applies a navigation strategy.
 * Default mode is STANDARD.
 * 
 * @author Nihat Masimli DNGKKG
 */
public class NavigationSystem {
    private final List<Destination> destinations;
    private NavigationStrategy strategy;

    /**
     * Starts with an empty list and standard strategy.
     */
    public NavigationSystem() {
        this.destinations = new ArrayList<>();
        this.strategy = new StandardNavigationStrategy();
    }

    /**
     * Sets the navigation mode, switching strategy.
     *
     * @param mode chosen NavigationMode
     */
    public void setMode(NavigationMode mode) {
        switch (mode) {
            case STANDARD:
                this.strategy = new StandardNavigationStrategy();
                break;
            case EFFICIENT:
                this.strategy = new EfficientNavigationStrategy();
                break;
            case EXPLORATION:
                this.strategy = new ExplorationNavigationStrategy();
                break;
        }
    }

    /** Add a new destination. */
    public void addDestination(Destination dest) {
        destinations.add(dest);
    }

    /**
     * Remove any that have been reached.
     *
     * @param currProgress shuttle's current progress
     */
    public void removeReachedDestinations(double currProgress) {
        for (Destination d : destinations) {
            d.updateProgress(currProgress);
        }
        destinations.removeIf(Destination::isReached);
    }

    /**
     * Chooses the next destination by delegating to the current strategy.
     *
     * @param currProgress shuttle's current travelled distance
     * @return next Destination, or null if none
     */
    public Destination getNextDestination(double currProgress) {
        return strategy.selectNext(destinations, currProgress);
    }

    /** True if no destinations remain. */
    public boolean isEmpty() {
        return destinations.isEmpty();
    }

    @Override
    public String toString() {
        return "NavigationSystem{" +
               "mode=" + strategy.getClass().getSimpleName() +
               ", destinations=" + destinations +
               '}';
    }
}
