package com.ntd.arithmeticcalculator.service.impl;

import com.ntd.arithmeticcalculator.model.Status;
import com.ntd.arithmeticcalculator.model.entity.User;
import com.ntd.arithmeticcalculator.repository.UserRepository;
import com.ntd.arithmeticcalculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        user.setStatus(Status.ACTIVE);
        user.setCredits(1000D);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> update(Long id, User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    user.setPassword(userDetails.getPassword());
                    user.setStatus(userDetails.getStatus());
                    userRepository.save(user);
                    return user;
                });
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}