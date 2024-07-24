package com.ntd.arithmeticcalculator.model.entity;

import com.ntd.arithmeticcalculator.model.Status;
import jakarta.persistence.*;
import java.util.Set;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "credits", nullable = false)
    private Double credits;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "user")
    private Set<RecordEntity> records;

    // Constructors, Getters, and Setters
}
