package com.ntd.arithmeticcalculator.model.request;

import lombok.Data;
import java.util.List;

@Data
public class OperationRequest {

    private Long operationId;

    private Long userId;

    private List<String> value;

}
