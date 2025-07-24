// ExplorationNavigationStrategy.java

import java.util.List;

/**
 * Chooses the destination with the highest scientific value.
 */
public class ExplorationNavigationStrategy implements NavigationStrategy {
    @Override
    public Destination selectNext(List<Destination> destinations, double currentProgress) {
        Destination best = null;
        double maxValue = Double.NEGATIVE_INFINITY;
        for (Destination d : destinations) {
            if (d.getScientificValue() > maxValue) {
                maxValue = d.getScientificValue();
                best = d;
            }
        }
        if (best != null) {
            best.updateProgress(currentProgress);
        }
        return best;
    }
}
