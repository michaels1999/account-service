package io.angelwing.account.service.repository;

import io.angelwing.account.service.model.Account;
import io.angelwing.account.service.model.User;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Collection<Account> findAll();

    Optional<Account> findById(UUID id);

    Collection<Account> findByUser(User user);
}
