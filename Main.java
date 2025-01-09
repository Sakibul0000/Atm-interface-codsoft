import java.util.Scanner;

class BankAccount {
    private double balance;
    private static final double WITHDRAWAL_LIMIT = 1000.0;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance && amount <= WITHDRAWAL_LIMIT) {
                balance -= amount;
                return true;
            } else if (amount > balance) {
                System.out.println("Insufficient balance. Your current balance is " + balance);
            } else {
                System.out.println("Withdrawal amount exceeds the limit of " + WITHDRAWAL_LIMIT);
            }
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
        return false;
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number from 1 to 4.");
                scanner.next();
            }
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Exiting. Thank you for using the ATM.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input! Please enter a valid amount to withdraw.");
            scanner.next();
        }
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Withdrawal failed. Please check your balance or try a smaller amount.");
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input! Please enter a valid amount to deposit.");
            scanner.next();
        }
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    private void checkBalance() {
        System.out.println("Current balance: " + account.getBalance());
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0);
        ATM atm = new ATM(account);
        atm.showMenu();
    }
}
