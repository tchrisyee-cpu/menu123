import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Menu {

    static Scanner sc = new Scanner(System.in);

    // these will come from the other classes once we connect them
    // for now just placeholder
    static String accountName = "Chris P Pata";
    static double balance = 15000.00;

    public static void

            main(String

    [] args) {
        {
            Scanner sc = new Scanner(System.in);
            AccountManager manager = new AccountManager();

            Account loggedIn = null;

            while (loggedIn == null) {
                System.out.println("\nCreate Account");// 1
                System.out.println("Login");// 2

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        manager.createAccount(sc);
                        manager.saveToFile();
                        break;

                    case 2:
                        loggedIn = manager.login(sc);
                        break;

                }
            }

            sc.close();
        }

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = handleUserChoice();

            if (choice == 1) {
                // TODO: call viewBalance() from Account class (Jays part)
                System.out.println("View Balance - not yet connected");
            } else if (choice == 2) {
                // TODO: call deposit() from Transaction class (Mikes part)
                System.out.println("Deposit - not yet connected");
            } else if (choice == 3) {
                // TODO: call withdraw() from Transaction class (Mikes part)
                System.out.println("Withdraw - not yet connected");
            } else if (choice == 4) {
                saveReport();
            } else if (choice == 5) {
                System.out.println("Goodbye!");
                running = false;
            } else {
                System.out.println("Invalid. Please enter 1-5 only.");
            }
        }

        sc
                .

                close();
    }

    public static void displayMenu() {
        System.out.println("\n==============================");
        System.out.println("       SIMPLE BANK SYSTEM     ");
        System.out.println("==============================");
        System.out.println("1. View Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Save Report");
        System.out.println("5. Exit");
        System.out.println("==============================");
        System.out.print("Enter choice: ");
    }

    public static int handleUserChoice() {
        int choice = -1;

        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            // if user types letters just return -1 so it shows invalid
            choice = -1;
        }

        return choice;
    }

    public static void saveReport() {
        try {
            FileWriter fw = new FileWriter("BankReport.txt");
            PrintWriter pw = new PrintWriter(fw);

            pw.println("==============================");
            pw.println("         BANK REPORT          ");
            pw.println("==============================");
            pw.println("Account Name : " + accountName);
            pw.printf("Balance      : PHP %.2f%n", balance);
            pw.println("==============================");

            pw.close();

            System.out.println("Report saved! Check BankReport.txt");

        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}