package com.ntd.arithmeticcalculator.business;

import com.ntd.arithmeticcalculator.business.operations.*;
import com.ntd.arithmeticcalculator.model.OperationType;

public class OperationFactory {
    public static OperationExecutor getOperation(OperationType operationType) {
        switch (operationType) {
            case ADDITION:
                return new SumOperation();
            case SUBTRACTION:
                return new SubtractOperation();
            case MULTIPLICATION:
                return new MultiplicationOperation();
            case DIVISION:
                return new DivisionOperation();
            case SQUARE_ROOT:
                return new SquareRootOperation();
            default:
                throw new IllegalArgumentException("Operation not supported.");
        }
    }
}
