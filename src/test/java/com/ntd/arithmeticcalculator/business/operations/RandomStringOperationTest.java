package com.ntd.arithmeticcalculator.business.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomStringOperationTest {

    private static final int LENGTH = 10;
    private RandomStringOperation randomStringOperation;

    @BeforeEach
    public void setUp() {
        randomStringOperation = new RandomStringOperation();
    }

    @Test
    public void testExecuteLength() {
        String result = randomStringOperation.execute(Collections.emptyList());
        assertEquals(LENGTH, result.length());
    }

    @Test
    public void testExecuteContent() {
        String result = randomStringOperation.execute(Collections.emptyList());
        assertTrue(result.matches("^[A-Za-z0-9]{" + LENGTH + "}$"));
    }
}