package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test class for DataUtilities.calculateRowTotal()
// This is a mock test because Values2D is an interface, so we simulate it using Mockito

public class CalculateRowTotalTest {

    private Values2D values;

    @BeforeEach
    void setUp() {
        values = mock(Values2D.class);
    }

    // Testing: normal behaviour where row contains valid numbers
    // Partition: Valid data, normal row index
    // Expected: Sum of row values
    @Test
    void testCalculateRowTotal_ValidData_ShouldReturnSum() {
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(2.0);
        when(values.getValue(0, 1)).thenReturn(3.0);

        double result = DataUtilities.calculateRowTotal(values, 0);

        // Expected total is 2 + 3 = 5
        assertEquals(5.0, result, 0.0000001);
    }

    // Testing: null input
    // Partition: Invalid input
    // Expected: IllegalArgumentException thrown
    @Test
    void testCalculateRowTotal_NullData_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.calculateRowTotal(null, 0);
        });
    }
    
    // new test for if there's 0 columns
    @Test
    void testCalculateRowTotal_ZeroColumns_ShouldReturnZero() {
        when(values.getColumnCount()).thenReturn(0);

        double result = DataUtilities.calculateRowTotal(values, 0);

        assertEquals(0.0, result, 0.0000001);
    }

    // new test for if there's a row with null values
    @Test
    void testCalculateRowTotal_NullValues_ShouldIgnoreNulls() {
        when(values.getColumnCount()).thenReturn(3);

        when(values.getValue(0, 0)).thenReturn(4.0);
        when(values.getValue(0, 1)).thenReturn(null);   // ignored
        when(values.getValue(0, 2)).thenReturn(6.0);

        double result = DataUtilities.calculateRowTotal(values, 0);

        assertEquals(10.0, result, 0.0000001);
    }
    
    // new test for negative values
    @Test
    void testCalculateRowTotal_NegativeValues() {
        when(values.getColumnCount()).thenReturn(2);

        when(values.getValue(0, 0)).thenReturn(-2.0);
        when(values.getValue(0, 1)).thenReturn(-3.0);

        double result = DataUtilities.calculateRowTotal(values, 0);

        assertEquals(-5.0, result, 0.0000001);
    }

    // new test for when there's mixed values
    @Test
    void testCalculateRowTotal_MixedValues() {
        when(values.getColumnCount()).thenReturn(3);

        when(values.getValue(0, 0)).thenReturn(10.0);
        when(values.getValue(0, 1)).thenReturn(-5.0);
        when(values.getValue(0, 2)).thenReturn(2.0);

        double result = DataUtilities.calculateRowTotal(values, 0);

        assertEquals(7.0, result, 0.0000001);
    }
}
