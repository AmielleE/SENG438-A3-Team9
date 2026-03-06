package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

// Test class for Range.toString()

public class ToStringTest {

    // Testing: string output contains both bounds
    // Partition: Normal string representation
    // Expected: Output should contain -1 and 1
    @Test
    void toString_ShouldContainLowerAndUpperBounds() {
        Range r = new Range(-1, 1);

        String s = r.toString();

        assertNotNull(s);
        assertTrue(s.contains("-1"), "toString() should include the lower bound");
        assertTrue(s.contains("1"), "toString() should include the upper bound");
    }
    
    // new test for range with both bounds zero
    @Test
    void toString_ZeroRange() {
        Range r = new Range(0, 0);

        String s = r.toString();

        assertNotNull(s);
        assertTrue(s.contains("0"), "toString() should include the zero bounds");
    }

    // new test for range with negative bounds only
    @Test
    void toString_NegativeRange() {
        Range r = new Range(-5, -2);

        String s = r.toString();

        assertNotNull(s);
        assertTrue(s.contains("-5"), "toString() should include the lower bound");
        assertTrue(s.contains("-2"), "toString() should include the upper bound");
    }

    // new test for range with positive bounds only
    @Test
    void toString_PositiveRange() {
        Range r = new Range(3, 7);

        String s = r.toString();

        assertNotNull(s);
        assertTrue(s.contains("3"), "toString() should include the lower bound");
        assertTrue(s.contains("7"), "toString() should include the upper bound");
    }
}