# expense-tracker
Simple expense tracker for a coding assignment 

Function Breakdown:
Expense.java
- The Expense object contains 3 attributes: the category, the amount, and the date, which serves as the "data" from the instructions. I made this decision since the instructions were a little unclear, and neither the clarifications provided nor the help I asked for pointed in a specific direction. 

The object has a couple of functions assigned to the class:
- A constructor function that makes the object
- getCategory(), which returns the object's category
- getAmount(), which returns the object's amount
- getDate(), which returns the object's data

Additionally, there are two additional functions:
- An override of toString that returns each attribute separated by a comma so that it's in the CSV format for export/import
- CSV_Out, which uses regular expressions to split the incoming line into 3 parts stored in an array, each part being an attribute of the Expense object. After the split, the function returns a new expense with each part index serving as the attribute.

tracker.java
- After importing everything I needed for the project, the first function I made was the main function. By creating variables to turn on the menu, which is run by a do-while loop, any choice that the user makes directs the program to another function in the file, which simulates the task necessary. In order to allow for future edits to one expense file, I made sure to include the creation of an arraylist to call an import function to take in prior expenses, so that the user can build off of it.
- The next function was calculateTotal(), which takes in an array list of expenses and, in a for each loop of each expense, adds up the amount of each expense to a variable called total, then returns the total. Originally, I had this as a void function, but I had to change it so that I could test it properly using JUnit.
- The following function, calculateTotalCategory(), utilizes the concept of using the key-value pairs in hashmaps (similar to dictionaries in Python). By iterating through each expense, I was able to put them into a key-value pair where the key is the category and the value is the amount. I was able to avoid duplicate keys by having an if statement where if the map already contains the key, to update the value by adding the current value to the incoming expense value.
- findMin() and findMax() are near identical functions, where by taking in the array list, call the calculateTotalCategory() function, and from there iterate through the hashmap. By keeping track of the highest and lowest total (function dependent), I was able to save the smallest and the largest total and corresponding category, so that by the end of the function, I was able to return the required category per request.
- getMonth() is a helper function for findTrend() where all I do is return a string which is the substring of the month and year in the Expense's date.
- findTrend() uses getMonth() by first creating a hashmap and putting the month-year as the key and the amount as the value, then returning the map to the main function. In the main function, the map is iterated, displaying both the key and the value to model a monthly trend. I chose the monthly model since there was no indication of how I should track the expense trend, so I went with the industry standard.
- exportData() takes in an arraylist and a string of the file name, and by creating a file writer, I can write each expense using the toString method modified from Expense.java so that it's saved in a CSV format. This will either go successfully with a success message or, if there is an error, catch, to throw an error. This function gets called when the user decides to hit decision 6 to exit the program. Their work gets saved to an expenses.txt file, which is later called on in future instantiations. If there is no expenses.txt file, the program should make one for you
- importData() does the reverse of exportData() by first making the empty array list of expenses and populating it by creating a buffered reader which reads the txt file and by using the CSV_Out function, saves the expense and adds it to the array list. Once each line is read (one expense per line) the function prints out a success message and returns the list. If something went wrong, an error message is given out instead, prompting the user about the failure.

Additional notes:
- Originally, most of my functions were in the tracker.java be void functions, but I found that hard to test with so returning objects and utilizing them in main became the MO.
- If I had more time, I would have put further enforcement on creating an expense as described in my presentation. By doing so, it would idiot proof the insertion process by forcing the user to subscribe to the method of making an expense.

