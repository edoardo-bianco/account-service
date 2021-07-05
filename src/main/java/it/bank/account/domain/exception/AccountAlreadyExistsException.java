package it.bank.account.domain.exception;

public class AccountAlreadyExistsException extends AccountException {

    public AccountAlreadyExistsException(String accountNumber) {
        super(String.format("An account with account number %s already exists.", accountNumber));
    }

}
