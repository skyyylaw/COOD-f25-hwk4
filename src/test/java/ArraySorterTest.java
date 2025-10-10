package src.test.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ArraySorterTest {

    private double[] nums = new double[]{6, 2, 1, 8};


    @Test
    public void testNullPattern() {
        // check that the method returns false
        assertFalse(ArraySorter.sortInPlace(null, true, nums));
        // check that the nums array has not been modified
        assertArrayEquals(new double[]{6, 2, 1, 8}, nums);
    }
}
