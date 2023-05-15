package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.exceptions.InvalidIdException;

import com.aninfo.model.Account;
import com.aninfo.model.Deposit;
import com.aninfo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {
        if (accountRepository.findById(cbu).isEmpty())
            throw new InvalidIdException("Cbu is not existent");
        return accountRepository.findById(cbu);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long cbu) {
        if (accountRepository.findById(cbu).isEmpty())
            throw new InvalidIdException("Cbu is not existent");
        accountRepository.deleteById(cbu);
    }

    @Transactional
    public Account withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - sum);
        accountRepository.save(account);

        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double sum, Deposit deposit) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }
        Double amount = promotion(sum);
        Account account = accountRepository.findAccountByCbu(cbu);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        deposit.setAmount(amount);

        return account;
    }

    private double promotion(Double sum){
        double new_sum;
        if (sum<2000)
            new_sum = sum;
        else if (sum>=5000)
            new_sum = sum+500;
        else
            new_sum = sum+sum*0.1;
        return new_sum;
    }

}
