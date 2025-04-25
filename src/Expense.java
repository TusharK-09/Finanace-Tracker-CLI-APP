import java.time.LocalDate;
public class Expense{
    private double amount;
    private String category;
    private String description;
    private LocalDate date;

    //making
    public Expense(double amount , String category  ,String description , LocalDate date ){
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    //getter methods
    public double getAmount(){

        return amount;
    }

    public String getCategory(){
        return category;
    }

    public String getDescription(){
        return description;
    }

    public LocalDate getDate(){
        return date;
    }

    //displaying expense
    public String toString(){
        return "Amount: Rs." + amount +
                ", Category: " + category +
                ", Description: " + description +
                ", Date: " + date;
    }
}