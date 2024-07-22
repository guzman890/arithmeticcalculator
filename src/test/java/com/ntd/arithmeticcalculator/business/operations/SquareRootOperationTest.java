package com.ntd.arithmeticcalculator.business.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SquareRootOperationTest {

    private SquareRootOperation squareRootOperation;

    @BeforeEach
    public void setUp() {
        squareRootOperation = new SquareRootOperation();
    }

    @Test
    public void testExecuteSuccess() {
        String result = squareRootOperation.execute(Arrays.asList("4"));
        assertEquals("2.0", result);
    }

    @Test
    public void testExecuteNotEnoughValues() {
        assertThrows(IllegalArgumentException.class, () -> squareRootOperation.execute(Collections.emptyList()));
    }
}