package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

// Test class for Range.shift(Range base, double delta)

public class ShiftTwoArgTest {
    private static final double EPS = 1e-9;

    // Testing: normal shift with positive delta
    // Partition: Normal shift with positive delta
    // Expected: [3, 5]
    @Test
    void shift_TwoArg_ShouldMoveBothBoundsByDelta() {
        Range base = new Range(1, 3);

        Range shifted = Range.shift(base, 2);

        assertNotNull(shifted);
        assertEquals(3.0, shifted.getLowerBound(), EPS);
        assertEquals(5.0, shifted.getUpperBound(), EPS);
    }
    
    // new test to shift a negative range with positive delta
    @Test
    void shift_NegativeRange_PositiveDelta() {
        Range base = new Range(-5, -2);
        Range shifted = Range.shift(base, 3);

        assertNotNull(shifted);
        assertEquals(-2.0, shifted.getLowerBound(), EPS);
        assertEquals(1.0, shifted.getUpperBound(), EPS);
    }

    // new test to shift a range that includes zero with negative delta
    @Test
    void shift_RangeIncludingZero_NegativeDelta() {
        Range base = new Range(-2, 2);
        Range shifted = Range.shift(base, -3);

        assertNotNull(shifted);
        assertEquals(-5.0, shifted.getLowerBound(), EPS);
        assertEquals(-1.0, shifted.getUpperBound(), EPS);
    }

    // new test to shift a zero-only range
    @Test
    void shift_ZeroRange_PositiveDelta() {
        Range base = new Range(0, 0);
        Range shifted = Range.shift(base, 4);

        assertNotNull(shifted);
        assertEquals(4.0, shifted.getLowerBound(), EPS);
        assertEquals(4.0, shifted.getUpperBound(), EPS);
    }

    // new test to shift by zero delta
    @Test
    void shift_ByZeroDelta() {
        Range base = new Range(1, 4);
        Range shifted = Range.shift(base, 0);

        assertNotNull(shifted);
        assertEquals(1.0, shifted.getLowerBound(), EPS);
        assertEquals(4.0, shifted.getUpperBound(), EPS);
    }
}