Class: User
Attributes:
  - income: List<IncomeItem>
  - expenses: List<ExpenseItem>
  - savingsGoal: SavingsGoal
  - alertSettings: AlertSettings
Methods:
  + addIncomeItem(IncomeItem item)
  + addExpenseItem(ExpenseItem item)
  + setSavingsGoal(SavingsGoal goal)
  + updateAlertSettings(AlertSettings settings)
  + getTotalIncome(): double
  + getTotalExpenses(): double
  + getSavingsProgress(): double

Class: IncomeItem
Attributes:
  - source: String
  - amount: double
  - date: LocalDate
Methods:
  + getSource(): String
  + getAmount(): double
  + getDate(): LocalDate

Class: ExpenseItem
Attributes:
  - category: String
  - amount: double
  - date: LocalDate
Methods:
  + getCategory(): String
  + getAmount(): double
  + getDate(): LocalDate

Class: SavingsGoal
Attributes:
  - goalAmount: double
  - goalPercentage: double
Methods:
  + getGoalAmount(): double
  + getGoalPercentage(): double
  + setGoalAmount(double goalAmount)
  + setGoalPercentage(double goalPercentage)


Class: DataManager
Attributes:
Methods:
  + saveUserData(User user, String filePath): void
  + loadUserData(String filePath): User

Class: CLI
Attributes:
Methods:
  + displayMainMenu(): void
  + processUserInput(User user): void
  + manageIncome(User user): void
  + manageExpenses(User user): void
  + manageSavingsGoal(User user): void
  + viewFinancialSummary(User user): void
  + manageAlertsAndNotifications(User user): void
  + exportData(User user): void

Class: ReportGenerator
Attributes:
Methods:
  + generatePDFReport(User user): String

