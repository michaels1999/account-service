package io.angelwing.account.service.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.util.Collection;

@Entity
@Table(name = "user_table")
public class User {

    @Id
    @Email(message = "Email is malformed")
    @Column(name = "user_email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_email")
    private Collection<Account> account;

    public User() {
        //NOOP
    }

    public User(final String email, final Collection<Account> account) {
        this.email = email;
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public Collection<Account> getAccounts() {
        return account;
    }

}
