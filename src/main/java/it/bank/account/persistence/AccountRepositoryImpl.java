package it.bank.account.persistence;

import it.bank.account.domain.vo.Account;
import it.bank.account.domain.service.AccountRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class AccountRepositoryImpl implements AccountRepository {
    private static final Map<Long,Account> accounts = new ConcurrentHashMap<>();

    @PostConstruct
    public void setup(){

        accounts.put(123456789L, new Account(123456789L,987654321L, "George Baird",
                new BigDecimal("354.23")));

        accounts.put(121212121L, new Account(121212121L,888777666L, "Mary Taylor",
                new BigDecimal("560.03")));

        accounts.put(545454545L, new Account(545454545L,222444999L, "Diana Rigg",
                new BigDecimal("422.00")));

    }

    @Override
    public Collection<Account> findAll() {
        return accounts.values();
    }

    @Override
    public Optional<Account> findByAccountNumber(Long accountNumber) {
        return existsByAccountNumber(accountNumber) ? Optional.of(accounts.get(accountNumber)) : Optional.empty();
    }

    @Override
    public boolean existsByAccountNumber(Long accountNumber) {
        return Objects.nonNull(accounts.get(accountNumber));
    }

    @Override
    public Account save(Account account) {
        accounts.put(account.getAccountNumber(), account);
        return account;
    }

    @Override
    public void deleteByAccountNumber(Long accountNumber) {
        accounts.remove(accountNumber);

    }

}
