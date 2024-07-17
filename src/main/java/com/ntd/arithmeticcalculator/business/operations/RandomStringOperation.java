package com.ntd.arithmeticcalculator.business.operations;

import java.util.List;
import java.util.Random;

public class RandomStringOperation implements OperationExecutor{
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 10;
    private final Random random = new Random();

    @Override
    public String execute(List<String> values){
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
