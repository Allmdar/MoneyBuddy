// This class represents an income item

import java.time.LocalDate; // Represents date (year,month, and day) without time information of the income
import java.io.Serializable;
//The Serializble interface is used to indicate that a class can be converted into a byte stream for storage
// In the context of the ExpenseItem class, implementing the Serializable interface allows the class objects to be saved and loaded from a file.



public class IncomeItem implements Serializable {  // class is declared as public and implements the 'Serializable' interface to allow instances of the class to be serialized and deserialized
// 'public' access modifiers allow other parts of the application to create instances of the ExpenseItem class and interact with its methods
    
    //instance variables
    private String source;
    private double amount;
    private LocalDate date;

    //Constructor which takes in three arguments and initializes the instance variables
    public IncomeItem(String source, double amount, LocalDate date) {
        this.source = source;
        this.amount = amount;
        this.date = date;
    }

    //Getter and Setter methods
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


