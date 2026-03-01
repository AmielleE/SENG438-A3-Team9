package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.Test;

// Test class for DataUtilities.createNumberArray2D()
// This is a regular test because it operates on a primitive 2D array and does not require mocking

public class CreateNumberArray2DTest {

    // Testing: normal behaviour with a valid 2D array of doubles
    // Partition: Valid input 2D array
    // Expected: Returns a 2D Number array with the same values
    @Test
    void testCreateNumberArray2D_ValidInput_ShouldReturnCorrect2DArray() {
        double[][] input = {
                {1.0, 2.0},
                {3.0, 4.0}
        };

        Number[][] result = DataUtilities.createNumberArray2D(input);

        assertEquals(1.0, result[0][0]);
        assertEquals(4.0, result[1][1]);
    }

    // Testing: null input
    // Partition: Invalid input (null array)
    // Expected: IllegalArgumentException thrown
    @Test
    void testCreateNumberArray2D_NullInput_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.createNumberArray2D(null);
        });
    }
    
    // new test for an empty array
    @Test
    void testCreateNumberArray2D_EmptyOuterArray() {
        double[][] input = {};

        Number[][] result = DataUtilities.createNumberArray2D(input);

        assertEquals(0, result.length);
    }

    // new test for a row with length=0
    @Test
    void testCreateNumberArray2D_EmptyInnerArray() {
        double[][] input = {{}};

        Number[][] result = DataUtilities.createNumberArray2D(input);

        assertEquals(1, result.length);
        assertEquals(0, result[0].length);
    }

    // new test for negative numbers
    @Test
    void testCreateNumberArray2D_NegativeValues() {
        double[][] input = {{-1.5, -2.5}};
        Number[][] result = DataUtilities.createNumberArray2D(input);

        assertEquals(-1.5, result[0][0]);
        assertEquals(-2.5, result[0][1]);
    }

    // new test for mixed values
    @Test
    void testCreateNumberArray2D_MixedValues() {
        double[][] input = {{0.0, -3.0, 5.0}};
        Number[][] result = DataUtilities.createNumberArray2D(input);

        assertEquals(0.0, result[0][0]);
        assertEquals(-3.0, result[0][1]);
        assertEquals(5.0, result[0][2]);
    }

    // new test for a 1 element matrix
    @Test
    void testCreateNumberArray2D_SingleElement() {
        double[][] input = {{7.5}};
        Number[][] result = DataUtilities.createNumberArray2D(input);

        assertEquals(7.5, result[0][0]);
    }

    // new test for if we have many rows of different lengths
    @Test
    void testCreateNumberArray2D_JaggedArray() {
        double[][] input = {{1.0, 2.0}, {3.0}};
        Number[][] result = DataUtilities.createNumberArray2D(input);

        assertEquals(1.0, result[0][0]);
        assertEquals(2.0, result[0][1]);
        assertEquals(3.0, result[1][0]);
    }    
}
