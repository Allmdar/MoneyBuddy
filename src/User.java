import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;


public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<YearMonth, List<IncomeItem>> monthlyIncomeItems;
    private HashMap<YearMonth, List<ExpenseItem>> monthlyExpenseItems;

    private List<IncomeItem> incomeItems;
    private List<ExpenseItem> expenseItems;
    private HashMap<YearMonth, SavingsGoal> savingsGoals;


    public User() {
      this.incomeItems = new ArrayList<>();
      this.expenseItems = new ArrayList<>();
      this.savingsGoals = new HashMap<>();
      this.monthlyIncomeItems = new HashMap<>();
      this.monthlyExpenseItems = new HashMap<>();
    }

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
    


    public void addIncome(IncomeItem incomeItem) {
        YearMonth yearMonth = YearMonth.from(incomeItem.getDate());
        if (!monthlyIncomeItems.containsKey(yearMonth)) {
            monthlyIncomeItems.put(yearMonth, new ArrayList<>());
        }
        monthlyIncomeItems.get(yearMonth).add(incomeItem);
        SavingsGoal currentSavingsGoal = getSavingsGoal(yearMonth);
        if (currentSavingsGoal != null && currentSavingsGoal.isPercentageBased() && currentSavingsGoal.getYearMonth().equals(yearMonth)) {
            updateSavingsGoalBasedOnPercentage(yearMonth);
        }
    }
    
    
    public void addExpense(ExpenseItem expenseItem) {
        YearMonth yearMonth = YearMonth.from(expenseItem.getDate());
        monthlyExpenseItems.putIfAbsent(yearMonth, new ArrayList<>());
        monthlyExpenseItems.get(yearMonth).add(expenseItem);
    }

    public double calculateTotalIncome(YearMonth yearMonth) {
        double totalIncome = 0;
        for (IncomeItem incomeItem : monthlyIncomeItems.getOrDefault(yearMonth, new ArrayList<>())) {
            totalIncome += incomeItem.getAmount();
        }
        return totalIncome;
    }

    public double calculateTotalExpenses(YearMonth yearMonth) {
        double totalExpenses = 0;
        for (ExpenseItem expenseItem : monthlyExpenseItems.getOrDefault(yearMonth, new ArrayList<>())) {
            totalExpenses += expenseItem.getAmount();
        }
        return totalExpenses;
    }

    public double calculateTotalSavings(YearMonth yearMonth) {
        double totalIncome = calculateTotalIncome(yearMonth);
        double totalExpenses = calculateTotalExpenses(yearMonth);
        return totalIncome - totalExpenses;
    }

    public void updateSavingsGoal(double goal, boolean percentageBased, double percentage, YearMonth yearMonth) {
        SavingsGoal newSavingsGoal = new SavingsGoal(goal, percentageBased, percentage, yearMonth);
        setSavingsGoal(newSavingsGoal, yearMonth);
    }
    
    public void updateSavingsGoalBasedOnPercentage(YearMonth yearMonth) {
        SavingsGoal currentSavingsGoal = getSavingsGoal(yearMonth);
        if (currentSavingsGoal != null && currentSavingsGoal.isPercentageBased()) {
            double totalIncome = calculateTotalIncome(yearMonth);
            double newGoalAmount = totalIncome * currentSavingsGoal.getOriginalPercentage() / 100;
            currentSavingsGoal.setPercentage(currentSavingsGoal.getOriginalPercentage());
            currentSavingsGoal.setGoalAmount(newGoalAmount);
            currentSavingsGoal.setGoal(newGoalAmount);
        }
    }
    
    
    public static void save(User user, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(user);
        } catch (IOException e) {
            System.err.println("Error saving user data: " + e.getMessage());
        }
    }
    
    public static User load(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (User) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading user data: " + e.getMessage());
        }
        return new User(); 
    }

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