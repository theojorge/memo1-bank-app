package com.aninfo.service;

import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction, AccountService accountService) {
        transaction.transactAccount(accountService);
        return transactionRepository.save(transaction);}

    public Collection<Transaction> getTransactions() {return transactionRepository.findAll();}

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public Collection<Transaction> findByCbu(Long cbu) {return transactionRepository.findTransactionsByCbu(cbu);}

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteById(Long id) {transactionRepository.deleteById(id); }


}
