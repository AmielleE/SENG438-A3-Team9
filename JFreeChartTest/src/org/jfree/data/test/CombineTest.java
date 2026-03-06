package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

// Test class for Range.combine(Range range1, Range range2)

public class CombineTest {
    private static final double EPS = 1e-9;

    // Testing: normal behaviour with two valid overlapping ranges
    // Partition: Two valid non null overlapping ranges
    // Expected: Combined range should be [0,10]
    @Test
    void combine_TwoNonNullRanges_ShouldReturnMinLowerMaxUpper() {
        Range r1 = new Range(0, 5);
        Range r2 = new Range(3, 10);

        try {
            Range combined = Range.combine(r1, r2);

            assertNotNull(combined, "combine() should not return null for two non-null ranges");
            assertEquals(0.0, combined.getLowerBound(), EPS, "Lower bound should be min of inputs");
            assertEquals(10.0, combined.getUpperBound(), EPS, "Upper bound should be max of inputs");
        } catch (IllegalArgumentException ex) {
            fail("combine() threw IllegalArgumentException for valid ranges: " + ex.getMessage());
        }
    }

    // Testing: null handling when first range is null
    // Partition: First range is null
    // Expected: Should return [1,2]
    @Test
    void combine_FirstNull_ShouldReturnSecond() {
        Range r2 = new Range(1, 2);

        Range combined = Range.combine(null, r2);

        assertNotNull(combined);
        assertEquals(1.0, combined.getLowerBound(), EPS);
        assertEquals(2.0, combined.getUpperBound(), EPS);
    }

    // Testing: null handling when second range is null
    // Partition: Second range is null
    // Expected: Should return [-2,4]
    @Test
    void combine_SecondNull_ShouldReturnFirst() {
        Range r1 = new Range(-2, 4);

        Range combined = Range.combine(r1, null);

        assertNotNull(combined);
        assertEquals(-2.0, combined.getLowerBound(), EPS);
        assertEquals(4.0, combined.getUpperBound(), EPS);
    }

    // Testing: boundary case when both inputs are null
    // Partition: Both inputs null
    // Expected: Should return null
    @Test
    void combine_BothNull_ShouldReturnNull() {
        assertNull(Range.combine(null, null));
    }
    
    // New test for: disjoint ranges
    // Partition: non-overlapping ranges
    // Expected: combined range spans both
    @Test
    void combine_DisjointRanges_ShouldExpandToIncludeBoth() {
        Range r1 = new Range(0, 2);
        Range r2 = new Range(5, 8);

        Range combined = Range.combine(r1, r2);

        assertEquals(0.0, combined.getLowerBound(), EPS);
        assertEquals(8.0, combined.getUpperBound(), EPS);
    }

    // New test for identical ranges
    // Partition: same bounds
    // Expected: result unchanged
    @Test
    void combine_IdenticalRanges_ShouldReturnSameBounds() {
        Range r1 = new Range(2, 6);
        Range r2 = new Range(2, 6);

        Range combined = Range.combine(r1, r2);

        assertEquals(2.0, combined.getLowerBound(), EPS);
        assertEquals(6.0, combined.getUpperBound(), EPS);
    }

    // New test for one range completely inside another
    // Partition: nested ranges
    // Expected: outer range preserved
    @Test
    void combine_NestedRanges_ShouldReturnOuterRange() {
        Range r1 = new Range(0, 10);
        Range r2 = new Range(3, 5);

        Range combined = Range.combine(r1, r2);

        assertEquals(0.0, combined.getLowerBound(), EPS);
        assertEquals(10.0, combined.getUpperBound(), EPS);
    }

    // New test for negative values
    // Partition: ranges with negative bounds
    // Expected: correct min and max selected
    @Test
    void combine_NegativeRanges_ShouldReturnCorrectBounds() {
        Range r1 = new Range(-10, -2);
        Range r2 = new Range(-5, 4);

        Range combined = Range.combine(r1, r2);

        assertEquals(-10.0, combined.getLowerBound(), EPS);
        assertEquals(4.0, combined.getUpperBound(), EPS);
    }
}