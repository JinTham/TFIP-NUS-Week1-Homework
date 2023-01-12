package HomeWorkWeek1;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String name;
    private String accNum;
    private Float balance;
    private List<String> transaction = new ArrayList<>();
    private boolean closed = false;
    private String createdDate;
    private String closedDate;
    //Constructor
    public BankAccount(String name, String createdDate){
        this.name = name;
        this.createdDate = createdDate;
        this.balance = 0.00F;
        SecureRandom rand = new SecureRandom();
        this.accNum = String.format("%d",rand.nextInt(9000)+1000);
    }
    public BankAccount(String name, String createdDate, Float initBalance){
        this.name = name;
        this.createdDate = createdDate;
        this.balance = initBalance;
        SecureRandom rand = new SecureRandom();
        this.accNum = String.format("%d",rand.nextInt(9000)+1000);
    }
    //Getters and Setters
    public String getName() {return name;}
    public String getAccNum() {return accNum;}
    public Float getBalance() {return balance;}
    public void setBalance(Float balance) {this.balance = balance;}
    public List<String> getTransaction() {return transaction;}
    public void setTransaction(List<String> transaction) {this.transaction = transaction;}
    public boolean isClosed() {return closed;}
    public void setClosed(boolean closed) {this.closed = closed;}
    public String getCreatedDate() {return createdDate;}
    public void setCreatedDate(String createdDate) {this.createdDate = createdDate;}
    public String getClosedDate() {return closedDate;}
    public void setClosedDate(String closedDate) {this.closedDate = closedDate;}
    
    //Methods
    public String deposit(int amount, String datetime) throws IllegalArgumentException{
        if (amount<=0){
            return "Incorrect amount";
        }
        this.balance += amount;
        this.transaction.add(String.format("Deposit $%d at %s",amount,datetime));
        return String.format("$%d successfully deposited",amount);
    }
    public String withdraw(int amount, String datetime) throws IllegalArgumentException{
        if (amount<=0){
            return "Incorrect amount";
        }
        this.balance -= amount;
        this.transaction.add(String.format("Withdraw $%d at %s",amount,datetime));
        return String.format("$%d successfully withdrawn",amount);
    }

}
