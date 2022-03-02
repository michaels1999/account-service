package io.angelwing.account.service.repository;


import io.angelwing.account.service.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Collection<User> findAll() {
        return (Collection<User>) entityManager
                .createStoredProcedureQuery("find_all_users", User.class)
                .getResultList();
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return Optional.ofNullable((User) entityManager
                .createStoredProcedureQuery("find_user_by_email", User.class)
                .registerStoredProcedureParameter("_user_email", String.class, ParameterMode.IN)
                .setParameter("_user_email", email)
                .getSingleResult());
    }
}
