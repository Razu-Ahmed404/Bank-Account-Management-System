import java.util.*;

public class Bank {
    private List<Account> accounts = new ArrayList<>();

    public boolean registerAccount(Account acc) {
        if (findAccount(acc.getAccountNumber()) != null) {
            System.out.println("Error: Account is " + acc.getAccountNumber() + " already exists.");
            return false;
        }
        accounts.add(acc);
        System.out.println("Account registered successfully for " + acc.getAccountName());
        return true;
    }

    public Account findAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    public void displayAllAccounts() {
        System.out.println("\n--- All Customer Accounts ---");
        if (accounts.isEmpty()) {
            System.out.println("No accounts registered in the system.");
            return;
        }
        for (Account acc : accounts) {
            acc.displayAccountStatus();
        }
    }
}