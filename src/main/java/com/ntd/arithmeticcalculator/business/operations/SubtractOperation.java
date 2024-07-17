package com.ntd.arithmeticcalculator.business.operations;

import java.util.List;

public class SubtractOperation implements OperationExecutor {
    @Override
    public String execute(List<String> values) {
        if (values.size() < 2) {
            throw new IllegalArgumentException("Not enough values for operation.");
        }
        double a = Double.parseDouble(values.get(0));
        double b = Double.parseDouble(values.get(1));

        double result = a - b;
        return String.valueOf(result);
    }
}
