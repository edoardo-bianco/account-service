package it.bank.account.domain.exception;

public class AccountNotFoundException extends  AccountException{

    public AccountNotFoundException(String accountNumber) {
        super(String.format("An account with account number %s was not found.", accountNumber));
    }

}
