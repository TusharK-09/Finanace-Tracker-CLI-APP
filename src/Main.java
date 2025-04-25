import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FinanceTracker financeTracker = new FinanceTracker();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        MongoUsers mongoUsers = new MongoUsers();


        String inputUser, inputPassword;
        boolean isLoggedIn = false;

        System.out.println("====== Welcome to KhaataBook ======");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choose an option: ");
        int authChoice = sc.nextInt();
        sc.nextLine();

        switch (authChoice) {
            case 1:
                System.out.print("Choose a username: ");
                inputUser = sc.nextLine();
                System.out.print("Choose a password: ");
                inputPassword = sc.nextLine();

                if (mongoUsers.isUserExists(inputUser)) {
                    System.out.println("Username already exists!");
                    return;
                }

                mongoUsers.insertUser(inputUser, inputPassword);
                System.out.println("Registration successful ✅.");
                return;

            case 2:
                System.out.print("Enter username: ");
                inputUser = sc.nextLine();
                System.out.print("Enter password: ");
                inputPassword = sc.nextLine();

                isLoggedIn = mongoUsers.checkLogin(inputUser, inputPassword);
                if (!isLoggedIn) {
                    System.out.println("Invalid credentials. Exiting...");
                    return;
                } else {
                    System.out.println("Login successful ✅");
                }
                break;

            default:
                System.out.println("Invalid choice. Exiting...");
                return;
        }

        while (true) {
            System.out.println("\n============ Welcome to KhaataBook ============");
            System.out.println("1. Set Budget");
            System.out.println("2. Add Expense");
            System.out.println("3. Update Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Show All Expenses");
            System.out.println("6. Show total expense");
            System.out.println("7. Show remaining budget");
            System.out.println("8. Exit App");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter your budget: Rs.");
                    double budget = sc.nextDouble();
                    financeTracker.setBudget(budget);
                    System.out.println("Budget set to Rs." + budget);
                    break;

                case 2:
                    System.out.println("Enter Amount: Rs.");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    System.out.println("Enter category: ");
                    String category = sc.nextLine();

                    System.out.println("Enter description of your spend: ");
                    String description = sc.nextLine();

                    System.out.print("Enter date (yyyy-MM-dd): ");
                    String dateStr = sc.nextLine();
                    LocalDate date = LocalDate.parse(dateStr, formatter);

                    financeTracker.addExpense(amount, category, description, date);

                    MongoTest mongoConnectionTest = new MongoTest();
                    mongoConnectionTest.insertExpense(amount, category, description, date.toString());

                    System.out.println("Expense added.");
                    break;

                case 3:
                    if (financeTracker.getAllExpenses().isEmpty()) {
                        System.out.println("No expenses to update!");
                        break;
                    }
                    System.out.println("Feature under development...");
                    break;

                case 4:
                    System.out.println("\n======== Choose to delete =======");
                    int i = 1;
                    for (Expense e : financeTracker.getAllExpenses()) {
                        System.out.println(i + ".  " + e);
                        i++;
                    }

                    System.out.println("Enter number to delete: ");
                    int deleteIndex = sc.nextInt() - 1;
                    sc.nextLine();

                    if (deleteIndex >= 0 && deleteIndex < financeTracker.getAllExpenses().size()) {
                        financeTracker.getAllExpenses().remove(deleteIndex);
                        System.out.println("Deleted successfully!");
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;

                case 5:
                    System.out.println("\n======== All Expenses =======");
                    if (financeTracker.getAllExpenses().isEmpty()) {
                        System.out.println("No expenses to show!");
                    } else {
                        for (Expense e : financeTracker.getAllExpenses()) {
                            System.out.println(e);
                        }
                    }
                    break;

                case 6:
                    System.out.println("Total expenses: Rs." + financeTracker.getTotalExpenses());
                    break;

                case 7:
                    if (financeTracker.getRemainingBudget() < 0) {
                        System.out.println("Warning! You are over the budget!");
                    }
                    System.out.println("Remaining Budget: Rs." + financeTracker.getRemainingBudget());
                    break;

                case 8:
                    System.out.println("Exiting the App....have a good day!!!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Option...Try again!");
            }
        }
    }
}
