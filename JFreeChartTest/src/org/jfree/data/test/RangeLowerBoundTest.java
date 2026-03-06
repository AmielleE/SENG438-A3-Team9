package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

public class RangeLowerBoundTest {

    // Partition: normal range with negative and positive
    // Expected: return -1
    @Test
    void getLowerBound_NormalRange() {
        Range r = new Range(-1, 1);
        assertEquals(-1.0, r.getLowerBound(), 1e-9);
    }

    // Partition: both bounds positive
    // Expected: return lower bound
    @Test
    void getLowerBound_PositiveRange() {
        Range r = new Range(3, 7);
        assertEquals(3.0, r.getLowerBound(), 1e-9);
    }

    // Partition: both bounds negative
    // Expected: return lower bound
    @Test
    void getLowerBound_NegativeRange() {
        Range r = new Range(-10, -2);
        assertEquals(-10.0, r.getLowerBound(), 1e-9);
    }

    // Partition: lower and upper equal
    // Expected: return the same value
    @Test
    void getLowerBound_ZeroRange() {
        Range r = new Range(0, 0);
        assertEquals(0.0, r.getLowerBound(), 1e-9);
    }

    // Partition: invalid range
    // Expected: constructor throws exception
    @Test
    void constructor_InvalidRange_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Range(5, 2);
        });
    }
}
