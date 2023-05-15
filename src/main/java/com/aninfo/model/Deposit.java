package com.aninfo.model;

import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.exceptions.InvalidIdException;
import com.aninfo.service.AccountService;

import javax.persistence.Entity;

@Entity
public class Deposit extends Transaction{
    public Deposit(Double amount, Long cbu) {
        this.amount = amount;
        this.cbu = cbu;
    }

    public Deposit() {}

    public void transactAccount(AccountService accountService){
        if (accountService.findById(cbu).isEmpty()){
            throw new InvalidIdException("Cbu is not  existent");
        }
        accountService.deposit(cbu, amount, this);
    }
}
