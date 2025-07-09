# 💸 Personal Expense Tracker
A Java-based console application that helps users track, manage, and analyze daily expenses. This project applies OOP, collections, file handling, exception handling, and JDBC to deliver a practical expense management solution.

🚀 Features
✅ Add New Expense – Date, amount, category, and description

🔍 View All Expenses – Display expenses in a table format

✏️ Edit Expense – Update date, category, or amount

❌ Delete Expense – Remove an entry by ID

📊 Total Summary – View total amount spent 

💾 Data Storage – PostgreSQL database + optional file handling

🛠️ Technologies Used
Technology	Purpose
💻 Core Java	Business logic using OOP
🧩 JDBC	Connects to PostgreSQL
🛢️ PostgreSQL	Stores all expense records
📁 Maven	Dependency management
🧰 Eclipse/IntelliJ	IDE for development & testing

📦 Prerequisites
Java JDK 8+

Apache Maven

PostgreSQL (with expenses table)

IDE: Eclipse / IntelliJ

JDBC Driver (postgresql-connector-java)

🧾 Database Schema (PostgreSQL)

CREATE TABLE expenses (
    id SERIAL PRIMARY KEY,
    expense_date DATE NOT NULL,
    amount NUMERIC(10, 2) NOT NULL,
    category VARCHAR(50),
    note TEXT
);

📂 Project Structure (Maven)
ExpenseTracker/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── com/tracker/
        │       ├── Main.java
        |       ├── DBConnection.java
        │       ├── model/Expense.java
        │       ├── dao/ExpenseDAO.java
        │       
        └── resources/     

# Dependecies
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.7.7</version>
        </dependency>
![image](https://github.com/user-attachments/assets/cfff2f11-3cee-475b-9b2c-fc2572ec9d0d)
![image](https://github.com/user-attachments/assets/542c79c0-41ad-4b9d-8dbf-09dd2fa0e6f0)
![image](https://github.com/user-attachments/assets/304c7beb-5cfb-414a-8eea-aadadc1e1159)




👩‍💻 Author
Name: Anjali Masali
📫 GitHub: @anjalimasali2007
Email: anjalimasali05@gmail.com



