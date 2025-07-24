
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * JUnit4 tests for the Orion Expedition project.
 * Covers Destination, FuelTank, Engine, NavigationSystem with all modes,
 * and SpaceShuttle file loading and mode selection.
 */
public class OrionExpeditionTest {

    private Destination dest;
    private FuelTank tank;
    private Engine engine;
    private NavigationSystem nav;
    private SpaceShuttle shuttle;

    @Before
    public void setUp() {
        dest = new Destination("Test", 100.0, 5.0, 10.0);
        tank = new FuelTank(1000.0);
        engine = new Engine();
        nav = new NavigationSystem();
        shuttle = new SpaceShuttle(1000.0);
    }

    // ---- Destination tests ----

    @Test
    public void testDestinationInitialState() {
        assertEquals(100.0, dest.distanceRemaining(), 0.0001);
        assertFalse(dest.isReached());
        assertEquals("Test", dest.getName());
        assertEquals(5.0, dest.getScientificValue(), 0.0001);
        assertEquals(10.0, dest.getConsumptionRate(), 0.0001);
    }

    @Test
    public void testUpdateProgressAndReach() {
        dest.updateProgress(50.0);
        assertEquals(50.0, dest.getProgress(), 0.0001);
        assertEquals(50.0, dest.distanceRemaining(), 0.0001);
        assertFalse(dest.isReached());

        dest.updateProgress(200.0);
        assertEquals(100.0, dest.getProgress(), 0.0001);
        assertEquals(0.0, dest.distanceRemaining(), 0.0001);
        assertTrue(dest.isReached());
    }

    @Test
    public void testObservationProgress() {
        dest.updateObservation(30.0);
        assertEquals(30.0, dest.getObservationCompletion(), 0.0001);
        assertFalse(dest.isObservationComplete());

        dest.updateObservation(80.0);
        assertEquals(100.0, dest.getObservationCompletion(), 0.0001);
        assertTrue(dest.isObservationComplete());
    }

    // ---- FuelTank tests ----

    @Test
    public void testFuelConsumptionAndRefuel() {
        assertEquals(1000.0, tank.getCurrentFuel(), 0.0001);
        tank.useFuel(10.0);
        assertEquals(900.0, tank.getCurrentFuel(), 0.0001);
        assertFalse(tank.isLowFuel());

        tank.useFuel(85.0); // uses 850 of max
        assertEquals(50.0, tank.getCurrentFuel(), 0.0001);
        assertTrue(tank.isLowFuel());

        tank.refuel();
        assertEquals(1000.0, tank.getCurrentFuel(), 0.0001);
    }

    // ---- Engine tests ----

    @Test
    public void testEngineStartStopAndThrust() {
        // Initially off
        assertFalse(engine.isRunning());
        assertEquals(0.0, engine.thrust(tank), 0.0001);
        
        engine.start();
        assertTrue(engine.isRunning());
        // Thrust consumes 10% of max fuel and returns 5.0
        double before = tank.getCurrentFuel();
        double p = engine.thrust(tank);
        assertEquals(5.0, p, 0.0001);
        assertEquals(before - 100.0, tank.getCurrentFuel(), 0.0001);

        engine.stop();
        assertFalse(engine.isRunning());
        assertEquals(0.0, engine.thrust(tank), 0.0001);
    }

    // ---- NavigationSystem (STANDARD) tests ----

    @Test
    public void testStandardNavigationStrategy() {
        nav.setMode(NavigationMode.STANDARD);
        nav.addDestination(new Destination("A", 500, 1.0, 10.0));
        nav.addDestination(new Destination("B", 200, 1.0, 10.0));
        Destination next = nav.getNextDestination(0.0);
        assertNotNull(next);
        assertEquals("B", next.getName());
    }

    @Test
    public void testEfficientNavigationStrategy() {
        nav.setMode(NavigationMode.EFFICIENT);
        // A: rate=10, dist=500 => ratio=0.02
        // B: rate=15, dist=200 => ratio=0.075
        nav.addDestination(new Destination("A", 500, 1.0, 10.0));
        nav.addDestination(new Destination("B", 200, 1.0, 15.0));
        Destination next = nav.getNextDestination(0.0);
        assertNotNull(next);
        assertEquals("A", next.getName());
    }

    @Test
    public void testExplorationNavigationStrategy() {
        nav.setMode(NavigationMode.EXPLORATION);
        // A: value=5, B: value=9
        nav.addDestination(new Destination("A", 500, 5.0, 10.0));
        nav.addDestination(new Destination("B", 200, 9.0, 15.0));
        Destination next = nav.getNextDestination(0.0);
        assertNotNull(next);
        assertEquals("B", next.getName());
    }

    // ---- SpaceShuttle file loading & mode tests ----

    @Test
    public void testLoadDestinationFromFileAndModeSelection() throws IOException {
        // Create temp file
        File tmp = File.createTempFile("dest", ".txt");
        tmp.deleteOnExit();
        try (BufferedWriter w = new BufferedWriter(new FileWriter(tmp))) {
            // two-column line
            w.write("X 300\n");
            // four-column line
            w.write("Y 200 8.0 20.0\n");
        }
        // Load into shuttle
        shuttle.loadDestinationFromFile(tmp.getAbsolutePath());
        // STANDARD mode should pick Y (distance 200 < 300)
        shuttle.setNavigationMode(NavigationMode.STANDARD);
        Destination nextStd = shuttle.getNavigationSystem()
                                     .getNextDestination(0.0);
        assertNotNull(nextStd);
        assertEquals("Y", nextStd.getName());

        // EXPLORATION mode picks highest scientific value (Y:8 > X:0)
        shuttle.setNavigationMode(NavigationMode.EXPLORATION);
        Destination nextExp = shuttle.getNavigationSystem()
                                     .getNextDestination(0.0);
        assertNotNull(nextExp);
        assertEquals("Y", nextExp.getName());

        // EFFICIENT mode: ratio X=10/300≈0.0333, Y=20/200=0.1 → pick X
        shuttle.setNavigationMode(NavigationMode.EFFICIENT);
        Destination nextEff = shuttle.getNavigationSystem()
                                     .getNextDestination(0.0);
        assertNotNull(nextEff);
        assertEquals("X", nextEff.getName());
    }

    @Test
    public void testLaunchWithNoDestinations() {
        // Should not throw and should not crash
        shuttle.launch();
        // progress remains zero
        assertEquals(0.0, shuttle.getProgress(), 0.0001);
    }
}
