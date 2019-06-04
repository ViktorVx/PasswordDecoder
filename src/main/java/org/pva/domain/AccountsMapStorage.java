package org.pva.domain;

import org.pva.domain.interfaces.IAccauntManipulations;

import java.util.*;

public class AccountsMapStorage implements IAccauntManipulations {

//    private List<Account> accounts = new ArrayList<>();
    private Map<String, Account> accounts = new HashMap<>();

    public AccountsMapStorage(String jsonString) {
        //todo delete fulfilling
        for (int i = 0; i < 5; i++) {
            Account account = new Account();
            account.setResource("google.com");
            account.setLogin(String.format("helloworld%d@gmail.com", i));
            account.setPassword(String.valueOf(i*37));
            account.setId(UUID.randomUUID());
            add(account);
        }
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    @Override
    public void add(Account account) {
        account.setId(UUID.randomUUID());
        accounts.put(account.getId().toString(), account);
    }

    @Override
    public void update(Account account) {
        if (!accounts.containsKey(account.getId())) throw new NoSuchElementException();
        accounts.put(account.getId().toString(), account);
    }

    @Override
    public void delete(Account account) {
        accounts.remove(account.getId());
    }




}
