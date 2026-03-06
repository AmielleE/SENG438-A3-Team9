package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

// Test class for Range.intersects(double lower, double upper)

public class IntersectsTest {

    // Testing: interval completely left of range
    // Partition: Interval completely left of range
    // Expected: false
    @Test
    void intersects_DisjointLeft_ShouldReturnFalse() {
        Range r = new Range(0, 10);
        assertFalse(r.intersects(-5, -1));
    }

    // Testing: interval completely right of range
    // Partition: Interval completely right of range
    // Expected: false
    @Test
    void intersects_DisjointRight_ShouldReturnFalse() {
        Range r = new Range(0, 10);
        assertFalse(r.intersects(11, 20));
    }

    // Testing: partially overlapping interval returns true
    // Partition: Partially overlapping interval
    // Expected: true
    @Test
    void intersects_Overlapping_ShouldReturnTrue() {
        Range r = new Range(0, 10);
        assertTrue(r.intersects(5, 15));
    }

    // Testing: interval fully contained within range returns true
    // Partition: Interval fully contained within range
    // Expected: true
    @Test
    void intersects_ContainedInterval_ShouldReturnTrue() {
        Range r = new Range(0, 10);
        assertTrue(r.intersects(2, 3));
    }

    // Testing: invalid interval where lower > upper
    // Partition: Invalid interval lower > upper
    // Expected: false
    @Test
    void intersects_InvalidIntervalLowerGreaterThanUpper_ShouldReturnFalse() {
        Range r = new Range(0, 10);
        assertFalse(r.intersects(5, 4));
    }
    
    // New test for interval touches lower boundary
    // Partition: Boundary case
    // Expected: true
    @Test
    void intersects_TouchesLowerBoundary_ShouldReturnTrue() {
        Range r = new Range(0, 10);
        assertTrue(r.intersects(-5, 0));
    }

    // New test for interval touches upper boundary
    // Partition: Boundary case
    // Expected: true
    @Test
    void intersects_TouchesUpperBoundary_ShouldReturnTrue() {
        Range r = new Range(0, 10);
        assertTrue(r.intersects(10, 15));
    }

    // New test for interval exactly matches the range
    // Partition: Exact match
    // Expected: true
    @Test
    void intersects_ExactMatch_ShouldReturnTrue() {
        Range r = new Range(0, 10);
        assertTrue(r.intersects(0, 10));
    }

    // New test for interval covers the entire range
    // Partition: Larger interval containing the range
    // Expected: true
    @Test
    void intersects_IntervalContainsRange_ShouldReturnTrue() {
        Range r = new Range(0, 10);
        assertTrue(r.intersects(-5, 20));
    }

    // New test for single point interval inside range
    // Partition: Degenerate interval
    // Expected: true
    @Test
    void intersects_SinglePointInsideRange_ShouldReturnTrue() {
        Range r = new Range(0, 10);
        assertTrue(r.intersects(5, 5));
    }

    // New test for single point outside range
    // Partition: Degenerate interval outside
    // Expected: false
    @Test
    void intersects_SinglePointOutsideRange_ShouldReturnFalse() {
        Range r = new Range(0, 10);
        assertFalse(r.intersects(11, 11));
    }
}