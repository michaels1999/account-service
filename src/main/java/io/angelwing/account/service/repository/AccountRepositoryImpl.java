package io.angelwing.account.service.repository;

import io.angelwing.account.service.model.Account;
import io.angelwing.account.service.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class AccountRepositoryImpl implements AccountRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Collection<Account> findAll() {
        return entityManager.createQuery("from Account" , Account.class).getResultList();
    }

    @Override
    public Optional<Account> findById(final UUID id) {
        return Optional.ofNullable(entityManager.find(Account.class , id));
    }

    @Override
    public Collection<Account> findByUser(final User user) {
        return entityManager.createQuery("from Account where user_email = ?1", Account.class)
                .setParameter(1, user.getEmail())
                .getResultList();
    }
}

