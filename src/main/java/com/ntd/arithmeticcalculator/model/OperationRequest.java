package com.ntd.arithmeticcalculator.model;

import lombok.Data;
import java.util.List;

@Data
public class OperationRequest {

    private Long operationId;

    private Long userId;

    private List<String> value;

}
