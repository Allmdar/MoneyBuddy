//Class represents user who has list of income items, expense items, and savings goals. 
//Manages the user's finanial data and performs various calculations on it

import java.io.Serializable;
import java.util.ArrayList; //a resizable-array implementation of the List interface -> stores the list of IncomeItem and ExpenseItem object
import java.util.List; 
import java.io.FileInputStream; //reads data from a file
import java.io.FileOutputStream; //writes data to a file
import java.io.IOException; //throws an exception when an I/O operation fails (save and load method)
import java.io.ObjectInputStream; // used for deserializing objects from a stream of bytes (load method)
import java.io.ObjectOutputStream; //used for serializing objects to a stream of bytes (save method)
import java.time.YearMonth; // Represents Year and Month without day information
import java.util.HashMap; // HashMap class -> Data Structure that associates key with values using a hashing function
//Used as the key for the monthlyIncomeItems, monthlyExpenseItems, and savingsGoals HashMaps
import java.util.Map; //used as the return type for the getSavingsGoals, getMonthlyIncomeItems, and getMonthlyExpenseItems methods in the User class.


public class User implements Serializable {
    private static final long serialVersionUID = 1L; //Serves as a version control mechanism when serializing and deserializing objects
    
    //attributes and instance variables
    private HashMap<YearMonth, List<IncomeItem>> monthlyIncomeItems; //Hashmap that maps a YearMonth key to a list of IncomeItem objects for that month
    private HashMap<YearMonth, List<ExpenseItem>> monthlyExpenseItems; //maps a YearMonth key to a list of ExpenseItem objects for that month.

    private List<IncomeItem> incomeItems; //A list of all 'IncomeItem' objects
    private List<ExpenseItem> expenseItems; 
    private HashMap<YearMonth, SavingsGoal> savingsGoals; //maps a YearMonth key to a SavingsGoal object for that month.


    //Constructor that initializes the instance variables, creating empty lists and HashMaps for the attributes.
    public User() {
      this.incomeItems = new ArrayList<>();
      this.expenseItems = new ArrayList<>();
      this.savingsGoals = new HashMap<>();
      this.monthlyIncomeItems = new HashMap<>();
      this.monthlyExpenseItems = new HashMap<>();
    }

    //Getters and Setter methods

    public List<IncomeItem> getIncomeItems() {
        return incomeItems;
    }

    public void setIncomeItems(List<IncomeItem> incomeItems) {
        this.incomeItems = incomeItems;
    }

    public List<ExpenseItem> getExpenseItems() {
        return expenseItems;
    }

    public void setExpenseItems(List<ExpenseItem> expenseItems) {
        this.expenseItems = expenseItems;
    }

    public void setSavingsGoal(SavingsGoal savingsGoal, YearMonth yearMonth) {
        this.savingsGoals.put(yearMonth, savingsGoal);
    }
    
    public SavingsGoal getSavingsGoal(YearMonth yearMonth) {
        return savingsGoals.getOrDefault(yearMonth, null);
    }
    

    //Methods


    //adds IncomeItem object to appropriate YearMonth in monthlyIncomeItems data structure
    public void addIncome(IncomeItem incomeItem) {
        YearMonth yearMonth = YearMonth.from(incomeItem.getDate()); //Extracts YearMonth object from the incomeItem's date
        if (!monthlyIncomeItems.containsKey(yearMonth)) {  // Checks if the monthlyIncomeItems HashMap already contains the yearMonth key
            monthlyIncomeItems.put(yearMonth, new ArrayList<>()); //this line adds a new key-value pair where the key is yearMonth and the value is a new empty list of IncomeItem objects
        }
        //adds the incomeItem to the list of IncomeItem objects associated with the yearMonth key.
        monthlyIncomeItems.get(yearMonth).add(incomeItem);
        SavingsGoal currentSavingsGoal = getSavingsGoal(yearMonth); //retrives the SavingsGoal object associated with the yearMonth using the getSavingsGoal() method
        //checks if there is a savings goal for the yearMonth, if it is percentage-based, and if the savings goal's YearMonth matches the yearMonth of the income item
        if (currentSavingsGoal != null && currentSavingsGoal.isPercentageBased() && currentSavingsGoal.getYearMonth().equals(yearMonth)) {
            updateSavingsGoalBasedOnPercentage(yearMonth);
        }
    }
    
    
    //adds an ExpenseItem object to the appropriate 'YearMonth' monthlyExpenseItems data structure of a HashMap
    public void addExpense(ExpenseItem expenseItem) {
        YearMonth yearMonth = YearMonth.from(expenseItem.getDate());
        monthlyExpenseItems.putIfAbsent(yearMonth, new ArrayList<>()); //checks if the monthlyExpenseItems HashMap already contains the yearMonth key
        monthlyExpenseItems.get(yearMonth).add(expenseItem); //adds the expenseItem to the list of 'ExpenseItem' objects associated with the yearMonth key
    }



    // calculates the total income for a given YearMonth by summing up the IncomeItem amounts.
    public double calculateTotalIncome(YearMonth yearMonth) {
        double totalIncome = 0;
        for (IncomeItem incomeItem : monthlyIncomeItems.getOrDefault(yearMonth, new ArrayList<>())) {
            totalIncome += incomeItem.getAmount();
        }
        return totalIncome;
    }

    // calculates the total expenses for a given YearMonth by summing up the ExpenseItem amounts
    public double calculateTotalExpenses(YearMonth yearMonth) {
        double totalExpenses = 0;
        for (ExpenseItem expenseItem : monthlyExpenseItems.getOrDefault(yearMonth, new ArrayList<>())) {
            totalExpenses += expenseItem.getAmount();
        }
        return totalExpenses;
    }

    // calculates the total savings for a given YearMonth by subtracting total expenses from total income
    public double calculateTotalSavings(YearMonth yearMonth) {
        double totalIncome = calculateTotalIncome(yearMonth);
        double totalExpenses = calculateTotalExpenses(yearMonth);
        return totalIncome - totalExpenses;
    }

    //  creates a new SavingsGoal object and sets it as the savings goal for the given YearMonth
    public void updateSavingsGoal(double goal, boolean percentageBased, double percentage, YearMonth yearMonth) {
        SavingsGoal newSavingsGoal = new SavingsGoal(goal, percentageBased, percentage, yearMonth);
        setSavingsGoal(newSavingsGoal, yearMonth);
    }
    
    // updates the savings goal amount for a given YearMonth based on the percentage and the total income for the month.
    public void updateSavingsGoalBasedOnPercentage(YearMonth yearMonth) {
        SavingsGoal currentSavingsGoal = getSavingsGoal(yearMonth); //retrieves the 'SavingsGoal' object associated with the given YearMonth from the 'savingsGoals' HashMap 
        if (currentSavingsGoal != null && currentSavingsGoal.isPercentageBased()) { //checks if savings goal exists and whether if it is percentage based
            double totalIncome = calculateTotalIncome(yearMonth);
            double newGoalAmount = totalIncome * currentSavingsGoal.getOriginalPercentage() / 100;
            currentSavingsGoal.setPercentage(currentSavingsGoal.getOriginalPercentage());
            currentSavingsGoal.setGoalAmount(newGoalAmount);
            currentSavingsGoal.setGoal(newGoalAmount);
        }
    }
    
    //saves a User object to a file with the specified file name using serialization.
    public static void save(User user, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(user);
        } catch (IOException e) {
            System.err.println("Error saving user data: " + e.getMessage());
        }
    }
    
    //loads a User object from a file with the specified file name using deserialization.
    public static User load(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (User) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading user data: " + e.getMessage());
        }
        return new User(); 
    }


    //Getters that return respective 'HashMaps' to provide access to the stored data

    public Map<YearMonth, SavingsGoal> getSavingsGoals() {
        return savingsGoals;
    }

    public Map<YearMonth, List<IncomeItem>> getMonthlyIncomeItems() {
        return monthlyIncomeItems;
    }
    
    public Map<YearMonth, List<ExpenseItem>> getMonthlyExpenseItems() {
        return monthlyExpenseItems;
    }

}