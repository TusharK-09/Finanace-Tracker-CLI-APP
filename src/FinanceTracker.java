import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class FinanceTracker {
    private double budget;
    private List<Expense> expenses;



    public FinanceTracker() {
        this.budget = 0;
        this.expenses = new  ArrayList<>();
    }


    //set budget
    public void setBudget(double budget){
        this.budget = budget;
    }


    //adddig an expense
    public void addExpense(double amount , String Category , String description , LocalDate date){
        Expense expense = new Expense(amount, Category, description, date);
        expenses.add(expense);
    }


    //get total expenses
    public double getTotalExpenses(){
        double total = 0;
        for(Expense e : expenses){
            total = total + e.getAmount();
        }
        return total;
    }

    //get remaining budget
    public double getRemainingBudget(){
        return budget - getTotalExpenses();
    }

    //get all expenses
    public List<Expense> getAllExpenses(){
        return expenses;
    }
}
