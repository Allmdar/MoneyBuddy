//This class represents a single expense item and provides methods to get and set its properties

import java.time.LocalDate; // Represents date (year,month, and day) without time information
import java.io.Serializable; 
//The Serializble interface is used to indicate that a class can be converted into a byte stream for storage
// In the context of the ExpenseItem class, implementing the Serializable interface allows the class objects to be saved and loaded from a file.


public class ExpenseItem implements Serializable { // class is declared as public and implements the 'Serializable' interface
// 'public' access modifiers allow other parts of the application to create instances of the ExpenseItem class and interact with its methods



    //Instance variables
    private String category;
    private String description;
    private double amount;
    private LocalDate date;

    //Constructor
    //This constructor initializes an ExpenseItem object with the specified category, description, amount, and date.
    public ExpenseItem(String category, String description, double amount, LocalDate date) {
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }
    
    //getter and setter methods
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
