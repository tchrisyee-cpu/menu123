
public class Account {
    private int accountNumber;
    private double balance;
    private int pin;
    private String name;
    private String birthDate; // YYYY-MM-DD

    public Account(int accountNumber, String name, double balance, int pin, String birthDate) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        this.pin = pin;
        this.birthDate = birthDate;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getPin() {
        return pin;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void displayInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);
    }
}
