package com.ntd.arithmeticcalculator.business.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubtractOperationTest {

    private SubtractOperation subtractOperation;

    @BeforeEach
    public void setUp() {
        subtractOperation = new SubtractOperation();
    }

    @Test
    public void testExecuteSuccess() {
        String result = subtractOperation.execute(Arrays.asList("10", "2"));
        assertEquals("8.0", result);
    }

    @Test
    public void testExecuteNotEnoughValues() {
        assertThrows(IllegalArgumentException.class, () -> subtractOperation.execute(Collections.singletonList("10")));
    }
}