import java.util.*;

public class Main {
    public static int getSafeIntInput(Scanner input) {
    while (true) {
        try {
            return input.nextInt();
        } catch (Exception e) {
            System.out.print("Invalid input! Please enter a number: ");
            input.nextLine();
            }
        }
    }
    public static double getSafeDoubleInput(Scanner input) {
    while (true) {
        try {
            return input.nextDouble();
        } catch (Exception e) {
            System.out.print("Invalid amount! Please enter a number: ");
            input.nextLine();
            }
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Bank bank = new Bank();
        List<Transaction> transactions = new ArrayList<>();

        SavingsAccount acc1 = new SavingsAccount("R1010", "Rahim", 1000, "rahim123");
        CurrentAccount acc2 = new CurrentAccount("K1999", "Karim", 2000, "karim123");

        bank.registerAccount(acc1);
        bank.registerAccount(acc2);

        String adminUser = "admin";
        String adminPass = "1234";

        int Choice;
        do {
            System.out.println("\n===== LOGIN SYSTEM =====");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            
            Choice = getSafeIntInput(input);

            switch (Choice) {
                case 1:
                    // --- ADMIN LOGIN ---
                    System.out.print("Enter Admin Username: ");
                    String user = input.next();
                    System.out.print("Enter Admin Password: ");
                    String password = input.next();

                    if (user.equals(adminUser) && password.equals(adminPass)) {
                        int adminChoice;
                        do {
                            System.out.println("\n===== ADMIN PANEL =====");
                            System.out.println("1. Register Account");
                            System.out.println("2. Display All Accounts");
                            System.out.println("3. Display All Transactions");
                            System.out.println("4. Logout");
                            System.out.print("Enter choice: ");
                            
                            adminChoice = getSafeIntInput(input);

                            switch (adminChoice) {
                                case 1:
                                    System.out.println("1. Savings Account\n2. Current Account");
                                    System.out.print("Enter type: ");
                                    int type = getSafeIntInput(input);

                                    System.out.print("Enter Acc No: ");
                                    String accNo = input.next();
                                    System.out.print("Enter Name: ");
                                    input.nextLine();
                                    String name = input.nextLine();
                                    System.out.print("Enter Balance: ");
                                    double bal = getSafeDoubleInput(input);
                                    System.out.print("Set Password: ");
                                    String pass = input.next();

                                    Account newAcc = null;
                                    switch (type) {
                                        case 1:
                                            newAcc = new SavingsAccount(accNo, name, bal, pass);
                                            break;
                                        case 2:
                                            newAcc = new CurrentAccount(accNo, name, bal, pass);
                                            break;
                                        default:
                                            System.out.println("Invalid Account Type!");
                                            break;
                                    }

                                    if (newAcc != null) {
                                        bank.registerAccount(newAcc);
                                    }
                                    break;

                                case 2:
                                    bank.displayAllAccounts();
                                    break;

                                case 3:
                                    System.out.println("\n--- All Transactions ---");
                                    if (transactions.isEmpty()) {
                                        System.out.println("Transactions is Empty.");
                                    } else {
                                        for (Transaction t : transactions) {
                                            t.displayInfo();
                                        }
                                    }
                                    break;

                                case 4:
                                    System.out.println("Logging out from Admin Panel...");
                                    break;

                                default:
                                    System.out.println("Invalid Admin choice!");
                                    break;
                            }
                        } while (adminChoice != 4);
                    } else {
                        System.out.println("Invalid Admin information!");
                    }
                    break;

                case 2:
                    // --- CUSTOMER LOGIN ---
                    System.out.print("Enter Acc No: ");
                    String userAccNo = input.next();
                    System.out.print("Enter Password: ");
                    String userPass = input.next();

                    Account userAcc = bank.findAccount(userAccNo);

                    if (userAcc != null && userAcc.validatePassword(userPass)) {
                        int userChoice;
                        do {
                            System.out.println("\n===== CUSTOMER PANEL (" + userAcc.getAccountName() + ") =====");
                            System.out.println("1. View Account Details");
                            System.out.println("2. Deposit Amount");
                            System.out.println("3. Withdraw Amount");
                            System.out.println("4. Transfer Amount");
                            System.out.println("5. Logout");
                            System.out.print("Enter choice: ");
                            userChoice = getSafeIntInput(input);

                            switch (userChoice) {
                                case 1:
                                    userAcc.displayAccountStatus();
                                    break;

                                case 2:
                                    System.out.print("Enter Amount: ");
                                    double depAmt = getSafeDoubleInput(input);
                                    userAcc.deposit(depAmt);

                                    Transaction transaction_Deposit = new Transaction(userAcc.getAccountNumber(), "Deposit", depAmt);
                                    transactions.add(transaction_Deposit);
                                    break;

                                case 3:
                                    System.out.print("Enter Amount: ");
                                    double withdraw_Amount = getSafeDoubleInput(input);
                                    if (userAcc.withdraw(withdraw_Amount)) {
                                        Transaction transaction_Withdraw = new Transaction(userAcc.getAccountNumber(), "Withdraw", withdraw_Amount);
                                        transactions.add(transaction_Withdraw);
                                    }
                                    break;

                                case 4:
                                    System.out.print("Receiver Acc No: ");
                                    String reciverNo = input.next();
                                    System.out.print("Amount: ");
                                    double transferAmount = getSafeDoubleInput(input);

                                    Account reciver_Account = bank.findAccount(reciverNo);

                                    if (reciver_Account != null && userAcc.withdraw(transferAmount)) {
                                        reciver_Account.deposit(transferAmount);

                                        Transaction transaction_Out = new Transaction(userAcc.getAccountNumber(), "Transfer Out", transferAmount);
                                        Transaction transaction_In = new Transaction(reciverNo, "Transfer In", transferAmount);

                                        transactions.add(transaction_Out);
                                        transactions.add(transaction_In);
                                        System.out.println("Transfer successful!");
                                    } else {
                                        System.out.println("Transfer failed!");
                                    }
                                    break;

                                case 5:
                                    System.out.println("Logging out from Customer Panel...");
                                    break;

                                default:
                                    System.out.println("Invalid Customer choice!");
                                    break;
                            }
                        } while (userChoice != 5);
                    } else {
                        System.out.println("Invalid Account Number or Password!");
                    }
                    break;

                case 3:
                    System.out.println("Exiting System... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (Choice != 3);
        input.close();
    }
}