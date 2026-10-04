package com.payflow.payflowapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payflow.payflowapi.entity.User;
import com.payflow.payflowapi.repository.UserRepository;

@Service
public class UserService {

    // At startup, Spring scans the classpath, finds UserRepository (a JpaRepository
    // interface) and generates a proxy implementation for it, registering that proxy
    // as a bean in the application context. Because UserService is itself a @Service
    // bean, Spring resolves this @Autowired field by type and injects that proxy here
    // before the bean is handed out for use - we never call "new UserRepositoryImpl()".
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUpiId(String upiId) {
        return userRepository.findByUpiId(upiId);
    }

    public List<User> findUsersWithBalanceAbove(Double amount) {
        return userRepository.findUsersWithBalanceAbove(amount);
    }
}
