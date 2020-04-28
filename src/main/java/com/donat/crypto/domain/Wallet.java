package com.donat.crypto.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "wallets")
@Data
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_generator")
    @SequenceGenerator(name = "wallet_generator", sequenceName = "wallet_seq")
    private Long id;

    @NotNull
    @Size(max=10)
    @Column(name = "ccy")
    private String ccy;

    @Column(name = "amount")
    private Double amount;

    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @Version
    @Column(name = "version")
    private long version;

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
