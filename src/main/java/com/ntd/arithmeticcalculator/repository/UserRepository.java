package com.ntd.arithmeticcalculator.repository;

import com.ntd.arithmeticcalculator.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}