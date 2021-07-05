package it.bank.account.domain.vo;

import it.bank.account.domain.enumerator.AccountStatus;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private Long accountNumber;
    private Long customerNumber;
    private String customerName;
    private BigDecimal balance;
    private AccountStatus accountStatus = AccountStatus.OPEN;

    public Account() {
    }

    public Account(Long accountNumber, Long customerNumber, String customerName, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.balance = balance;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public void markOverdrawn(){
        accountStatus = AccountStatus.OVERDRAWN;
    }

    public void removeOverdrawn(){
        accountStatus = AccountStatus.OPEN;
    }

    public void close(){
        accountStatus = AccountStatus.CLOSED;
        balance = BigDecimal.valueOf(0);
    }

    public void withdrawnFunds(BigDecimal amount){
        balance = balance.subtract(amount);
    }

    public void addFunds(BigDecimal amount){
        balance = balance.add(amount);
    }

    public BigDecimal getBalance(){
        return balance;
    }

    public Long getAccountNumber(){
        return accountNumber;
    }

    public long getCustomerNumber(){
        return customerNumber;
    }

    public String getCustomerName(){
        return customerName;
    }

    public AccountStatus getStatus(){
        return accountStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNumber.equals(account.accountNumber) &&
                customerNumber.equals(account.customerNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, customerNumber);
    }


}
