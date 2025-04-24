package mm.model;

import org.junit.jupiter.api.Test;

import mm.model.physics.PhysicMathUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing the Example class with its arbitrary arithmetical computations
 */
public class ExampleTest {
    /**
     * This tes case doesn't make any sense. It is just here as an example.
     */
    @Test
    public void foo() {
        assertEquals(32, PhysicMathUtils.meterToPixel(1), "PhysicsMathUtils returns the correct pixel size for a meter.");
    }
}
