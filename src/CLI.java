import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CLI {
    private static final String DATA_FILE = "financial_data.ser";
    private User user;
    private Scanner scanner;
    private DecimalFormat df;

    public CLI() {
        this.user = new User();
        this.scanner = new Scanner(System.in);
        this.df = new DecimalFormat("#.00");
        this.user = User.load(DATA_FILE);
    }
    


    public void start() {
        System.out.println("Welcome to the Financial Tracker!");
        mainMenu();
    }

    private void mainMenu() {
        while (true) {
            System.out.println("\nMoney Buddy Main Menu:");
            System.out.println("==========================");
            System.out.println("1. Manage Income");
            System.out.println("2. Manage Expenses");
            System.out.println("3. Set Savings Goal");
            System.out.println("4. View Financial Summary");
            System.out.println("5. Export Data");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    manageIncome();
                    break;
                case 2:
                    manageExpenses();
                    break;
                case 3:
                    setSavingsGoal();
                    break;
                case 4:
                    viewFinancialSummary();
                    break;
                case 5:
                    exportData();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageIncome() {
        try {
            System.out.println("Enter the income source:");
            String source = scanner.nextLine();
            System.out.println("Enter the income amount:");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Enter the income date (yyyy-mm-dd):");
            String dateString = scanner.nextLine();
            LocalDate date = LocalDate.parse(dateString);

            IncomeItem incomeItem = new IncomeItem(source, amount, date);
            user.addIncome(incomeItem);
            user.updateSavingsGoalBasedOnPercentage(YearMonth.from(date));

            User.save(user, DATA_FILE);
            System.out.println("Income added successfully.");
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println("Invalid input. Please check the format and try again.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); 
        }
    }

    private void manageExpenses() {
        try {
            System.out.println("Enter the expense category:");
            String category = scanner.nextLine();
            System.out.println("Enter the expense description:");
            String description = scanner.nextLine();
            System.out.println("Enter the expense amount:");
            double amount = scanner.nextDouble();
            scanner.nextLine(); 
            System.out.println("Enter the expense date (yyyy-mm-dd):");
            String dateString = scanner.nextLine();
            LocalDate date = LocalDate.parse(dateString);
    
            ExpenseItem expenseItem = new ExpenseItem(category, description, amount, date);
            user.addExpense(expenseItem);
            User.save(user, DATA_FILE);
            System.out.println("Expense added successfully.");
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println("Invalid input. Please check the format and try again.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); 
        }
    }
    

    private void setSavingsGoal() {
        while (true) {
            System.out.println("Manage Savings Goal:");
            System.out.println("1. Set savings goal (amount)");
            System.out.println("2. Set savings goal (percentage of total income)");
            System.out.println("3. View current savings goal");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice (1-4): ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
    
                switch (choice) {
                    case 1:
                        setSavingsGoalAmount();
                        break;
                    case 2:
                        setSavingsGoalPercentage();
                        break;
                    case 3:
                        viewSavingsGoal();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); 
            }
        }
    }
    
    
    
    private void viewFinancialSummary() {
        try {
            System.out.println("Enter the year and month for the financial summary (yyyy-mm):");
            String yearMonthString = scanner.nextLine();
            YearMonth yearMonth = YearMonth.parse(yearMonthString);
    
            double totalIncome = user.calculateTotalIncome(yearMonth);
            double totalExpenses = user.calculateTotalExpenses(yearMonth);
            double totalSavings = user.calculateTotalSavings(yearMonth);
            SavingsGoal savingsGoal = user.getSavingsGoal(yearMonth);
    
            System.out.println("\nFinancial Summary for " + yearMonth.toString() + ":");
            System.out.println("Income: $" + df.format(totalIncome));
            System.out.println("Expenses: $" + df.format(totalExpenses));
            System.out.println("Savings: $" + df.format(totalSavings));
    
            if (savingsGoal != null) {
                if (savingsGoal.isPercentageBased()) {
                    System.out.println("Savings Goal: " + df.format(savingsGoal.getPercentage()) + "% of total income ($" + df.format(savingsGoal.getGoal()) + ")");
                } else {
                    System.out.println("Savings Goal: $" + df.format(savingsGoal.getGoal()));
                }
    
                if (totalSavings < savingsGoal.getGoal()) {
                    System.out.println("\nWARNING: You did not meet your savings goal this month.");
                } else {
                    System.out.println("\nCongratulations! You met your savings goal this month.");
                }
            } else {
                System.out.println("No savings goal set for this month.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid input. Please use the format yyyy-mm.");
        }
    }
    
    
    private void exportData() {
        System.out.println("Enter the file name for the exported CSV file (e.g. 'report.csv'):");
        String fileName = scanner.next();
        if (!fileName.endsWith(".csv")) {
            System.out.println("Error: File name should end with .csv");
            return;
        }
        ReportGenerator reportGenerator = new ReportGenerator(user);
        try {
            boolean exported = reportGenerator.generateCSVReport(fileName);
            if (exported) {
                System.out.println("Data has been exported successfully.");
            } else {
                System.out.println("An error occurred while exporting the data.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not create the specified file.");
        }
        User.save(user, DATA_FILE);
    }
    

    private void setSavingsGoalPercentage() {
        try {
            System.out.println("Enter the year and month for the savings goal (yyyy-mm):");
            String yearMonthString = scanner.nextLine();
            YearMonth yearMonth = YearMonth.parse(yearMonthString);
    
            System.out.print("Enter the percentage of total income you want to set as your savings goal: ");
            double percentage = scanner.nextDouble();
            scanner.nextLine(); 
    
            double totalIncome = user.calculateTotalIncome(yearMonth);
            double newGoalAmount = totalIncome * percentage / 100;
            user.updateSavingsGoal(newGoalAmount, true, percentage, yearMonth);
            System.out.println("Savings goal set to " + df.format(newGoalAmount) + " (" + percentage + "% of total income) for " + yearMonth.toString());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid input. Please use the format yyyy-mm.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); 
        }
    }
    


    private void setSavingsGoalAmount() {
        try {
            System.out.print("Enter the year and month for the savings goal (yyyy-mm): ");
            String yearMonthString = scanner.nextLine();
            YearMonth yearMonth = YearMonth.parse(yearMonthString);
    
            System.out.print("Enter the new savings goal amount: ");
            double newGoal = scanner.nextDouble();
            scanner.nextLine();
            user.updateSavingsGoal(newGoal, false, 0, yearMonth);
            System.out.println("Savings goal set to $" + df.format(newGoal) + " for " + yearMonth.toString());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid input. Please use the format yyyy-mm.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); 
        }
    }

    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.start();
    }

    private void viewSavingsGoal() {
        try {
            System.out.println("Enter the Year and Month for the savings goal (YYYY-MM):");
            String yearMonthInput = scanner.nextLine().trim();
            YearMonth yearMonth = YearMonth.parse(yearMonthInput);
    
        try {
            yearMonth = YearMonth.parse(yearMonthInput);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid input. Please use the format YYYY-MM.");
            return;
        }
    
        SavingsGoal savingsGoal = user.getSavingsGoal(yearMonth);
        if (savingsGoal == null) {
            System.out.println("No savings goal set for " + yearMonth.toString() + ".");
            return;
        }
    
        double savingsGoalAmount = savingsGoal.getGoal();
        double totalIncome = user.calculateTotalIncome(yearMonth);
        double savingsGoalPercentage = totalIncome != 0 ? (savingsGoalAmount / totalIncome) * 100 : 0;
    
        System.out.println("Savings goal for " + yearMonth.toString() + ":");
        System.out.println("Amount: " + df.format(savingsGoalAmount));
        System.out.println("Percentage of total income: " + df.format(savingsGoalPercentage) + "%");
    } catch (DateTimeParseException e) {
        System.out.println("Invalid input. Please use the format YYYY-MM.");
    }
}
        
}
