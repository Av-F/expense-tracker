//import statements taken from tracker.java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class trackerTest {
    @Test
    public void testcalculateTotal() {
    Expense e1 = new Expense("Groceries", 50.00, "07-12-2003");
    Expense e2 = new Expense("Taxes", 280.00, "15-1-2004");
    ArrayList<Expense> list = new ArrayList<>();
    list.add(e1);
    list.add(e2);
    tracker.calculateTotal(list);
    assertEquals(tracker.calculateTotal(list), 330.0);
}
   @Test
    public void testcalculateTotalCategory() {
    Expense e1 = new Expense("Groceries", 50.00, "07-12-2003");
    Expense e2 = new Expense("Taxes", 280.00, "15-1-2004");
    ArrayList<Expense> list = new ArrayList<>();
    list.add(e1);
    list.add(e2);
    Map<String, Double> list2 = tracker.calculateTotalCategory(list);
    Map<String, Double> expected = new HashMap<>(); 
    expected.put("Groceries", 50.00);
    expected.put("Taxes", 280.00);
    assertEquals(list2, expected);
    }
   
    @Test
    public void testfindMin() {
        Expense e1 = new Expense("Groceries", 0.00, "07-12-2003");
        Expense e2 = new Expense("Taxes", 280.00, "15-1-2004");
        ArrayList<Expense> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        String expected = "Groceries";
        assertEquals(expected, tracker.findMin(list));
    }

    @Test
    public void testfindMax() {
        Expense e1 = new Expense("Groceries", 50.00, "07-12-2003");
        Expense e2 = new Expense("Taxes", 9000.00, "15-1-2004");
        ArrayList<Expense> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        String expected = "Taxes";
        assertEquals(expected, tracker.findMax(list));
    }

    @Test
    public void testgetMonth() {
        String date = "09-10-2011";
        String expected = "10-2011";
        assertEquals(expected, tracker.getMonth(date));
    }
    
    @Test
    public void testfindTrend() {
    Expense e1 = new Expense("Groceries", 50.00, "07-12-2003");
    Expense e2 = new Expense("Taxes", 280.00, "15-01-2004");
    ArrayList<Expense> list = new ArrayList<>();
    list.add(e1);
    list.add(e2);
    Map<String, Double> list2 = tracker.findTrend(list);
    
    Map<String, Double> expected = new HashMap<>(); 
    expected.put("12-2003", 50.00);
    expected.put("01-2004", 280.00);
    assertEquals(list2, expected);
    }

    @Test
    public void testexportDataCreation() {
    Expense e1 = new Expense("Groceries", 50.00, "07-12-2003");
    Expense e2 = new Expense("Taxes", 280.00, "15-01-2004");
    ArrayList<Expense> list = new ArrayList<>();
    list.add(e1);
    list.add(e2);
    String fileName = "test.txt";
    File file = new File(fileName);
    tracker.exportData(list, fileName);
    assertTrue(file.exists(), "Exported file should exist");
    // Delete test file when done 
    file.delete();
    }

    @Test
    public void testimportDataValidity() throws IOException { // Need to throw an exception statement in order for this to work
        //Create a file with one object in it 
        String filename = "test.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Groceries,100.50,08-07-2012");
        }
        // Create a list from the file
        ArrayList<Expense> list = tracker.importData(filename);
        // Find a value we would want to find from the imported file
        String expectedString = "Groceries";
        assertEquals(expectedString, list.get(0).category); //Test if what we want is in the expense object
        // Delete test file when done
        new File(filename).delete();
    }

    


}