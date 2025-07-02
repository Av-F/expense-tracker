// Class of Expense
public class Expense {
    String category;
    double amount;
    String date;
// Constructor function 
public Expense(String category, double amount, String date) {
    this.category = category;
    this.amount = amount; 
    this.date = date;
}
// getCategory() to return the category
public String getCategory() {
    return category;
}
// getAmount() to return the amount
public double getAmount() {
        return amount;
    }
// getDate() to return the date 
public String getDate() {
    return date;
}
    
    @Override // Will need to override the toString() so that the csvFile is saved with each attribute seperated by a comma
    public String toString() {
        return category + "," + amount + "," + date;
    }
    // This function splits the CSV file such that it's a string array with each attribute being a string
    public static Expense CSV_Out(String line) {
        String[] parts = line.split(","); //split the line by each comma
        return new Expense(parts[0], Double.parseDouble(parts[1]), parts[2]); //Return a new expense with each part 
    } 


}