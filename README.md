A simple ATM system developed using Java and JDBC that performs basic banking operations through the command-line interface (CLI).
The application connects to a MySQL database to store and retrieve account information.
This project demonstrates the use of JDBC for database connectivity, SQL operations, and basic banking logic implementation in Java.
Features
User Authentication using PIN
Check Account Balance
Deposit Money
Withdraw Money
Create New Account
Data stored in MySQL database
Fully console-based (no GUI)
Technologies Used
Java
JDBC
MySQL
SQL
Command Line Interface (CLI)
Project Structure

ATM-JDBC-Application
│
├── src
│   ├── Main.java
│   ├── ATMOperations.java
│   ├── DatabaseConnection.java
│   └── User.java
│
├── database
│   └── atm_database.sql
│
└── README.md
Database Setup
Install MySQL.
Create a database:

Sql
CREATE DATABASE atm_system;
Create the accounts table:

Sql
CREATE TABLE accounts (
    account_number INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    pin INT,
    balance DOUBLE
);
Configure Database Connection
Update your database credentials in the Java file:
Copy code
Java
String url = "jdbc:mysql://localhost:3306/atm_system";
String user = "root";
String password = "your_password";
How to Run the Project
Clone the repository
git clone https://github.com/yourusername/java-jdbc-atm.git
Open the project in your preferred Java IDE.
Add the MySQL JDBC Driver.
Configure the database connection.
Run the Main.java file.
Example Console Output
===== ATM SYSTEM =====
1. Create Account
2. Login
3. Deposit Money
4. Withdraw Money
5. Check Balance
6. Exit
Learning Objectives
This project helps understand:
JDBC database connectivity
SQL operations using Java
Basic banking logic implementation
Console-based Java applications
License
This project is open-source and available for learning purposes.
