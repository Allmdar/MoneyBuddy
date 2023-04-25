import java.io.Serializable;
import java.time.YearMonth;

public class SavingsGoal implements Serializable {
    private double goal;
    private double goalAmount;
    private double percentage;
    private boolean percentageBased;
    private double goalPercentage;
    private double originalPercentage;
    private YearMonth yearMonth;

    public void setGoalPercentage(double goalPercentage) {
        this.goalPercentage = goalPercentage;
    }

    public double getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(double goalAmount) {
        this.goalAmount = goalAmount;
    }

    public SavingsGoal() {
        this.goal = 0;
        this.percentageBased = false;
        this.percentage = 0;
    }

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
