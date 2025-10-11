import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArraySorterTest {

  private double[] nums = new double[]{6, 2, 1, 8};
  private double[] list1 = new double[]{1, 2, 3, 4};
  private double[] list2 = new double[]{1, 2, 3, 4, 6};

@Test
  public void testNullPattern() {
    // check that the method returns false
    assertFalse(ArraySorter.sortInPlace(null, true, nums));
    // check that the nums array has not been modified
    assertArrayEquals(new double[]{6, 2, 1, 8}, nums);
  }

  @Test
  public void testValidPatternOneNullList() {
    assertFalse(ArraySorter.sortInPlace(nums, true, list1, null));
    assertArrayEquals(new double[]{6, 2, 1, 8}, nums);
  }

  @Test
  public void testValidPatternTwoNullList() {
    assertFalse(ArraySorter.sortInPlace(nums, true, null, null));
    assertArrayEquals(new double[]{6, 2, 1, 8}, nums);
  }

  @Test
  public void testValidPatternInvalidListLength() {
    assertFalse(ArraySorter.sortInPlace(nums, true, list2));
    assertArrayEquals(new double[]{6, 2, 1, 8}, nums);
  }

  @Test
  public void testValidPatternValidListAscend() {
    assertTrue(ArraySorter.sortInPlace(nums, true, list1));
    assertArrayEquals(new double[]{1, 2, 6, 8}, nums);
    assertArrayEquals(new double[]{3, 2, 1, 4}, list1);
  }

  @Test
  public void testValidPatternValidListDescend() {
    assertTrue(ArraySorter.sortInPlace(nums, false, list1));
    assertArrayEquals(new double[]{8, 6, 2, 1}, nums);
    assertArrayEquals(new double[]{4, 1, 2, 3}, list1);
  }


}
