package io.angelwing.account.service.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @Column(name = "account_id")
    private UUID id;

    @NotBlank(message = "Account name should not be empty")
    private String name;

    @Positive(message = "Balance should be greater than zero")
    @NotNull(message = "Balance is missing")
    private Double balance;

    @NotNull(message = "Currency is missing")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "last_transaction_time")
    @NotNull(message = "Last Transaction Time is missing")
    @Past(message = "last Transaction Time should be before now")
    private LocalDateTime lastTransactionTime;

    public Account() {
        // NOOP
    }

    public Account(final UUID id,
                   final String name,
                   final Double balance,
                   final Currency currency,
                   final LocalDateTime lastTransactionTime) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.currency = currency;
        this.lastTransactionTime = lastTransactionTime;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDateTime getLastTransactionTime() {
        return lastTransactionTime;
    }

}
