package io.angelwing.account.service.controller;

import io.angelwing.account.service.exception.AccountNotFoundException;
import io.angelwing.account.service.model.Account;
import io.angelwing.account.service.repository.AccountRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Collection;
import java.util.UUID;

@Path("accounts")
public class AccountController {

    @Inject
    AccountRepository accountRepository;

    @GET
    public Collection<Account> findAll() {
        return accountRepository.findAll();
    }

    @GET
    @Path("{id}")
    public Account findById(@PathParam("id") final UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);
    }
}
