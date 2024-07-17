package com.ntd.arithmeticcalculator.business.operations;

import java.util.List;

public class SquareRootOperation implements OperationExecutor {
    @Override
    public String execute(List<String> values) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("Not enough values for operation.");
        }
        double a = Double.parseDouble(values.get(0));
        double result = Math.sqrt(a);
        return String.valueOf(result);
    }
}