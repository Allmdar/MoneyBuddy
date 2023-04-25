import java.time.LocalDate;
import java.io.Serializable;

public class IncomeItem implements Serializable {
    private String source;
    private double amount;
    private LocalDate date;

    public IncomeItem(String source, double amount, LocalDate date) {
        this.source = source;
        this.amount = amount;
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}


