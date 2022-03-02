package io.angelwing.account.service.repository;

import io.angelwing.account.service.model.Account;
import io.angelwing.account.service.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class AccountRepositoryImpl implements AccountRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Collection<Account> findAll() {
        return (Collection<Account>) entityManager
                .createStoredProcedureQuery("find_all_accounts", Account.class)
                .getResultList();
    }

    @Override
    public Optional<Account> findById(final UUID id) {
        return Optional.ofNullable((Account) entityManager
                .createStoredProcedureQuery("find_account_by_id", Account.class)
                .registerStoredProcedureParameter("_account_id", String.class, ParameterMode.IN)
                .setParameter("_account_id", id.toString())
                .getSingleResult());
    }

    @Override
    public Collection<Account> findByUser(final User user) {
        return (Collection<Account>) entityManager
                .createStoredProcedureQuery("find_all_accounts_by_user_email", Account.class)
                .registerStoredProcedureParameter("_user_email", String.class, ParameterMode.IN)
                .setParameter("_user_email", user.getEmail())
                .getResultList();
    }
}

