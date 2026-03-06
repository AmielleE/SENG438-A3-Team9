package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

// Test class for Range.hashCode()

public class HashCodeTest {

    // Testing: hashCode contract for equal objects
    // Partition: Equal objects (same bounds)
    // Expected: Same hash code for equivalent objects
    @Test
    void hashCode_EqualObjects_ShouldHaveSameHashCode() {
        Range r1 = new Range(0, 1);
        Range r2 = new Range(0, 1);

        assertTrue(r1.equals(r2));
        assertEquals(r1.hashCode(), r2.hashCode());
    }
    
    // New test for hashCode consistency
    // Partition: Same object called multiple times
    // Expected: Same hashCode every time
    @Test
    void hashCode_SameObjectMultipleCalls_ShouldBeConsistent() {
        Range r = new Range(2, 5);

        int hash1 = r.hashCode();
        int hash2 = r.hashCode();

        assertEquals(hash1, hash2);
    }

    // New test for different ranges should typically produce different hash codes
    // Partition: Different bounds
    // Expected: hash codes not equal
    @Test
    void hashCode_DifferentObjects_ShouldUsuallyBeDifferent() {
        Range r1 = new Range(0, 1);
        Range r2 = new Range(0, 2);

        assertNotEquals(r1.hashCode(), r2.hashCode());
    }

    // New test for ranges with negative bounds
    // Partition: Negative values
    // Expected: Valid hash code generated
    @Test
    void hashCode_NegativeBounds_ShouldStillGenerateHashCode() {
        Range r = new Range(-10, -2);

        int hash = r.hashCode();

        assertNotEquals(0, hash); // ensure a hash value is produced
    }
}