package caw.atm;

import java.util.List;

public class User {
    private String name;
    private String password;
    private List<Account> accounts;

    public User(String name, String password, List<Account> accounts) {
        this.name = name;
        this.password = password;
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}

