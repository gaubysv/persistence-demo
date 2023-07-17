package org.example.persistencedemo.domain;

import javax.persistence.Entity;

@Entity
public class BankAccount extends BillingDetails {

    private String account;
    private String bankName;
    private String swift;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }
}
