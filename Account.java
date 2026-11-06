public abstract class Account {
    private String accountNo, accountName, password;
    private double balance;

    public Account(String accountNumber, String accountName, double balance, String password) {
        this.accountNo = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
        this.password = password;
    }

    public String getAccountNumber() { return accountNo; }
    public String getAccountName() { return accountName; }
    public double getBalance() { return balance; }
    protected void setBalance(double balance) { this.balance = balance; }

    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public abstract String getAccountType();
    public abstract boolean withdraw(double amount);

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited " + amount + "$");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void displayAccountStatus() {
        System.out.println(getAccountType() + "\nAccount Number: " + accountNo + "\nName: " + accountName + "\nBalance: " + balance + "$");
        System.out.println("-----------");
    }
}