// imports 
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class tracker {
        
    // calculateTotal() takes in the arrayList of expenses and calculates the total ammount of expenses 
     public static double calculateTotal(ArrayList<Expense> expenses) {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount(); 
        }
        return total;
    }
    
    // calculateTotalCategory() takes in an arrayList of expenses and calculates the expense by category
    public static Map<String, Double> calculateTotalCategory(ArrayList<Expense> expenses) {
       // Map the category and double 
        Map<String, Double> totals = new HashMap<>();        
        for (Expense e : expenses) {
            String eCategory = e.getCategory();
            double eAmount = e.getAmount();
            
            if (totals.containsKey(eCategory)) {
            totals.put(eCategory, totals.get(eCategory) + eAmount);
            } else {
            totals.put(eCategory, eAmount);
            }
        }   
        // Return a map of category and total
        return totals;
    }
    

 //findMin(ArrayList<Expense> expenses) is a function that when given an arraylist, returns the category with the minumum total expense
public static String findMin(ArrayList<Expense> expenses) {
   //Call findMinMax() to get a hashmap of each expense and their combined total
    Map<String, Double> list = calculateTotalCategory(expenses);
    // Create variables for the maximum and minimum categories
        String minCat = "";
        double minTotal = Double.POSITIVE_INFINITY;
        // For each loop that gets through each category and total
        for (Map.Entry<String, Double> entry : list.entrySet()) {
        String category = entry.getKey();
        double total = entry.getValue();
            // to then compare the current total with the min total
            // if found, then replace the min total with the curent total
        if (total < minTotal) {
            minTotal = total;
            minCat = category;
        }
    }
    return minCat;
}
 //findMax(ArrayList<Expense> expenses) is a function that when given an arraylist, returns the category with the maximum total expense
public static String findMax(ArrayList<Expense> expenses) {
   Map<String, Double> list = calculateTotalCategory(expenses);
    // Create variables for the maximum and minimum categories
        String maxCat= "";
        double maxTotal = Double.NEGATIVE_INFINITY;
        // For each loop that gets through each category and total
        for (Map.Entry<String, Double> entry : list.entrySet()) {
        String category = entry.getKey();
        double total = entry.getValue();
            // to then compare the current total with the max total
            // if found, then replace the max total with the curent total
        if (total > maxTotal) {
            maxTotal = total;
            maxCat = category;
        }
    }
    return maxCat;
}

// Helper method getMonth gets the month and year of the date
public static String getMonth(String date) {
    return date.substring(3, 10);
}

//Find trend shows the month/year trend of total expenses. 
public static Map<String, Double> findTrend(ArrayList<Expense> expenses) {
    // map out the key and value pair of month and total
    Map<String, Double> totals = new HashMap<>();
    // itterate through the expenses, grabbing the month and storing into the month string of the key value pair
    for (Expense e : expenses) {
        String month = getMonth(e.getDate());
        double amount = e.getAmount();
        if (totals.containsKey(month)) {
            totals.put(month, totals.get(month) + amount);
            } else {
            totals.put(month, amount);
            }
        }  
        return totals;
    }

// exportData(ArrayList<Expense> expenses, String filename) exports the data to a .txt file
public static void exportData(ArrayList<Expense> expenses, String filename) {
    // In a try catch, create a PrintWriter and print out each expense as formatted in expense.java
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
        for (Expense e : expenses) {
            writer.println(e.toString());
        }
        // If successful, tell user it got exported 
        System.out.println("Exported successfully to " + filename);
    } catch (IOException e) { // display an error otherwise
        System.out.println("Error writing file: " + e.getMessage());
    }
}

// importData(String filename) is a function that imports a .txt file and makes an arrayList of type Expense from the data
public static ArrayList<Expense> importData(String filename) {
    // Create an array list 
    ArrayList<Expense> expenses = new ArrayList<>();
    // try catch formula similar to export 
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) { // While there is a line, call the split function from expense.java
            Expense e = Expense.CSV_Out(line); // This creates a new Expense object to which we can then add to the arraylist
            expenses.add(e);
        }
        System.out.println("All expenses imported from:  " + filename); // if successful then display a success message
    } catch (IOException e) { //otherwise display a fail message
        System.out.println("Error reading file: " + e.getMessage());
    }
    return expenses;
}
    // Main function
    public static void main(String[] args) {
        // List of Expenses arrayList
        ArrayList<Expense> expenses = importData("expenses.txt");
        // variables such as scanner, user decision and if the menu is on
        boolean isOn = true;
        Scanner scan = new Scanner(System.in);
        int decision = 0;
        //The menu
        do {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Menu:");
            System.out.println("    Please enter the number corresponding to the desired task:");
            System.out.println("1: Enter an expense");
            System.out.println("2: Calculate total expenses");
            System.out.println("3: Calculate total expenses by category");
            System.out.println("4: Find the expense trend");
            System.out.println("5: Find the highest and lowest spend category");
            System.out.println("6: Exit program");
            System.out.println("--------------------------------------------------------------------");
            // Take in user input
            decision = scan.nextInt();
            scan.nextLine();
            // Create a query for user to enter the expense
            if(decision == 1) {
                System.out.println("Enter expense category:");
                String category = scan.nextLine();
                // We want to ensure that the first letter is always an uppercase so "foo" == "Foo"
                String categoryUpperCase = category.substring(0,1).toUpperCase() 
                + category.substring(1);
                category = categoryUpperCase;
                System.out.println("Enter expense amount (DO NOT include the $ sign):"); 
                double amount = scan.nextDouble();
                // Rounds the user value so that if they enter a decimal > 2, it will round to the 2nd decimal.
                amount = Math.round(amount * 100.00) / 100.00;
                scan.nextLine();
                
                System.out.println("Enter expense date: formatted DD-MM-YYYY");
                String date = scan.nextLine();
                
                if(date.matches("\\d{2}-\\d{2}-\\d{4}")) {
                 // Create the expense and add it into the arrayList
                Expense expense = new Expense(category.trim(), amount, date);
                expenses.add(expense);   
                } else {
                    System.out.println("invaid date selected, try again");
                }
            // Run the caculateTotal(expenses) function to calculate the running total
            } else if(decision == 2) {
                System.out.println("Total is: $"+ calculateTotal(expenses));
            //Run the CaculateTotalCategory(expenses) function to calculate total by category
            } else if(decision ==3) {
            
                Map<String,Double> list = calculateTotalCategory(expenses);
            // Then for each category, print out the category name and total
            for (Map.Entry<String, Double> entry : list.entrySet()) {
                String category = entry.getKey();
                double total = entry.getValue();
                System.out.println(category + "'s total cost is: " + total);
        }
            //Run findTrend(expenses) function to get the monthly spending trend
            } else if (decision == 4) {  
               Map<String, Double> list = findTrend(expenses);
                
               // print out the totals per month in a map specific for each loop
                for (Map.Entry<String, Double> entry : list.entrySet()) {
                String month = entry.getKey();
                double total = entry.getValue();
                System.out.println(month + "'s total cost is: " + total);
            }
            
            
                // Run the findMinMax(expenses) functions to caculate the cheapest and most expensive expense category
            } else if (decision == 5) { 
                calculateTotalCategory(expenses);
                // Print out the maximum and minimum cost category
                String minCat = findMin(expenses);
                String maxCat = findMax(expenses);
                System.out.println("The MOST expensive expense category is: " + maxCat);
                 System.out.println("The LEAST expensive expense category is: " + minCat);
                // switch isOn to false to break out of the loop
            } else if (decision == 6) {
                exportData(expenses,"expenses.txt");
                isOn = false;
            } else { // if the user put something invalid, prompt the user that the response was invalid and keep looping
                System.out.println("Invalid response, try again");
                scan.nextLine(); // move over to a blank line
            }
        } while(isOn == true);
        scan.close();
    }
}