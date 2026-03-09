package atmapp;

import java.sql.*;
import java.util.*;

public class ATM {
    private static Scanner sc = new Scanner(System.in);
    private static List<Account> accountList = new ArrayList<>();

    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database connection failed!");
            return;
        }

        try {
            
            loadAccounts(conn);

            System.out.println("===== Welcome to ATM Machine =====");
            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();
            System.out.print("Enter PIN: ");
            int pin = sc.nextInt();

            Account acc = findAccount(accNo, pin);
            if (acc != null) {
                int choice;
                do {
                    System.out.println("\n1. Check Balance");
                    System.out.println("2. Deposit");
                    System.out.println("3. Withdraw");
                    System.out.println("4. Change PIN");
                    System.out.println("5. Exit");
                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();

                    switch (choice) {
                        case 1 -> System.out.println("Balance: ₹" + acc.getBalance());
                        case 2 -> deposit(acc, conn);
                        case 3 -> withdraw(acc, conn);
                        case 4 -> changePin(acc, conn);
                        case 5 -> System.out.println("Thank you!");
                        default -> System.out.println("Invalid choice!");
                    }
                } while (choice != 5);
            } else {
                System.out.println("Invalid Account or PIN!");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadAccounts(Connection conn) throws SQLException {
        String query = "SELECT * FROM accounts";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            accountList.add(new Account(
                rs.getInt("acc_no"),
                rs.getString("name"),
                rs.getInt("pin"),
                rs.getDouble("balance")
            ));
        }
    }

    private static Account findAccount(int accNo, int pin) {
        for (Account a : accountList) {
            if (a.getAccNo() == accNo && a.getPin() == pin) {
                return a;
            }
        }
        return null;
    }

    private static void deposit(Account acc, Connection conn) throws SQLException {
        System.out.print("Enter amount to deposit: ");
        double amt = sc.nextDouble();
        acc.setBalance(acc.getBalance() + amt);

        String query = "UPDATE accounts SET balance = ? WHERE acc_no = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setDouble(1, acc.getBalance());
        pst.setInt(2, acc.getAccNo());
        pst.executeUpdate();

        System.out.println("Deposit successful! New Balance: ₹" + acc.getBalance());
    }

    private static void withdraw(Account acc, Connection conn) throws SQLException {
        System.out.print("Enter amount to withdraw: ");
        double amt = sc.nextDouble();
        if (amt <= acc.getBalance()) {
            acc.setBalance(acc.getBalance() - amt);
            String query = "UPDATE accounts SET balance = ? WHERE acc_no = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setDouble(1, acc.getBalance());
            pst.setInt(2, acc.getAccNo());
            pst.executeUpdate();
            System.out.println("Withdrawal successful! New Balance: ₹" + acc.getBalance());
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    private static void changePin(Account acc, Connection conn) throws SQLException {
        System.out.print("Enter current PIN: ");
        int oldPin = sc.nextInt();
        if (oldPin == acc.getPin()) {
            System.out.print("Enter new PIN: ");
            int newPin = sc.nextInt();
            acc.setPin(newPin);

            String query = "UPDATE accounts SET pin = ? WHERE acc_no = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, newPin);
            pst.setInt(2, acc.getAccNo());
            pst.executeUpdate();

            System.out.println("PIN changed successfully!");
        } else {
            System.out.println("Incorrect current PIN!");
        }
    }
}