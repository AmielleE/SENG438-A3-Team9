package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

// Test class for Range.equals(Object obj)

public class EqualsTest {

    // Testing: two Range objects with same bounds are equal
    // Partition: Two Range objects with same bounds
    // Expected: true
    @Test
    void equals_SameBounds_ShouldReturnTrue() {
        Range r1 = new Range(0, 1);
        Range r2 = new Range(0, 1);
        assertTrue(r1.equals(r2));
    }

    // Testing: two Range objects with different bounds are not equal
    // Partition: Two Range objects with different bounds
    // Expected: false
    @Test
    void equals_DifferentBounds_ShouldReturnFalse() {
        Range r1 = new Range(0, 1);
        Range r2 = new Range(0, 2);
        assertFalse(r1.equals(r2));
    }

    // Testing: robustness when comparing to null or different type
    // Partition: Null object or different type
    // Expected: false
    @Test
    void equals_NullOrDifferentType_ShouldReturnFalse() {
        Range r = new Range(0, 1);
        assertFalse(r.equals(null));
        assertFalse(r.equals((Object) "not a range"));
    }
    
    // New test for object compared with itself
    // Partition: Same object reference
    // Expected: true
    @Test
    void equals_SameObjectReference_ShouldReturnTrue() {
        Range r = new Range(2, 5);
        assertTrue(r.equals(r));
    }

    // New test for same lower bound but different upper bound
    // Partition: Partially matching bounds
    // Expected: false
    @Test
    void equals_SameLowerDifferentUpper_ShouldReturnFalse() {
        Range r1 = new Range(0, 5);
        Range r2 = new Range(0, 10);
        assertFalse(r1.equals(r2));
    }

    // New test for same upper bound but different lower bound
    // Partition: Partially matching bounds
    // Expected: false
    @Test
    void equals_DifferentLowerSameUpper_ShouldReturnFalse() {
        Range r1 = new Range(0, 5);
        Range r2 = new Range(-2, 5);
        assertFalse(r1.equals(r2));
    } 

}