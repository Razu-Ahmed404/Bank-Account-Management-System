public class CurrentAccount extends Account {
    private double overdraft_Limit = 1000;

    public CurrentAccount(String accountNo, String accountName, double balance, String password) {
        super(accountNo, accountName, balance, password);
    }

    @Override public String getAccountType() { 
        return "Current Account"; 
    }

    @Override public boolean withdraw(double amount) {
        if (amount > 0 && (getBalance() - amount) >= -overdraft_Limit) {
            setBalance(getBalance() - amount);
            System.out.println("Successfully withdrew " + amount + "$");
            return true;
        }
        System.out.println("Withdrawal failed! Exceeds overdraft limit.");
        return false;
    }
}
