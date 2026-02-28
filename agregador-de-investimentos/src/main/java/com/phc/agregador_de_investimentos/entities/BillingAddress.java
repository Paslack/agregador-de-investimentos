package com.phc.agregador_de_investimentos.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_billing_address")
public class BillingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String street;
    private Integer number;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public BillingAddress() {
    }

    public BillingAddress(Long id, String street, Integer number, Account account) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.account = account;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
