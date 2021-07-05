package it.bank.account.domain.service;

import it.bank.account.domain.vo.Account;

import java.util.Collection;
import java.util.Optional;

public interface AccountRepository {
    Collection<Account> findAll();
    Optional<Account> findByAccountNumber(Long accountNumber);
    boolean existsByAccountNumber(Long accountNumber);
    Account save(Account account);
    void deleteByAccountNumber(Long accountNumber);

}