// Orbiter.java
/**
 * @author Nihat Masimli
 */
import java.util.Random;

/**
 * Controls the shuttle's voyage, handling state transitions
 * (Docked → Cruising → Orbiting → Emergency).
 * 
 * @author Nihat Masimli DNGKKG
 */
public class Orbiter {
    private Destination currentDestination;
    private Destination nextDestination;
    private final SpaceShuttle shuttle;
    private final Random random;
    private ShuttleState state;

    /**
     * Binds this orbiter to the shuttle; starts docked at Earth.
     *
     * @param shuttle the SpaceShuttle being controlled
     */
    public Orbiter(SpaceShuttle shuttle) {
        this.shuttle = shuttle;
        this.random = new Random();
        this.state = ShuttleState.DOCKED;
        this.currentDestination = new Destination("Earth", 0, 0, 0);
        this.nextDestination = shuttle.getNavigationSystem()
                                      .getNextDestination(0.0);
    }

    /** Begins the expedition through all remaining destinations. */
    public void startCourse() {
        System.out.println("Initial state: " + state);
        while (nextDestination != null) {
            System.out.println("\nHeading to " + nextDestination.getName());
            dockedActions();
            if (state == ShuttleState.DOCKED) {
                System.out.println("Stuck docked. Aborting mission.");
                return;
            }
            cruiseToDestination();
            if (state == ShuttleState.EMERGENCY && 
                shuttle.getFuelTank().getCurrentFuel() <= 0) {
                return; // cannot continue
            }
            if (state == ShuttleState.ORBITING) {
                orbitAndObserve();
                // Remove and update targets
                shuttle.getNavigationSystem()
                       .removeReachedDestinations(shuttle.getProgress());
                currentDestination = nextDestination;
                nextDestination = shuttle.getNavigationSystem()
                                         .getNextDestination(shuttle.getProgress());
            }
        }
        System.out.println("\nAll destinations reached. Mission complete!");
    }

    /** In DOCKED state: refuel if needed, then launch engine to cruise. */
    private void dockedActions() {
        if (shuttle.getFuelTank().isLowFuel()) {
            System.out.println("Docked: refueling...");
            shuttle.getFuelTank().refuel();
        }
        shuttle.getEngine().start();
        state = ShuttleState.CRUISING;
    }

    /** In CRUISING state: thrust until arrival or emergency. */
    private void cruiseToDestination() {
        state = ShuttleState.CRUISING;
        shuttle.getEngine().start();
        while (state == ShuttleState.CRUISING) {
            double progInc = shuttle.getEngine()
                                    .thrust(shuttle.getFuelTank());
            shuttle.incrementProgress(progInc);
            nextDestination.updateProgress(shuttle.getProgress());
            System.out.printf(
                "Travel: %.2f/%.2f to %s%n",
                nextDestination.getProgress(),
                nextDestination.getDistanceFromEarth(),
                nextDestination.getName()
            );
            if (nextDestination.isReached()) {
                System.out.println("Arrived at " + nextDestination.getName());
                shuttle.getEngine().stop();
                state = ShuttleState.ORBITING;
                break;
            }
            if (shuttle.getFuelTank().isLowFuel()) {
                System.out.println("Fuel critical—entering EMERGENCY.");
                shuttle.getEngine().stop();
                state = ShuttleState.EMERGENCY;
                emergencyProcedure();
            }
        }
    }

    /** In ORBITING state: perform observations until complete or emergency. */
    private void orbitAndObserve() {
        System.out.println("Orbiting and observing " + nextDestination.getName());
        state = ShuttleState.ORBITING;
        while (!nextDestination.isObservationComplete()) {
            shuttle.getFuelTank().useFuel(3.0); // reduced consumption
            nextDestination.updateObservation(5.0);
            System.out.printf(
                "Observation: %.2f%% complete%n",
                nextDestination.getObservationCompletion()
            );
            if (shuttle.getFuelTank().isLowFuel()) {
                System.out.println("Fuel critical during observation—EMERGENCY.");
                state = ShuttleState.EMERGENCY;
                emergencyProcedure();
                if (state != ShuttleState.CRUISING) {
                    return;
                }
                System.out.println("Resuming observations...");
            }
        }
        System.out.println("Observations complete at " + nextDestination.getName());
    }

    /** Handles low-fuel emergencies: attempt refuel, then either resume or dock. */
    private void emergencyProcedure() {
        System.out.println("Attempting emergency refuel...");
        if (random.nextDouble() < 0.5) {
            shuttle.getFuelTank().refuel();
            System.out.println("Emergency refuel succeeded.");
            // resume cruising
            shuttle.getEngine().start();
            state = ShuttleState.CRUISING;
        } else {
            System.out.println("Emergency refuel failed. Docking shuttle.");
            state = ShuttleState.DOCKED;
            shuttle.getEngine().stop();
        }
    }
}
