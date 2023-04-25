<h2 align="center">Algorithm</h3>

**Below is the basic Algorithm:**
##### 1. Initialize the program and load user data (if any)

##### 2. Present the main menu with the following options to the user:

  - Add income
  - Add expense
  - Set savings goal
  - View financial summary
  - Export data
  - Exit program

*To create a more visually pleasing CLI, use text formatting, ASCII Art, and clear navigation prompts.*

##### 3. Processing User Input

###### If the user selects 'Add income':

   - Prompt the user to enter income details (source, amount, date)
   - Validate and store the income data.
   - Update the financial summary

###### If the user selects 'Add expense':

  - Prompt the user to enter expense details (category, amount, date)
  - Validate and store the expense data
  - Update the financial summary

###### If the user selects 'Set savings goal':

  - Prompt the user to enter a monthly savings goal option: Fixed amount or percentage of income
    - If the user chooses a fixed amount, the user enters a monthly savings goal in dollars
    - If the user chooses a percentage of income, the user enters a percentage which is validated. The savings goal in dollars is then calculated based on the entered percentage and total income
  - Store the savings goal
  - Update the financial summary

###### If the user selects 'View financial summary':
1. Retrieve the user's financial data:
  - Access the stored data related to the user's income, expenses, and savings goal.

2. Calculate the financial summary:

  - Total income: Sum all income sources to obtain the total income.
  - Total expenses: Sum all expenses (by category or overall) to obtain the total expenses.
  - Savings progress: Calculate the savings progress by subtracting the total expenses from the total income. Then compare the savings progress with the user's savings goal to determine if the goal has been met, exceeded, or not reached.


3. Format the financial summary and display it


###### If the user selects 'Export Data':
1. Prompt the user to select a file format:
  - Display a list of supported file formats (e.g., CSV, PDF, etc.) and ask the user to choose one.
2. Display an error message if user inputs wrong choice
3. Generate the financial report:
  - Retrieve the user's financial data (income, expenses, savings goal, etc.) and organize it into a structured format that can be easily converted to the chosen file format.
  - Depending on the selected format, apply the appropriate formatting rules and convert the structured data into the desired file format. For example creating a CSV file with comma-separated values or a PDF document with formatted text and tables, or any other format-specific conversion.
4. Export the data
  - Prompt the user to specify a file name and location to save the exported file, and save the file in that location
  - Display a confirmation message if data exported successfully

##### 4. Loop back to step 2 until the user chooses to exit the program


**_Below is the in-depth basic Algorithm for each class:_**

##### User.java class:
1. Data Members:
- `monthlyIncomeItems:` a HashMap that stores `YearMonth` as the key and a list of `IncomeItem` objects as the value.
- `monthlyExpenseItems:` a HashMap that stores `YearMonth` as the key and a list of `ExpenseItem` objects as the value.
- `incomeItems:` a List of IncomeItem objects.
- `expenseItems:` a List of ExpenseItem objects.
- `savingsGoals:` a HashMap that stores YearMonth as the key and a SavingsGoal object as the value.

2. Initializer (constructor):
- Initialize all data members with empty data structures (e.g., empty ArrayLists or HashMaps).

3. Define access methods for all data members (getters and setters):

- `getIncomeItems` and `setIncomeItems` for `incomeItems`
- `getExpenseItems` and `setExpenseItems` for `expenseItems`
- `getSavingsGoal` and `setSavingsGoal` for `savingsGoals`
- `getMonthlyIncomeItems` for `monthlyIncomeItems`
- `getMonthlyExpenseItems` for `monthlyExpenseItems`

4. All other additional methods:
- `addIncome` method for adding an IncomeItem object to the `monthlyIncomeItems` HashMap and updating the savings goal if necessary
- `addExpense` method for adding an ExpenseItem object to the `monthlyExpenseItems` HashMap
- `calculateTotalIncome` method for calculating the total income for a specific year and month
- `calculateTotalExpenses` method for calculating the total expenses for a specific year and month
- `calculateTotalSavings` method for calculating the total savings for a specific year and month
- `updateSavingsGoal` method for updating the savings goal based on the provided parameters
- `updateSavingsGoalBasedOnPercentage` method for updating the savings goal based on the original percentage for a specific year and month
- `save` method for saving a User object to a file
- `load` method for loading a User object from a file

##### IncomeItem.java class:
1. Data Members:
- `source`: a String representing the source of the income.
- `amount`: a double representing the amount of income.
- `date`: a LocalDate representing the date of the income.

2. Initializer (constructor)
- Accept three parameters: `source, amount, and date.`
- Assign the values of the parameters to the data members.

3. Define access methods for all data members (getters and setters):
- `getSource` and `setSource` for `source`
- `getAmount` and `setAmount` for `amount`
- `getDate` and `setDate` for `date`

##### ExpenseItem.java class:
1. Data Members:
- `category`: a String representing the category of the expense.
- `description`: a String representing the description of the expense.
- `amount`: a double representing the amount of the expense.
- `date`: a LocalDate representing the date of the expense.

2. Initiializer (Constructor):
- Accept four parameters: `category, description, amount, and date.`
- Assign the values of the parameters to the corresponding data members.

3. Define access methods for all data members (getters and setters):

- `getCategory` and `setCategory` for `category`
- `getDescription` and `setDescription` for `description`
- `getAmount` and `setAmount` for `amount`
- `getDate` and `setDate` for `date`

##### SavingsGoal.java class:
1. Data Members:
- `goal`: a double representing the savings goal amount.
- `goalAmount`: a double representing the current goal amount.
- `percentage:` a double representing the percentage of the savings goal.
- `percentageBased:` a boolean indicating whether the goal is percentage-based.
- `goalPercentage:` a double representing the goal percentage.
- `originalPercentage:` a double representing the original percentage value.
- `yearMonth:` a YearMonth object representing the year and month of the savings goal.


2. Initializers (Constructors):
- Default constructor which sets `goal` to 0, `percentageBased` to false, `percentage` to 0
- Constructor with `goal` parameter, sets `goal` to given value, `percentage` to 0 and `percentageBased` to false.

Constructor with `goal`, `percentageBased`, `percentage`, and `yearMonth` parameters:
- Set `goal` to the given value.
- Set `percentageBased` to the given value.
- Set `percentage` and `originalPercentage` to the given value.
- Set `yearMonth` to the given value.
- Set `goalAmount` to the given value.

3. Define access methods for all data members (getters and setters):
- `getGoal`, `setGoal` for `goal`
- `getGoalAmount`, `setGoalAmount` for `goalAmount`
- `getPercentage`, `setPercentage` for `percentage`
- `isPercentageBased` for `percentageBased`
- `getGoalPercentage`, `setGoalPercentage` for `goalPercentage`
- `getOriginalPercentage`, `setOriginalPercentage` for `originalPercentage`
- `getYearMonth` for `yearMonth`


##### DataManager.java class:
1. Data Members
- `filename`: a String representing the name of the file where user data is saved
2. Initializers (Constructors)
- Accept a `filename` parameter and set the filename data member to the given value.

3. `saveUserData` method:
-  Accept a `User` object as a parameter.
- Create a `FileOutputStream` object with the `filename` data member.
- Create an `ObjectOutputStream` object using the `FileOutputStream`.
- Write the `User` object to the `ObjectOutputStream`.
- Close the `ObjectOutputStream`.
- Catch and handle any `IOException` that may occur.
4. `loadUserData` method:
- initialize a `User` object, user, to `null.`
- Create a `FileInputStream` object with the `filename` data member.
- Create an `ObjectInputStream` object using the `FileInputStream.`
- Read a `User` object from the `ObjectInputStream` and assign it to user.
- Close the `ObjectInputStream.`
- Catch and handle any `IOException` or `ClassNotFoundException` that may occur.
- Return the `User object.`

##### CLI.java class:
1. Data Members
- `DATA_FILE`: a constant String representing the file name for saving user data.
- `user`: a User object to manage income, expenses, and savings goals.
- `scanner`: a Scanner object for reading input from the command line.
- `df:` a DecimalFormat object for formatting currency values.

2. Initializer (Constructor)
- Initialize the User, Scanner, and DecimalFormat objects.
- Load the user data from the `DATA_FILE.`

3. `start` method:
- prints a welcome message and calls the `mainMenu` method

4. `mainMenu` method:
- Display main menu in a loop, read the user's choice, and based on the user choice call the appropriate method.

5. `main` method creates a new `CLI` object and calls the `start` method on the created object
6. `manageIncome` adds income items to the user
7. `manageExpenses` adds expense items to the user
8. `setSavingsGoal` manages the user's savings goals (amount or percentage based)
9. `viewFinancialSummary` displays the user's financial summary for a given year and month
10. `exportData` exports the user's data to a CSV file.
11. `setSavingsGoalAmount` sets an amount based savings goal (not percentage based)
12. `viewSavingsGoalAmount` displays the user's savings goal for a specific year and month.

#####  ReportGenerator.java class:
1. Data Members:
- `User` object named `user` to manage income, expenses and savings goals.
- A `DecimalFormat` object named `df` for formatting currency values

2. `generateCSVReport` method takes a file name as a parameter and returns a boolean value. It also create a new `File, FileWriter, and PrintWriter `objects. Furthermore, it writes the headers and data for the Income Items, Expenses, Savings Goals sections. Lastly, it returns true if the report was generated successfully, and false otherwise. Also closes the `PrintWriter` and `FileWriter` objects