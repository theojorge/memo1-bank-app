package com.aninfo.model;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InvalidTransactionCbuException;
import com.aninfo.service.AccountService;

import javax.persistence.Entity;

@Entity
public class Extraction extends Transaction{
    public Extraction(Double amount, Long cbu) {
        this.amount = amount;
        this.cbu = cbu;
    }

    public Extraction() {}

    public void transactAccount(AccountService accountService){
        if (accountService.findById(cbu).isEmpty()){
            throw new InvalidTransactionCbuException("Cbu is not  existent");
        }
        accountService.withdraw(cbu, amount); }
}
