##Class: User

####Attributes:
- serialVersionUID: long
- monthlyIncomeItems: HashMap<YearMonth, List<IncomeItem>>
- monthlyExpenseItems: HashMap<YearMonth, List<ExpenseItem>>
- incomeItems: List<IncomeItem>
- expenseItems: List<ExpenseItem>
- savingsGoals: HashMap<YearMonth, SavingsGoal>
####Methods:
+ User()
+ getIncomeItems(): List<IncomeItem>
+ setIncomeItems(incomeItems: List<IncomeItem>): void
+ getExpenseItems(): List<ExpenseItem>
+ setExpenseItems(expenseItems: List<ExpenseItem>): void
+ setSavingsGoal(savingsGoal: SavingsGoal, yearMonth: YearMonth): void
+ getSavingsGoal(yearMonth: YearMonth): SavingsGoal
+ addIncome(incomeItem: IncomeItem): void
+ addExpense(expenseItem: ExpenseItem): void
+ calculateTotalIncome(yearMonth: YearMonth): double
+ calculateTotalExpenses(yearMonth: YearMonth): double
+ calculateTotalSavings(yearMonth: YearMonth): double
+ updateSavingsGoal(goal: double, percentageBased: boolean, percentage: double, yearMonth: YearMonth): void
+ updateSavingsGoalBasedOnPercentage(yearMonth: YearMonth): void
+ save(user: User, fileName: String): void
+ load(fileName: String): User
+ getSavingsGoals(): Map<YearMonth, SavingsGoal>
+ getMonthlyIncomeItems(): Map<YearMonth, List<IncomeItem>>
+ getMonthlyExpenseItems(): Map<YearMonth, List<ExpenseItem>>

##Class: IncomeItem
####Attributes:
- source: String
- amount: double
- date: LocalDate
####Methods:
+ IncomeItem(source: String, amount: double, date: LocalDate)
+ getSource(): String
+ setSource(source: String): void
+ getAmount(): double
+ setAmount(amount: double): void
+ getDate(): LocalDate
+ setDate(date: LocalDate): void


##Class: ExpenseItem
####Attributes:
- category: String
- description: String
- amount: double
- date: LocalDate
####Methods:
+ ExpenseItem(category: String, description: String, amount: double, date: LocalDate)
+ getCategory(): String
+ setCategory(category: String): void
+ getDescription(): String
+ setDescription(description: String): void
+ getAmount(): double
+ setAmount(amount: double): void
+ getDate(): LocalDate
+ setDate(date: LocalDate): void

##Class: SavingsGoal
####Attributes:
- goal: double
- goalAmount: double
- percentage: double
- percentageBased: boolean
- goalPercentage: double
- originalPercentage: double
- yearMonth: YearMonth
####Methods:
+ setGoalPercentage(goalPercentage: double): void
+ getGoalAmount(): double
+ setGoalAmount(goalAmount: double): void
+ SavingsGoal()
+ SavingsGoal(goal: double)
+ SavingsGoal(goal: double, percentageBased: boolean, percentage: double, yearMonth: YearMonth)
+ getPercentage(): double
+ setPercentageBasedAndPercentage(percentageBased: boolean, percentage: double): void
+ getGoal(): double
+ setGoal(goal: double): void
+ isPercentageBased(): boolean
+ setOriginalPercentage(originalPercentage: double): void
+ getOriginalPercentage(): double
+ getGoalPercentage(): double
+ setPercentage(percentage: double): void
+ getYearMonth(): YearMonth


##Class: DataManager
####Attributes:
- filename: String
####Methods:
+ DataManager(filename: String)
+ saveUserData(user: User): void
+ loadUserData(): User

##Class: CLI
####Attributes:
- DATA_FILE: String
- user: User
- scanner: Scanner
- df: DecimalFormat
####Methods:
+ CLI(): void
+ start(): void
+ mainMenu(): void
+ manageIncome(): void
+ manageExpenses(): void
+ setSavingsGoal(): void
+ viewFinancialSummary(): void
+ exportData(): void
+ setSavingsGoalPercentage(): void
+ setSavingsGoalAmount(): void
+ main(args: String[]): void
+ viewSavingsGoal(): void

##Class: ReportGenerator
####Attributes: 
- user: User
- df: DecimalFormat
####Methods:
+ ReportGenerator(user: User): void
+ generateCSVReport(fileName: String): boolean


