package com.payflow.payflowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payflow.payflowapi.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
