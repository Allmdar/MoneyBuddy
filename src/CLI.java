import java.io.FileNotFoundException; //Handles File related exceptions
import java.text.DecimalFormat; // Formats decimal numbers (e.g. Currency Values)
import java.time.LocalDate;  // Represents date (year,month, and day) without time information
import java.time.YearMonth; // Represents Year and Month without day information
import java.time.format.DateTimeParseException; // handles exceptions related to date and time parsing
import java.util.InputMismatchException; // Handles input related exceptions when (when input doesn't match expected data type)
import java.util.Scanner; //Reads User input from command line

public class CLI { // CLI Class decleration

    //instance variables -> variables declared within a class but outside a constructor
    //Intance variables are associated with an instance (object) of the class, and are initialized when the object is created.

    private static final String DATA_FILE = "financial_data.ser"; //stores filename for saving/loading user data. static final means it is a constant and cannot be changed once intialized
    private User user; //represents user and stores their financial data. Each instance of CLI class will have its own user object
    private Scanner scanner; //Reads user input from the command line.
    private DecimalFormat df; //Formats decimal numbers (e.g. Currency Values)

    //constructor
    //initializes the user, scanner, and df instance variables
    public CLI() {
        this.user = new User(); //Creates new User Object
        this.scanner = new Scanner(System.in);
        this.df = new DecimalFormat("#.00"); //#.00 -> decimal numbers will be formatted with two decimal places
        this.user = User.load(DATA_FILE); //loads user from the 'DATA_FILE' string
    }
    
    //start() method prints a welcome message and calls the mainMenu() method to display the options and start MoneyBuddy
    public void start() {
        System.out.println("Welcome to the Financial Tracker!");
        mainMenu();
    }


    //Displays main menu options and continiously loops until user chooses to exit the application. 
    //Validates input and calls method based on that
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

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

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

    //A 'private void' method can only be accessed withtin the class it is declared and does not return any value.
    
    private void manageIncome() {
        try {
            //asks the user for income related information
            System.out.println("Enter the income source:");
            String source = scanner.nextLine();
            System.out.println("Enter the income amount:");
            double amount = scanner.nextDouble(); //consumes newline character
            scanner.nextLine();
            System.out.println("Enter the income date (yyyy-mm-dd):");
            String dateString = scanner.nextLine();
            LocalDate date = LocalDate.parse(dateString); //used to convert the date string entered into a 'LocalDate' object

            IncomeItem incomeItem = new IncomeItem(source, amount, date); // IncomeItem object is created
            user.addIncome(incomeItem); // The object is then added to the user's financial data using the addIncome() method
            user.updateSavingsGoalBasedOnPercentage(YearMonth.from(date)); //The savings goal is updated based on the new income
            //The 'YearMonth.from(date)' converts the `LocalDate` object `date` to a `YearMonth` object, which is then passed as argument to the method of the `user` object

            User.save(user, DATA_FILE); //The users financial data is saved
            System.out.println("Income added successfully.");
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println("Invalid input. Please check the format and try again.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); //used to consume the invalid input so that the scanner can continue to read the next input correctly
        }
    }

    // 
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
    
            ExpenseItem expenseItem = new ExpenseItem(category, description, amount, date); //ann ExpenseItem object (expenseItem) is created with the user-provided inputs (category, description, amount, and date).
            user.addExpense(expenseItem); //The expenseItem is added to the 'User' object's list of expenses
            User.save(user, DATA_FILE); //The data is saved to the users file
            System.out.println("Expense added successfully.");
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println("Invalid input. Please check the format and try again.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); 
        }
    }
    

    //Displays options to manage the savings goal and calls the appropriate method
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
    
    
    //private method that displays the user's financial summary for a specific month, including total income, expenses, savings, and their savings goal status
    private void viewFinancialSummary() {
        try {
            System.out.println("Enter the year and month for the financial summary (yyyy-mm):");
            String yearMonthString = scanner.nextLine();
            YearMonth yearMonth = YearMonth.parse(yearMonthString);
            

            //The method calculates the total income, total expenses, and total savings for the specified month using the User object's methods 
            double totalIncome = user.calculateTotalIncome(yearMonth);
            double totalExpenses = user.calculateTotalExpenses(yearMonth);
            double totalSavings = user.calculateTotalSavings(yearMonth);
            SavingsGoal savingsGoal = user.getSavingsGoal(yearMonth);
    
            System.out.println("\nFinancial Summary for " + yearMonth.toString() + ":");
            System.out.println("Income: $" + df.format(totalIncome));
            System.out.println("Expenses: $" + df.format(totalExpenses));
            System.out.println("Savings: $" + df.format(totalSavings));
            

            //If there is a savings goal for the entered month, the method checks if the goal is percentage-based or fixed amount  and displays the goal according to that
            if (savingsGoal != null) {
                if (savingsGoal.isPercentageBased()) {
                    System.out.println("Savings Goal: " + df.format(savingsGoal.getPercentage()) + "% of total income ($" + df.format(savingsGoal.getGoal()) + ")");
                } else {
                    System.out.println("Savings Goal: $" + df.format(savingsGoal.getGoal()));
                }

                // notification to user whether goal is met is implemented here
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
    
    
    //
    private void exportData() {
        System.out.println("Enter the file name for the exported CSV file (e.g. 'report.csv'):");
        String fileName = scanner.next();
        if (!fileName.endsWith(".csv")) {
            System.out.println("Error: File name should end with .csv");
            return;
        }
        // A ReportGenerator object is created by passing the 'User' object to its constructor
        ReportGenerator reportGenerator = new ReportGenerator(user);
        try {
            boolean exported = reportGenerator.generateCSVReport(fileName); //returns true if report generation is successful
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
            
            //The method calculates the total income for the specified month using the User objects calculateTotalIncome() method
            double totalIncome = user.calculateTotalIncome(yearMonth);
            double newGoalAmount = totalIncome * percentage / 100; 

            // updates the user's savings goal using updateSavingsGoal() method and passing the new goal amount, a true boolean value to indicate that the goal is percentage-based, the entered percentage and the yearMonth object

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
            user.updateSavingsGoal(newGoal, false, 0, yearMonth); //percentageBased is set to false
            System.out.println("Savings goal set to $" + df.format(newGoal) + " for " + yearMonth.toString());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid input. Please use the format yyyy-mm.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); 
        }
    }

    private void viewSavingsGoal() {
        try {
            System.out.println("Enter the Year and Month for the savings goal (YYYY-MM):");
            //Reads the input as a string and trims any leading/trailing whitespace
            String yearMonthInput = scanner.nextLine().trim(); 
            YearMonth yearMonth = YearMonth.parse(yearMonthInput); //parses the users input into a `YearMonth` object using the `YearMonth.parse()` method
    
        try {
            yearMonth = YearMonth.parse(yearMonthInput);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid input. Please use the format YYYY-MM.");
            return;
        }
        //user.getSavingsGoal(yearMonth) method is called to retrieve the `SavingsGoal` object for the specified year and month
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

//main() method which is the entry point for the program
// A main() method should be public and static with a return type of void.
// It accepts a single argument, an array of String objects 
// The `args` parameter is used to pass command line arguments to the program
public static void main(String[] args) {
    CLI cli = new CLI(); // creates a new instance of the 'CLI' Class
    cli.start(); // starts the Command Line Interface of the financial application
}
}
