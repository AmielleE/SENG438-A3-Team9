package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

public class RangeUpperBoundTest {

    // Normal range
    @Test
    void getUpperBound_NormalRange() {
        Range r = new Range(-1, 5);
        assertEquals(5.0, r.getUpperBound(), 1e-9);
    }

    // Zero range
    @Test
    void getUpperBound_ZeroRange() {
        Range r = new Range(0, 0);
        assertEquals(0.0, r.getUpperBound(), 1e-9);
    }

    // Negative range
    @Test
    void getUpperBound_NegativeRange() {
        Range r = new Range(-10, -2);
        assertEquals(-2.0, r.getUpperBound(), 1e-9);
    }

    // Invalid range
    @Test
    void getUpperBound_InvalidRange_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Range(5, 2).getUpperBound();
        });
    }
}