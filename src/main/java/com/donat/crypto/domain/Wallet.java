package com.donat.crypto.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "wallets")
@Data
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ccy", length = 10, nullable = false)
    private String ccy;

    @Column(name = "amount")
    private Double amount;

    //TODO tól/ig érvényesség dátum bevezetése, hogy a vagyon alakulását lehessen követni
    //Ezt kezelni kell majd a repo szinten is természetesen

    public Wallet() {
    }

    public Wallet(String ccy, Double amount) {
        this.ccy = ccy;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(ccy, wallet.ccy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ccy);
    }
}
