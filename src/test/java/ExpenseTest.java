// import junit and the assertions
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ExpenseTest {
    @Test
    public void testgetcategory() {
    Expense test = new Expense("Groceries", 50.00, "07-12-2003");
     assertEquals("Groceries", test.getCategory());
    }
    
     @Test
    public void testBlankCaegory() {
    Expense test = new Expense("", 50.00, "07-12-2003");
     assertEquals("", test.getCategory());
    }

    @Test
    public void testgetAmount() {
    Expense test = new Expense("Groceries", 50.00, "07-12-2003");
     assertEquals(50, test.getAmount());
    }

    @Test
    public void testnoAmount() {
    Expense test = new Expense("Groceries", 0, "07-12-2003");
     assertEquals(0, test.getAmount());
    }

    @Test
    public void testgetDate() {
    Expense test = new Expense("Groceries", 12, "07-12-2003");
     assertEquals("07-12-2003", test.getDate());
    }

    @Test
    public void testOveride() {
    Expense test = new Expense("Groceries", 30, "07-12-2003");
    String expected = "Groceries,30.0,07-12-2003";
    assertEquals(expected, test.toString());
    }

    @Test
    public void testCSV_OUT() {
    Expense expected = new Expense("dog food", 30, "07-12-2003");
    String line = "dog food,30.0,07-12-2003";
    Expense e = Expense.CSV_Out(line);
    assertEquals(expected.getAmount(), e.getAmount());
    assertEquals(expected.getCategory(), e.getCategory());
    assertEquals(expected.getDate(), e.getDate());
    }
}