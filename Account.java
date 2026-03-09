package atmapp;

public class Account {
    private int accNo;
    private String name;
    private int pin;
    private double balance;

    public Account(int accNo, String name, int pin, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccNo() { return accNo; }
    public String getName() { return name; }
    public int getPin() { return pin; }
    public void setPin(int pin) { this.pin = pin; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}