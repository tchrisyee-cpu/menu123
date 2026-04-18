import java.util.Scanner;
import java.util.Random;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.io.FileWriter;
import java.io.IOException;

public class AccountManager {

    private Account[] accounts = new Account[100];
    Random rand = new Random();
    private int count = 0;

    private int generateAccountNumber() {
        return 100000 + rand.nextInt(900000);
    }

    private boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        name = name.trim();
        return !name.isEmpty() && name.matches("[A-Za-z '-]+$");
    }

    private boolean isValidBirthDate(String birthDate) {
        if (birthDate == null) {
            return false;
        }
        try {
            LocalDate birth = LocalDate.parse(birthDate.trim());
            return !birth.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isEligible(String birthDate) {
        LocalDate birth = LocalDate.parse(birthDate.trim());
        LocalDate today = LocalDate.now();
        int age = Period.between(birth, today).getYears();
        return age >= 18;
    }

    public void createAccount(Scanner sc) {
        int accNum = generateAccountNumber();

        String name;
        do {
            System.out.print("Enter Name: ");
            name = sc.nextLine().trim();
            if (!isValidName(name)) {
                System.out.println("Invalid name. Please enter letters, spaces, hyphens, or apostrophes only.");
            }
        } while (!isValidName(name));

        String birthDate;
        do {
            System.out.print("Enter Birthdate (YYYY-MM-DD): ");
            birthDate = sc.nextLine().trim();
            if (!isValidBirthDate(birthDate)) {
                System.out.println("Invalid birthdate.");
            } else if (!isEligible(birthDate)) {
                System.out.println("You must be at least 18 years old to open an account.");
                return;
            }
        } while (!isValidBirthDate(birthDate));

        String pinString;
        int pin = 0;
        boolean validPin = false;

        while (!validPin) {
            System.out.print("Enter 4-digit PIN: ");
            pinString = sc.nextLine().trim();

            if (pinString.matches("\\d{4}")) {
                try {
                    pin = Integer.parseInt(pinString);
                    validPin = true;

                } catch (NumberFormatException e) {
                    System.out.println("Invalid PIN format.");
                    sc.nextLine();
                }
            } else {
                System.out.println("PIN must be exactly 4 digits.");
            }
        }

        double balance = 0;
        boolean validBalance = false;
        while (!validBalance) {
            try {
                System.out.print("Enter Initial Deposit: ");
                balance = sc.nextDouble();

                if (balance >= 0) {
                    validBalance = true;
                } else {
                    System.out.println("Balance cannot be negative.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numbers only.");
                sc.nextLine();
            }
        }

        sc.nextLine();
        boolean success = addAccount(accNum, name, balance, pin, birthDate);
        if (!success) {
            System.out.println("Failed to create account.");
        }
    }

    public Account getAccount(int accNum) {
        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountNumber() == accNum) {
                return accounts[i];
            }
        }
        return null;
    }

    public boolean addAccount(int accNum, String name, double balance, int pin, String birthDate) {
        accounts[count] = new Account(accNum, name, balance, pin, birthDate);
        count++;

        System.out.println("Account created successfully!");
        System.out.println("Your Account Number: " + accNum);
        return true;
    }

    public Account login(Scanner sc) {
        int attempts = 1;

        System.out.print("Enter Account Number: ");
        int accNum = sc.nextInt();

        do {
            try {
                System.out.print("Enter PIN: ");
                int pin = sc.nextInt();

                Account acc = getAccount(accNum);

                if (acc != null && acc.getPin() == pin) {
                    System.out.println("Login successful!");
                    return acc;
                } else {
                    System.out.println("Invalid PIN.");
                    attempts++;

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Numbers only.");
                sc.nextLine();
                attempts++;
            }
        } while (attempts <= 3);
        if (attempts > 3) {
            System.out.println("You have been locked out.");

        }
        return null;
    }

    public void saveToFile() {

        try (FileWriter writer = new FileWriter("accounts.txt")) {

            for (int i = 0; i < count; i++) {
                Account acc = accounts[i];

                writer.write(
                        acc.getAccountNumber() + "," +
                                acc.getName() + "," +
                                acc.getPin() + "," +
                                acc.getBirthDate() + "," +
                                acc.getBalance() + "\n");
            }

        } catch (IOException e) {
            System.out.println("Unable to save account data.");

        }
    }

}
