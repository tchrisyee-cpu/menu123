import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Menu {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
    
        AccountManager manager = new AccountManager();
        TransactionManager tm = new TransactionManager(); 
        Account loggedIn = null;

            while (loggedIn == null) {
                System.out.println("\nCreate Account");// 1
                System.out.println("Login");// 2
                System.out.print("Enter choice: ");

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

                    default:
                        System.out.println("Invalid choice.");
                }
        }

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = handleUserChoice();

            switch (choice){
                case 1: //view balance
                    System.out.printf("Current Balance: PHP %,.2f%n", loggedIn.getBalance());
                    break;
                case 2: //deposit
                    tm.deposit(loggedIn, sc);
                    break;
                case 3: //withdraw
                    tm.withdraw(loggedIn, sc);
                    break;
                case 4: //view transactions
                    tm.viewTransactions;
                    break;
                case 5: //save report
                    saveReport(loggedIn, tm);
                    break;
                case 6: //exit
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid. Please enter 1-6 only.");
            }
        }

        sc.close();
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
        int choice;

        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            // if user types letters just return -1 so it shows invalid
            choice = -1;
        }
        return choice;
    }

    //save report
    public static void saveReport(Account acc, TransactionManager tm) {
        try {
            FileWriter fw = new FileWriter("BankReport.txt");
            PrintWriter pw = new PrintWriter(fw);

            pw.println("==============================");
            pw.println("         BANK REPORT          ");
            pw.println("==============================");

            //account details
            pw.println("Account Name : " + acc.getAccountNumber());
            pw.println("Name         : " + acc.getName());
            pw.printf("Balance      : PHP %,.2f%n", acc.getBalance());

            //transaction history
            pw.println("\nTRANSACTION HISTORY");
            pw.println("------------------------------");

            Transaction[] transactions = tm.getTransactions();
            int count = tm.getCount();

            if (count == 0){
                pw.println("No transactions available.");
            } else{
                for (int i = 0; i < count; i++){
                    pw.println(transactions[i]):
                }
            }

            pw.println("\n==============================");
            pw.println("       END OF REPORT          ");
            pw.println("==============================");

            pw.close();

            System.out.println("Report saved! Check BankReport.txt");

        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
