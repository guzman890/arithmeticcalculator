package com.ntd.arithmeticcalculator.business.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SumOperationEntityTest {

    private SumOperation sumOperation;

    @BeforeEach
    public void setUp() {
        sumOperation = new SumOperation();
    }

    @Test
    public void testExecuteSuccess() {
        String result = sumOperation.execute(Arrays.asList("10", "5"));
        assertEquals("15.0", result);
    }

    @Test
    public void testExecuteNotEnoughValues() {
        assertThrows(IllegalArgumentException.class, () -> sumOperation.execute(Collections.singletonList("10")));
    }
}