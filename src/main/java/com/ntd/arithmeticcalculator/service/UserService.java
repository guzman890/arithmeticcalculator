package com.ntd.arithmeticcalculator.service;

import com.ntd.arithmeticcalculator.model.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    Optional<User> update(Long id, User userDetails);
    void deleteUser(Long id);
}