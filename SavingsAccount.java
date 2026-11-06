public class SavingsAccount extends Account {
    private double min_Balance = 500;

    public SavingsAccount(String accountNo, String accountName, double balance, String password) {
        super(accountNo, accountName, balance, password);
    }

    @Override public String getAccountType() { 
        return "Savings Account"; 
    }

    @Override public boolean withdraw(double amount) {
        if (amount > 0 && (getBalance() - amount) >= min_Balance) {
            setBalance(getBalance() - amount);
            System.out.println("Successfully withdraw " + amount + "$");
            return true;
        }
        System.out.println("Withdrawal failed! Minimum Balance must be " + min_Balance + "$");
        return false;
    }
}