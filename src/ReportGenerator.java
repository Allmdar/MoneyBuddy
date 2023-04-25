import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.io.File;
import java.time.YearMonth;
import java.util.Map;
import java.util.List;
import java.io.FileNotFoundException;



public class ReportGenerator {
    private User user;
    private DecimalFormat df;

    public ReportGenerator(User user) {
        this.user = user;
        this.df = new DecimalFormat("#.00");
    }

    public boolean generateCSVReport(String fileName) throws FileNotFoundException {
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fileWriter);
    
            pw.println("Income Items:");
            pw.println("YearMonth,Description,Amount");
            for (Map.Entry<YearMonth, List<IncomeItem>> entry : user.getMonthlyIncomeItems().entrySet()) {
                YearMonth yearMonth = entry.getKey();
                List<IncomeItem> incomeItems = entry.getValue();
                for (IncomeItem incomeItem : incomeItems) {
                    pw.println(yearMonth.toString() + "," + incomeItem.getSource() + "," + df.format(incomeItem.getAmount()));
                }
            }
    
            pw.println("\nExpenses:");
            pw.println("YearMonth,Category,Description,Amount");
            for (Map.Entry<YearMonth, List<ExpenseItem>> entry : user.getMonthlyExpenseItems().entrySet()) {
                YearMonth yearMonth = entry.getKey();
                List<ExpenseItem> expenseItems = entry.getValue();
                for (ExpenseItem expenseItem : expenseItems) {
                    pw.println(yearMonth.toString() + "," + expenseItem.getCategory() + "," + expenseItem.getDescription() + "," + df.format(expenseItem.getAmount()));
                }
            }
    
            pw.println("\nSavings Goals:");
            pw.println("YearMonth,Amount,Percentage,MoneySaved,GoalMet");
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

    
            pw.close();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}    

