

/**
 * Represents a celestial destination with scientific value,
 * observation progress, and a fuel consumption rate.
 * 
 * @author Nihat Masimli 
 */
public class Destination {
    private String name;
    private double distanceFromEarth;
    private double progress;
    private double scientificValue;
    private double observationCompletion;
    private double consumptionRate;

    /**
     * Constructs a new Destination.
     *
     * @param name              the name of the destination
     * @param distanceFromEarth the distance from Earth
     * @param scientificValue   a metric of the scientific interest
     * @param consumptionRate   percentage of max fuel consumed per thrust toward this destination
     */
    public Destination(String name, double distanceFromEarth,
                       double scientificValue, double consumptionRate) {
        this.name = name;
        this.distanceFromEarth = distanceFromEarth;
        this.scientificValue = scientificValue;
        this.consumptionRate = consumptionRate;
        this.progress = 0.0;
        this.observationCompletion = 0.0;
    }

    /**
     * Updates travel progress toward this destination.
     *
     * @param travelledDistance total distance the shuttle has travelled
     */
    public void updateProgress(double travelledDistance) {
        this.progress = Math.min(travelledDistance, this.distanceFromEarth);
    }

    /**
     * How much farther remains to reach this destination.
     *
     * @return remaining distance (>= 0)
     */
    public double distanceRemaining() {
        return Math.max(this.distanceFromEarth - this.progress, 0.0);
    }

    /**
     * True if travel progress has reached or passed the destination.
     */
    public boolean isReached() {
        return this.progress >= this.distanceFromEarth;
    }

    /**
     * Increment observation progress in orbit.
     *
     * @param percent how many percentage points to add
     */
    public void updateObservation(double percent) {
        this.observationCompletion =
            Math.min(this.observationCompletion + percent, 100.0);
    }

    /**
     * True if observation has reached 100%.
     */
    public boolean isObservationComplete() {
        return this.observationCompletion >= 100.0;
    }

    /* Getters */
    public String getName() { return name; }
    public double getDistanceFromEarth() { return distanceFromEarth; }
    public double getProgress() { return progress; }
    public double getScientificValue() { return scientificValue; }
    public double getObservationCompletion() { return observationCompletion; }
    public double getConsumptionRate() { return consumptionRate; }

    @Override
    public String toString() {
        return String.format(
            "Destination{name='%s', distance=%.2f, prog=%.2f, obs=%.2f%%, sci=%.2f, rate=%.2f%%}",
            name, distanceFromEarth, progress, observationCompletion,
            scientificValue, consumptionRate
        );
    }
}
