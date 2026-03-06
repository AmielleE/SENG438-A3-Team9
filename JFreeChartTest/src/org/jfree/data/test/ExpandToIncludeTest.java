package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

// Test class for Range.expandToInclude(Range range, double value)

public class ExpandToIncludeTest {
    private static final double EPS = 1e-9;

    // Testing: null range creates a new single point range
    // Partition: Null range input
    // Expected: [5, 5]
    @Test
    void expandToInclude_NullRange_ShouldCreateSinglePointRange() {
        Range result = Range.expandToInclude(null, 5.0);

        assertNotNull(result);
        assertEquals(5.0, result.getLowerBound(), EPS);
        assertEquals(5.0, result.getUpperBound(), EPS);
    }

    // Testing: value inside range does not change bounds
    // Partition: Value inside existing range
    // Expected: [0, 10]
    @Test
    void expandToInclude_ValueInside_ShouldReturnUnchanged() {
        Range base = new Range(0, 10);

        Range result = Range.expandToInclude(base, 5);

        assertNotNull(result);
        assertEquals(0.0, result.getLowerBound(), EPS);
        assertEquals(10.0, result.getUpperBound(), EPS);
    }

    // Testing: value below lower bound expands the lower bound
    // Partition: Value below lower bound
    // Expected: [-2, 10]
    @Test
    void expandToInclude_ValueBelowLower_ShouldExpandLower() {
        Range base = new Range(0, 10);

        Range result = Range.expandToInclude(base, -2);

        assertNotNull(result);
        assertEquals(-2.0, result.getLowerBound(), EPS);
        assertEquals(10.0, result.getUpperBound(), EPS);
    }

    // Testing: value above upper bound expands the upper bound
    // Partition: Value above upper bound
    // Expected: [0, 12]
    @Test
    void expandToInclude_ValueAboveUpper_ShouldExpandUpper() {
        Range base = new Range(0, 10);

        Range result = Range.expandToInclude(base, 12);

        assertNotNull(result);
        assertEquals(0.0, result.getLowerBound(), EPS);
        assertEquals(12.0, result.getUpperBound(), EPS);
    }
    
    // New test for value exactly equal to lower bound
    // Partition: Boundary case (value == lowerBound)
    // Expected: Range unchanged
    @Test
    void expandToInclude_ValueEqualsLowerBound_ShouldReturnUnchanged() {
        Range base = new Range(0, 10);

        Range result = Range.expandToInclude(base, 0);

        assertNotNull(result);
        assertEquals(0.0, result.getLowerBound(), EPS);
        assertEquals(10.0, result.getUpperBound(), EPS);
    }

    // New test for value exactly equal to upper bound
    // Partition: Boundary case (value == upperBound)
    // Expected: Range unchanged
    @Test
    void expandToInclude_ValueEqualsUpperBound_ShouldReturnUnchanged() {
        Range base = new Range(0, 10);

        Range result = Range.expandToInclude(base, 10);

        assertNotNull(result);
        assertEquals(0.0, result.getLowerBound(), EPS);
        assertEquals(10.0, result.getUpperBound(), EPS);
    }

    // New test for range containing negative values
    // Partition: Negative range expansion
    // Expected: [-10, -1]
    @Test
    void expandToInclude_NegativeRange_ShouldExpandCorrectly() {
        Range base = new Range(-5, -1);

        Range result = Range.expandToInclude(base, -10);

        assertNotNull(result);
        assertEquals(-10.0, result.getLowerBound(), EPS);
        assertEquals(-1.0, result.getUpperBound(), EPS);
    }

    // New test for expanding range with very large value
    // Partition: Large expansion case
    // Expected: [0, 1000]
    @Test
    void expandToInclude_LargeValue_ShouldExpandUpperBound() {
        Range base = new Range(0, 10);

        Range result = Range.expandToInclude(base, 1000);

        assertNotNull(result);
        assertEquals(0.0, result.getLowerBound(), EPS);
        assertEquals(1000.0, result.getUpperBound(), EPS);
    }
}