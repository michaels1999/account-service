package io.angelwing.account.service.controller;

import io.angelwing.account.service.exception.AccountNotFoundException;
import io.angelwing.account.service.exception.UserNotFoundException;
import io.angelwing.account.service.model.Account;
import io.angelwing.account.service.model.User;
import io.angelwing.account.service.repository.AccountRepository;
import io.angelwing.account.service.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Collection;

@Path("users")
public class AccountServiceController {

    @Inject
    AccountRepository accountRepository;

    @Inject
    UserRepository userRepository;

    @GET
    public Collection<User> findAllUsers() {
        return userRepository.findAll();
    }

    @GET
    @Path("{email}")
    public User findUserByEmail(@PathParam("email") final String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    @GET
    @Path("{email}/accounts")
    public Collection<Account> findAccountsByEmail(@PathParam("email") final String email) {
        return userRepository.findByEmail(email)
                .map(user -> accountRepository.findByUser(user))
                .orElseThrow(AccountNotFoundException::new);
    }
}
