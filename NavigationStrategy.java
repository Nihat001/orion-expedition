/**
 * @author Nihat Masimli
 */
import java.util.List;


/**
 * Strategy for selecting the next destination.
 */
public interface NavigationStrategy {
    /**
     * Chooses the next destination from the list.
     *
     * @param destinations    remaining destinations
     * @param currentProgress shuttle's current travelled distance
     * @return the chosen Destination, or null if none remain
     */
    Destination selectNext(List<Destination> destinations, double currentProgress);
}
