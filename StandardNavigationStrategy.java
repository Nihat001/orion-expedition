// StandardNavigationStrategy.java
/**
 * @author Nihat Masimli
 */
import java.util.List;

/**
 * Chooses the closest destination (minimal remaining distance).
 */
public class StandardNavigationStrategy implements NavigationStrategy {
    @Override
    public Destination selectNext(List<Destination> destinations, double currentProgress) {
        Destination best = null;
        double bestDist = Double.MAX_VALUE;
        for (Destination d : destinations) {
            d.updateProgress(currentProgress);
            double rem = d.distanceRemaining();
            if (rem < bestDist) {
                bestDist = rem;
                best = d;
            }
        }
        return best;
    }
}
