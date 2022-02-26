package io.angelwing.account.service.repository;


import io.angelwing.account.service.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Collection<User> findAll() {
        return entityManager.createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return Optional.ofNullable(entityManager.find(User.class , email));
    }
}
