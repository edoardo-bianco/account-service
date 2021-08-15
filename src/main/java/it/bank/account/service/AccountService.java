package it.bank.account.service;

import it.bank.account.domain.exception.AccountAlreadyExistsException;
import it.bank.account.domain.exception.AccountNotFoundException;
import it.bank.account.domain.vo.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
public class AccountService {
    @Inject
    private AccountRepository accountRepository;

    public Collection<Account> viewAccountList(){
        return accountRepository.findAll();
    }

    public Optional<Account> viewAccountDetails(Long accountNumber) {
        return Optional.ofNullable(accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(Long.toString(accountNumber))));
    }

    public Account addAccountToBankAccount(Account account){
        if(accountRepository.existsByAccountNumber(account.getAccountNumber())){
            throw new AccountAlreadyExistsException(Long.toString(account.getAccountNumber()));
        }
        return accountRepository.save(account);
    }

    public void removeAccountFromBankAccount(Long accountNumber){
        if(!accountRepository.existsByAccountNumber(accountNumber)){
            throw new AccountNotFoundException(Long.toString(accountNumber));
        }
        accountRepository.deleteByAccountNumber(accountNumber);
    }

    public Account editAccountDetail(Long accountNumber, Account account){
        Optional<Account>  existingAccount = accountRepository.findByAccountNumber(accountNumber);

        if(existingAccount.isEmpty()){
           return addAccountToBankAccount(account);
        }

        Account accountToUpdate = existingAccount.get();

        return accountRepository.save(accountToUpdate);

    }
}
