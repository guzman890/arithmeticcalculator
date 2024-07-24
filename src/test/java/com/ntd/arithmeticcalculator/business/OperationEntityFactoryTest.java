package com.ntd.arithmeticcalculator.business;


import com.ntd.arithmeticcalculator.business.operations.*;
import com.ntd.arithmeticcalculator.model.OperationType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationEntityFactoryTest {

    @Test
    public void testGetAdditionOperation() {
        assertTrue(OperationFactory.getOperation(OperationType.ADDITION) instanceof SumOperation);
    }

    @Test
    public void testGetSubtractionOperation() {
        assertTrue(OperationFactory.getOperation(OperationType.SUBTRACTION) instanceof SubtractOperation);
    }

    @Test
    public void testGetMultiplicationOperation() {
        assertTrue(OperationFactory.getOperation(OperationType.MULTIPLICATION) instanceof MultiplicationOperation);
    }

    @Test
    public void testGetDivisionOperation() {
        assertTrue(OperationFactory.getOperation(OperationType.DIVISION) instanceof DivisionOperation);
    }

    @Test
    public void testGetSquareRootOperation() {
        assertTrue(OperationFactory.getOperation(OperationType.SQUARE_ROOT) instanceof SquareRootOperation);
    }

    @Test
    public void testGetRandomStringOperation() {
        assertTrue(OperationFactory.getOperation(OperationType.RANDOM_STRING) instanceof RandomStringOperation);
    }

}