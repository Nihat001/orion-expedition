

/**
 * Engine provides thrust for the space shuttle, consuming fuel and enabling progress.
 * 
 * @author Nihat Masimli 
 */
public class Engine {
    private boolean running;
    
    /**
     * Starts the engine.
     */
    public void start(){
        this.running = true;
    }
    
    /**
     * Stops the engine.
     */
    public void stop(){
        this.running = false;
    }
    
    /**
     * Checks if the engine is currently running.
     * 
     * return true if running
     */
    public boolean isRunning(){
        return running;
    }
    
    /**
     * Applies thrust: consumes 10% of max fuel and yields 5% progress.
     * If engine is not running, this call has no effect.
     * 
     * @param tank the FuelTank to draw fuel from
     * @return progress increment as a percentage (5.0 if running, else 0.0)
     */
    public double thrust(FuelTank tank){
        if(!running){
            return 0.0;
        }
        //Consume 10% of max capacity
        tank.useFuel(10.0);
        //Return 5% progress increment
        return 5.0;
    }
    
    @Override
    public String toString(){
        return String.format("Engine{running=%b}", running);
    }
}
