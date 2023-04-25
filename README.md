<h1 align="center"><u>Project Proposal</u></h1>

<h2 align="center">Project Summary</h3>

##### Proposed Project Title: Money Buddy (Personal Financial Tracker)

Money Buddy is a personal financial tracker application that helps users effectively manage their income, expenses, and savings goals. The application provides a simple command-line interface for users to input and keep track of their financial transactions for a specific month and year, set and review savings goals, and analyze their financial performance through reports.

##### Intended User
The intended user is anyone looking to manage their personal finances, track income and expenses, and set savings goals to better plan their financial future.

##### What problem is the project trying to solve?
The project aims to address the challenge of effectively managing personal finances by providing users with an easy-to-use tool that consolidates financial information, allowing them to track income and expenses, set savings goals, and analyze their financial performance. Furthermore, if the user feels unsafe connecting their bank account to a third party app to use these tools, they can simply resort to Money Buddy


##### Which technologies will you need?
- Java Development Kit (JDK) to run an executable JAR file
- A serialized file ('financial_data.ser') for storing and loading user data.
- A CSV file export for generating financial reports.
- A command-line interface (CLI) for user interaction.


<h2 align="center">Use Case Analysis</h3>

**There are two ways you can start MoneyBuddy:**

<h5 align="center">Using an executable jar file</h5>

1. Make sure there is nothing in `bin` directory otherwise run `make clean` on the terminal
![Step1](/images/img0.png) 
![Step2](/images/img1.png)

2. run `make jar` on terminal
![Step3](/images/img2.png)

3. Now, make sure you see the `executable jar` file in the project directory
![Step4](/images/img3.png)

4. Now run `java -jar MoneyBuddy.jar` on the terminal, which will run the program
![Step5](/images/img4.png)

<h5 align="center">Using make and make run</h5>

1. Run `make` and `make run` on the terminal
![Step6](/images/img5.png)

**Now, how do you navigate through the Money Buddy Program itself?**

1. Lets see how you can report your income to Money Buddy
- Choose option 1, enter your income source and income itself
![Income](/images/img6.png)

2. Now lets see how you can manage your expenses in Money Buddy
- Choose option 2, 



<h2 align="center">Data Design</h3>

<h2 align="center">UI Design</h3>

<h2 align="center">Algorithm</h3>

<h2 align="center">Future Improvements</h3>

**In the future, additional features and enhancements could be considered:**
- Implement Mult-user support and also enhancing data security measures to protect user financial information, such as implementing encryption, password protection, and more measures.
- Graphical User Interface (GUI) to improve user experience
- Using a database to store and manage user data more efficiently.
- Add customizable budget categories and subcategories to help users better organize their expenses and monitor their spending habits.
- Implement a feature to send users alerts or notifications when they are nearing their budget limits or when they have achieved their savings goals.
- Implementing real time multiple currencies and currency conversion by using some sort of API
- Introduce the ability to manage recurring income and expenses, such as monthly bills or salaries, to automate the tracking process and reduce manual data entry.
- Incorporate data visualization features, such as graphs and charts, to help users better understand their financial trends and make informed decisions.








