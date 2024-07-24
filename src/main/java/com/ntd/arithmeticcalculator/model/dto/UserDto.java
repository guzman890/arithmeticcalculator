package com.ntd.arithmeticcalculator.model.dto;

import com.ntd.arithmeticcalculator.model.Status;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private Double credits;
    private Status status;
}