package com.payflow.payflowapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payflow.payflowapi.entity.Transaction;
import com.payflow.payflowapi.repository.TransactionRepository;

@Service
public class TransactionService {

    // Same mechanism as in UserService: Spring generates and injects a proxy
    // implementation of TransactionRepository at startup, so no manual wiring
    // or implementation class is needed here.
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction sendMoney(Transaction transaction) {
        // Balance deduction / validation is out of scope for this assignment -
        // this just persists the transaction record.
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
