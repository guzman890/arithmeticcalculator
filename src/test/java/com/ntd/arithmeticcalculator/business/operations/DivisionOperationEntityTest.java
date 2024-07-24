package com.ntd.arithmeticcalculator.business.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DivisionOperationEntityTest {

    private DivisionOperation divisionOperation;

    @BeforeEach
    public void setUp() {
        divisionOperation = new DivisionOperation();
    }

    @Test
    public void testExecuteSuccess() {
        String result = divisionOperation.execute(Arrays.asList("10", "2"));
        assertEquals("5.0", result);
    }

    @Test
    public void testExecuteDivisionByZero() {
        assertThrows(IllegalArgumentException.class, () -> divisionOperation.execute(Arrays.asList("10", "0")));
    }
}