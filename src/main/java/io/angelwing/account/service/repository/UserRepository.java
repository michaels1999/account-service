package io.angelwing.account.service.repository;


import io.angelwing.account.service.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {

    Collection<User> findAll();

    Optional<User> findByEmail(String email);
}
