/**
 * FuelTank manages fuel levels for the space shuttle.
 * 
 * @author Nihat Masimli DNGKKG
 */
public class FuelTank {
    private final double maxFuel;
    private double currentFuel;
    
    /**
     * Constructs a FuelTank with the specified maximum capacity.
     * The tank starts fully fuelled.
     * 
     * @param maxFuel the maximum fuel capacity
     */
    public FuelTank(double maxFuel){
        this.maxFuel = maxFuel;
        this.currentFuel = maxFuel;
    }
    
    /**
     * Consumes a percentage of the maximum fuel.
     * Fuel cannot drop below zero.
     * 
     * @param percent percentage of the max fuel to use (0-100)
     */
    public void useFuel(double percent){
        double amount = (percent / 100.0) * maxFuel;
        this.currentFuel = Math.max(this.currentFuel - amount, 0.0);
    }
    
    /**
     * Refuels the tank back to full capacity.
     */
    public void refuel(){
        this.currentFuel = maxFuel;
    }
    
    /**
     * Checks if the current fuel level is below 15% of the capacity.
     * 
     * @return true if low on fuel
     */
    public boolean isLowFuel(){
        return this.currentFuel < (0.15 * maxFuel);
    }
    
    /**
     * Retrieves the current fuel level.
     * 
     * @return current fuel
     */
    public double getCurrentFuel(){
        return currentFuel;
    }
    
    /**
     * Retrieves the maximum fuel capacity.
     * 
     * @return max fuel capacity
     */
    public double getMaxFuel(){
        return maxFuel;
    }
    
    @Override
    public String toString(){
        return String.format(
                "FuelTank{currentFuel=%.2f, maxFuel=%.2f}",
                currentFuel, maxFuel
        );
    }
}
