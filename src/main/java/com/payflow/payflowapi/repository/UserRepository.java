package com.payflow.payflowapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.payflow.payflowapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Derived query: Spring Data parses "findByUpiId" into
    // "select u from User u where u.upiId = ?1" at startup.
    Optional<User> findByUpiId(String upiId);

    // Same idea, expressed explicitly as JPQL instead of a derived name.
    @Query("SELECT u FROM User u WHERE u.balance > :amount")
    List<User> findUsersWithBalanceAbove(@Param("amount") Double amount);
}
