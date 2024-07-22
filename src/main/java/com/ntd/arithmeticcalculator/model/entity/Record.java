package com.ntd.arithmeticcalculator.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;


@Data
@Entity
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "operation_id", nullable = false)
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "user_balance")
    private Double userBalance;

    @Column(name = "operation_response")
    private String operationResponse;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    // Constructors, Getters, and Setters
}