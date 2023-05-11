//Class stores information about goal amount, whether goal is percentage-based, the percentage of income to save, the specific month and year for which goal is set


import java.time.YearMonth;
import java.io.Serializable;
//The Serializble interface is used to indicate that a class can be converted into a byte stream for storage
// In the context of the ExpenseItem class, implementing the Serializable interface allows the class objects to be saved and loaded from a file.


public class SavingsGoal implements Serializable {
    //Instance Variables
    private double goal; //represents the amount of money the user wants to save for a specific month and year
    private double goalAmount; //  double of the goal amount when it is not percentage based
    private double percentage; // double representing the percentage of the user's income they want to save (when savings goal is percentage based)
    private boolean percentageBased; //Boolean indicating whether the savings goal is percentage based or amount based
    private double goalPercentage; //A double representing the goal percentage. This stores the percentage of the goal relative to the user's income.
    private double originalPercentage; //double representing the original percentage value, which is the same as 'percentage' when the goal is first created
    private YearMonth yearMonth; //represents month and year foe which the savings goal is set

    //Constructors

    //Default constructor that initializes the 'goal,' 'percentage,' and 'percentageBased' attributes with default values
    public SavingsGoal() {
        this.goal = 0;
        this.percentageBased = false;
        this.percentage = 0;
    }
    
    //A constructor that takes the goal amount as input (when percentageBased is false)
    public SavingsGoal(double goal) {
        this.goal = 0;
        this.percentage = 0;
        this.percentageBased = false;
    }

    public SavingsGoal(double goal, boolean percentageBased, double percentage, YearMonth yearMonth) {
        this.goal = goal;
        this.percentageBased = percentageBased;
        this.percentage = percentage;
        this.originalPercentage = percentage; 
        this.yearMonth = yearMonth;
        this.goalAmount = goal; 
    }

    
    //Setters and Getter Methods (Allows access and modification of the attributes while maintaining encapsulation)
    public void setGoalPercentage(double goalPercentage) {
        this.goalPercentage = goalPercentage;
    }

    public void setGoalAmount(double goalAmount) {
        this.goalAmount = goalAmount;
    }

    public double getGoalAmount() {
        return goalAmount;
    }
    

    public double getPercentage() {
        return percentage;
    }

    public void setPercentageBasedAndPercentage(boolean percentageBased, double percentage) {
        this.percentageBased = percentageBased;
        this.percentage = percentage;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public boolean isPercentageBased() {
        return percentageBased;
    }

    public void setOriginalPercentage(double originalPercentage) {
        this.originalPercentage = originalPercentage;
    }

    public double getOriginalPercentage() {
        return originalPercentage;
    }

    public double getGoalPercentage() {
        return goalPercentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
        this.originalPercentage = percentage;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }
}
