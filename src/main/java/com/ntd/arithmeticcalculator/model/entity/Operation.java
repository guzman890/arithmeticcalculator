package com.ntd.arithmeticcalculator.model.entity;

import com.ntd.arithmeticcalculator.model.OperationType;
import jakarta.persistence.*;
import java.util.Set;
import lombok.Data;

@Data
@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label")
    private String label;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OperationType type;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "cost")
    private Double cost;

    @OneToMany(mappedBy = "operation")
    private Set<Record> records;

}