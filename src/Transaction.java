/** Defines both Incomes and Expenses as transactions with identifying methods for use in operations
 */

@Deprecated
public abstract class Transaction {
    private int id;
    private String name, description;
    private static char currency;
    private double ammount;

    public static final int EXPENSE = 1, INCOME = 2; // Constants for type checks

    // Getter methods
    public char getCurrency() { return currency; }
    public double getAmmount() { return ammount; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getID() { return id; }
    protected abstract int getType();

    // Setter methods
    public void setCurrency(char currency) { Transaction.currency = currency; }
    public void setAmmount(double ammount) { this.ammount = ammount; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) {this.description = description; }
    public void setID(int id) { this.id = id;}
}

@Deprecated
// Distinguishing classes
class Expense extends Transaction {
    public final int getType() { return EXPENSE; }
}

@Deprecated
class Income extends Transaction {
    public final int getType() { return INCOME; }
}
