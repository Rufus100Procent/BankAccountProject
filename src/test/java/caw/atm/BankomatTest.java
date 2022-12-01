package caw.atm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BankomatTest {

    public BankomatTest() {
    }
    private Bankomat bankomat;
    private Account account;

    private IBank bankMock;

    @BeforeEach
    void setUp() {

        bankomat = new Bankomat();

    }

    //passed
    @Test
    void  checkAccountBalance_Test() {

        account = new Account("1",100);
        assertEquals(100, account.getAccountBalance(), "getAccountBalance is working");

    }

    //passed
    @Test
    void CheckAccounts_UserExistButNotAccountNumber() {
        List<User> bankUsers = new LinkedList<>();
        List<Account> user1Accounts = new ArrayList<>();

        user1Accounts.add(new Account("1", 100));
        User user1 = new User("Ali", "A4567", user1Accounts);
        bankUsers.add(user1);

        int test1 = bankomat.CheckAccount(user1, "15");

        assertEquals(-99, test1);
    }

    //passed
    @Test
    void CheckAccounts_Comparing2UsersAndAccount() {
        List<User> bankUsers = new LinkedList<>();
        List<Account> user1Accounts = new ArrayList<>();
        List<Account> user2Accounts = new ArrayList<>();

        user1Accounts.add(new Account("1", 100));
        user2Accounts.add(new Account("2", 200));

        User user1 = new User("Ali", "A", user1Accounts);
        User user2 = new User("mrAli", "b", user2Accounts);
        bankUsers.add(user1);
        bankUsers.add(user2);


        int test1 = bankomat.CheckAccount(user1, "1");
        int test2 = bankomat.CheckAccount(user2, "2");
        assertNotEquals(test1 , test2 );
    }

    //passed
    @Test
    void CheckAccounts_UserDoesNtExist_ButAccountIs() {
        List<Account> user1Accounts = new ArrayList<>();
        user1Accounts.add(new Account("1", 0));

        int test1 =  bankomat.CheckAccount(new User("", "", user1Accounts),"0" );
        assertEquals(-99, test1);
    }

    //passed
    @Test
    void depositTest_1() {
        account = new Account("1",0);
        bankomat.DepositMoney(account, 100);
        //see if depost working
        assertEquals(100, account.getAccountBalance());
    }
    //passed
    @Test
    void depositingAccountThatDoesntExist() {
        account = new Account("1",0);
        bankomat.DepositMoney(new Account("2", 0), 100);
        assertEquals(0, account.getAccountBalance());
    }

    //passed
    @Test
    void depositNegative_Test() {
        account = new Account("1",0);
        bankomat.DepositMoney(account, -100);

        assertEquals(0, account.getAccountBalance());
    }

    //passed
    @Test
    void depositMoreThenAlloed() {
        account = new Account("1",0);
        bankomat.DepositMoney(account, 1001);

        assertEquals(1001, account.getAccountBalance());
    }

    //passed
    @Test
    void DepositWithdrawTest() {
        account = new Account("1",100);

        bankomat.DepositMoney(account, 100);
        bankomat.WithdrawMoney(account, 100);

        //total + depost = 200, 200-100 = 100; Expected 100, actual 100;
        assertEquals(account.getAccountBalance(), 100);
    }

    //passed
    @Test
    void WithdrawMoney() {
        account = new Account("1",100);
        bankomat.WithdrawMoney(account, 90);

        assertEquals(10, account.getAccountBalance());
    }
    //passed
    @Test
    void withdrawMoreThenDeposit() {
        account = new Account("1",100);

        bankomat.DepositMoney(account, 100);
        bankomat.WithdrawMoney(account, 10000);

        assertEquals(200, account.getAccountBalance());

    }

    //passed
    @Test
    void withdrawWhenBlance_0() {
        account = new Account("1",0);
        bankomat.WithdrawMoney(account, 10000);

        assertEquals(0, account.getAccountBalance());
    }

    //passed
    @Test
    void withdrawNegativeBalance() {
        account = new Account("1",0);

        bankomat.WithdrawMoney(account, -10000);

        assertEquals(0, account.getAccountBalance());
    }
  //passed
    @Test
    void withdrawMoreThenAllowed() {
        account = new Account("1",2001);

        bankomat.WithdrawMoney(account, 1001);

        assertEquals(1000, account.getAccountBalance());
    }


    //passed
    @Test
    public   void Login_UserDoesntExist(){

        User user1 = new Bankomat().logIn("A", "A");
        assertNull(user1);
    }

    //passed
    @Test
    public void testLogIn_UserExist() {
        bankMock = mock(IBank.class);
        bankomat = new Bankomat(bankMock);

        List<User> bankUsers = new LinkedList<>();
        List<Account> user1Accounts = new ArrayList<>();
        user1Accounts.add(new Account("123456", 11));
        user1Accounts.add(new Account("987654", 12));
        User user1 = new User("tom", "tom17", user1Accounts);
        bankUsers.add(user1);

        List<Account> user2Accounts = new ArrayList<>();
        user2Accounts.add(new Account("944", 100));
        user2Accounts.add(new Account("945", 200));
        User user2 = new User("bob", "bob98", user2Accounts);
        bankUsers.add(user2);

        when(bankMock.getUsers()).thenReturn(bankUsers);
        User result = bankomat.logIn("bob", "bob98");

        //comparing tom and bob name/password/and if accounts match
        assertAll(() -> assertEquals(result.getName(), user2.getName()), //expected bob, actual bob
                () -> assertEquals(result.getPassword(), user2.getPassword()),//expected bob98, actual bob98
                () -> assertNotSame(result.getPassword(),user1.getPassword()), //comparing tom and bob password
                () -> assertNotSame(result.getName(),user1.getName()), //comparing tom and bob name
                () -> assertEquals(result.getAccounts().get(0).getAccountNumber(), "" + 944),
                () -> assertEquals(result.getAccounts().get(0).getAccountBalance(), 100),
                () -> assertEquals(result.getAccounts().get(1).getAccountNumber(), "" + 945),
                () -> assertEquals(result.getAccounts().get(1).getAccountBalance(), 200));
    }


}
