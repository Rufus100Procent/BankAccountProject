package caw.atm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Bank implements IBank {

    @Override
    public List<User> getUsers() {
        List<User> bankUsers = new LinkedList<>();
        List<Account> user1Accounts = new ArrayList<>();
        user1Accounts.add(new Account("1", 0));
        user1Accounts.add(new Account("2", 0));
        User user1 = new User("MrAli", "555", user1Accounts);

        return bankUsers;
    }

}
