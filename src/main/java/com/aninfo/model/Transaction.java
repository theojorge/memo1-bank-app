package com.aninfo.model;

import com.aninfo.service.AccountService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Optional;
@Entity
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected Long cbu;

    protected Double amount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {this.amount = amount; }

    public abstract void transactAccount(AccountService accountService);
}
