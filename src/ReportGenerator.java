//Class is responsible for generating a CSV (Comma Separated Values) report of the financial datar for a specific user


import java.io.FileWriter; //allows the class to write text content to a file in an easy way
import java.io.IOException; //handles exception handling
import java.io.PrintWriter; //provides formatting text options
import java.text.DecimalFormat; // formats numbers as strings with a specific pattern
import java.io.File; //Provides methods for working with the file system, such as creating, deleting, and renaming files or directories
import java.time.YearMonth; // Represents Year and Month without day information
import java.util.Map; //Imports the Map interface. A Map is a data structure that stores key-value pairs.
//Map is used to store the financial data organized by the YearMonth keys
import java.util.List; //List is datastructure that stores an ordered collection of elemets
//List is used to store IncomeItem and ExpenseItem obkets for each YearMonth
import java.io.FileNotFoundException; //Used to throw an exception when an attempt to open a file denoted by a specified pathname has failed



public class ReportGenerator {

    //Instance Variables
    private User user;
    private DecimalFormat df;

    //Constructor which initializes the instance variables with a user object passed as an argument
    public ReportGenerator(User user) {
        this.user = user;
        this.df = new DecimalFormat("#.00"); //creates a new DecimalFormat object with the specified format "#.00".
    }

    // method takes fileName as input and generates a CSV report based on the user's financial data
    public boolean generateCSVReport(String fileName) throws FileNotFoundException {
        try {
            File file = new File(fileName); //Created a new file object representing the CSV file to be created
            FileWriter fileWriter = new FileWriter(file); //Creates a new FileWriter object allowing to write text on the file
            PrintWriter pw = new PrintWriter(fileWriter); //Creates PrintWriter object that wraps around the FileWriter, providing high-level methods for writing formatted text to the file.
            
            //Headers and financial data to Printwriter
            pw.println("Income Items:");
            pw.println("YearMonth,Description,Amount");
            //The following for loop iterates through the user's income items for each YearMonth, writing them to the CSV file in the format 
            for (Map.Entry<YearMonth, List<IncomeItem>> entry : user.getMonthlyIncomeItems().entrySet()) {
                YearMonth yearMonth = entry.getKey();
                List<IncomeItem> incomeItems = entry.getValue();
                for (IncomeItem incomeItem : incomeItems) {
                    pw.println(yearMonth.toString() + "," + incomeItem.getSource() + "," + df.format(incomeItem.getAmount()));
                }
            }
    
            pw.println("\nExpenses:");
            pw.println("YearMonth,Category,Description,Amount");
            //This for loop iterates through the user's expense items for each YearMonth, writing them to the CSV file
            for (Map.Entry<YearMonth, List<ExpenseItem>> entry : user.getMonthlyExpenseItems().entrySet()) {
                YearMonth yearMonth = entry.getKey();
                List<ExpenseItem> expenseItems = entry.getValue();
                for (ExpenseItem expenseItem : expenseItems) {
                    pw.println(yearMonth.toString() + "," + expenseItem.getCategory() + "," + expenseItem.getDescription() + "," + df.format(expenseItem.getAmount()));
                }
            }
    
            pw.println("\nSavings Goals:");
            pw.println("YearMonth,Amount,Percentage,MoneySaved,GoalMet");

            //The last for loop iterates through the user's savings goals for each YearMonth, calculates the money saved and whether the goal was met, and writes this information to the CSV file in the format
            for (Map.Entry<YearMonth, SavingsGoal> entry : user.getSavingsGoals().entrySet()) {
                YearMonth yearMonth = entry.getKey();
                SavingsGoal savingsGoal = entry.getValue();
                double totalIncome = user.calculateTotalIncome(yearMonth);
                double totalExpenses = user.calculateTotalExpenses(yearMonth);
                double moneySaved = totalIncome - totalExpenses;
                boolean goalMet = moneySaved >= savingsGoal.getGoal();
                String goalMetStr = goalMet ? "YES" : "NO";
                pw.println(yearMonth.toString() + "," + df.format(savingsGoal.getGoal()) + "," + df.format(savingsGoal.getPercentage()) + "," + df.format(moneySaved) + "," + goalMetStr);
}

            //Closes the PrintWriter and FileWriter to ensure data is properly flushed and that resources are freed up.
            pw.close();
            fileWriter.close();
            return true; //indicates CSV Report was generated successfully
        } catch (IOException e) {
            //If an error occurs during the file writing process
            e.printStackTrace();
            return false;
        }
    }
    
}    