package com.ntd.arithmeticcalculator.business.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultiplicationOperationEntityTest {

    private MultiplicationOperation multiplicationOperation;

    @BeforeEach
    public void setUp() {
        multiplicationOperation = new MultiplicationOperation();
    }

    @Test
    public void testExecuteSuccess() {
        String result = multiplicationOperation.execute(Arrays.asList("10", "2"));
        assertEquals("20.0", result);
    }

    @Test
    public void testExecuteNotEnoughValues() {
        assertThrows(IllegalArgumentException.class, () -> multiplicationOperation.execute(Collections.singletonList("10")));
    }
}