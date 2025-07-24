

import java.util.List;

/**
 * @author Nihat Masimli DNGKKG
 */

/**
 * Chooses the destination minimizing a fuel-to-distance ratio:
 * consumptionRate / remainingDistance.
 */
public class EfficientNavigationStrategy implements NavigationStrategy {
    @Override
    public Destination selectNext(List<Destination> destinations, double currentProgress) {
        Destination best = null;
        double bestRatio = Double.MAX_VALUE;
        for (Destination d : destinations) {
            d.updateProgress(currentProgress);
            double rem = d.distanceRemaining();
            if (rem <= 0) continue;
            double ratio = d.getConsumptionRate() / rem;
            if (ratio < bestRatio) {
                bestRatio = ratio;
                best = d;
            }
        }
        return best;
    }
}
