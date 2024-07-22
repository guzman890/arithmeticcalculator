package com.ntd.arithmeticcalculator.service;

import com.ntd.arithmeticcalculator.model.entity.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    Optional<UserEntity> findById(Long id);
    List<UserEntity> findAll();
    Optional<UserEntity> update(Long id, UserEntity userDetails);
    void deleteUser(Long id);
}