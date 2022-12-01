package caw.atm;

import java.util.List;

public class Bankomat {

    private User loggedInUser;
    private IBank bank;

    public Bankomat() {
        bank = new Bank();
    }

    public Bankomat(IBank bank) {
        this.bank = bank;
    }

    public void Run() {
        ScannerClass scannerClass = new ScannerClass();

        String userName = scannerClass.scannerGetString("Enter Username: ");
        String password = scannerClass.scannerGetString("Password: ");

        Bankomat bankAccount = new Bankomat();
        bankAccount.printMenu();

        boolean again = false;
        Account account = new Account("1", 0);

        while (again == false) {
            try {
                int choises;
                choises = scannerClass.scannerGetInt("");
                switch (choises) {
                    case 1:
                        int amountDeposite = scannerClass.scannerGetInt("Write Amount to deposit");
                        DepositMoney(account, amountDeposite);
                        bankAccount.printMenu();
                        break;
                    case 2:
                        int amountWithdraw = scannerClass.scannerGetInt("Write Amount to withdraw");
                        WithdrawMoney(account, amountWithdraw);
                        bankAccount.printMenu();
                        break;
                    case 3:
                        System.out.println(account.getAccountBalance());
                        bankAccount.printMenu();
                        break;
                    case 4:
                        again = true;
                    default:
                        System.out.println("enter Number from 1-4");
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid input!!, TRY AGAIN");
            }
        }

    }
    private void printMenu() {
        System.out.println("*****************");
        System.out.println("you have 3 options to chose from");
        System.out.println("1. Deposit Money");
        System.out.println("2. Withdraw Money");
        System.out.println("3. view Account");
        System.out.println("4. exit");
    }


    //Tested
    User logIn(String userName, String password) {
        List<User> users = bank.getUsers();
        for (User u : users) {
            if (u.getName().equals(userName) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }


    //Tested
    public int CheckAccount(User currentUser, String accountNumber) {
        for (Account cUA : currentUser.getAccounts()) {
            if (cUA.getAccountNumber().equals(accountNumber)) {
                return cUA.getAccountBalance();
            }
        }
        return -99;
    }


    //Tested
    public Account DepositMoney(Account account, Integer money) {
        money = Math.max(0,money);

        Integer currentAmount = account.getAccountBalance();
        currentAmount += money;
        account.setAccountBalance(currentAmount);
        System.out.println("New Balans on Accountnumber: " + account.getAccountNumber() + " total is " + account.getAccountBalance());
        if  (money<0) {
            System.out.println("amount is negative");

        } else if (money>1000) {
            System.out.println("you have reached your limit");

        }
        return account;
    }

    //Tested
    public Account WithdrawMoney(Account account, Integer money) {
        money = Math.max(0,money);

        Integer currentAmount = account.getAccountBalance();
        if (currentAmount < money) {
            System.out.println("Now enough money in account. Only " + currentAmount);
        } else if (currentAmount > money) {
            currentAmount -= money;
            account.setAccountBalance(currentAmount);
            System.out.println("New Balans on Accountnumber: " + account.getAccountNumber() + " has " + account.getAccountBalance() +" left");

            if (money < 0) {
                System.out.println("amout negative");
            } else if (money> 1000) {
                System.out.println("you have reached your limit");
            }


        }
        return account;
    }




    //Tested
    private Account getCorrectAccount(String accountnumber){
        for (Account currentAccount : loggedInUser.getAccounts()) {
            if (accountnumber.equals(currentAccount.getAccountNumber())){
                return currentAccount;
            }
        }

        return null;

    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
