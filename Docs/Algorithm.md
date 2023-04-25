# Algorithm (Financial Tracker - Money Buddy)

### 1. Initialize the program and load user data (if any)

### 2. Present the main menu with the following options to the user:

  - Add income
  - Add expense
  - Set savings goal
  - View financial summary
  - Export data
  - Exit program

*To create a more visually pleasing CLI, use text formatting, ASCII Art, and clear navigation prompts.*

### 3. Processing User Input

#### If the user selects 'Add income':

   - Prompt the user to enter income details (source, amount, date)
   - Validate and store the income data.
   - Update the financial summary

#### If the user selects 'Add expense':

  - Prompt the user to enter expense details (category, amount, date)
  - Validate and store the expense data
  - Update the financial summary

#### If the user selects 'Set savings goal':

  - Prompt the user to enter a monthly savings goal option: Fixed amount or percentage of income
    - If the user chooses a fixed amount, the user enters a monthly savings goal in dollars
    - If the user chooses a percentage of income, the user enters a percentage which is validated. The savings goal in dollars is then calculated based on the entered percentage and total income
  - Store the savings goal
  - Update the financial summary

#### If the user selects 'View financial summary':
1. Retrieve the user's financial data:
  - Access the stored data related to the user's income, expenses, and savings goal.

2. Calculate the financial summary:

  - Total income: Sum all income sources to obtain the total income.
  - Total expenses: Sum all expenses (by category or overall) to obtain the total expenses.
  - Savings progress: Calculate the savings progress by subtracting the total expenses from the total income. Then compare the savings progress with the user's savings goal to determine if the goal has been met, exceeded, or not reached.
  - Alerts or notifications: Check if any alerts or notifications are triggered based on the user's financial data (e.g., approaching or exceeding the savings goal, overspending in a specific category, etc.).

3. Format the financial summary and display it


#### If the user selects 'Export Data':
1. Prompt the user to select a file format:
  - Display a list of supported file formats (e.g., CSV, PDF, etc.) and ask the user to choose one.
2. Display an error message if user inputs wrong choice
3. Generate the financial report:
  - Retrieve the user's financial data (income, expenses, savings goal, etc.) and organize it into a structured format that can be easily converted to the chosen file format.
  - Depending on the selected format, apply the appropriate formatting rules and convert the structured data into the desired file format. For example creating a CSV file with comma-separated values or a PDF document with formatted text and tables, or any other format-specific conversion.
4. Export the data
  - Prompt the user to specify a file name and location to save the exported file, and save the file in that location
  - Display a confirmation message if data exported successfully

### 4. Loop back to step 2 until the user chooses to exit the program