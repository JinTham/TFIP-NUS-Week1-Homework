package HomeWorkWeek1;

    import java.security.SecureRandom;
    import java.util.ArrayList;
    import java.util.List;

public class FixedDepositAccount {
    private String name;
    private String accNum;
    private Float balance;
    private List<String> transaction = new ArrayList<>();
    private boolean closed = false;
    private String createdDate;
    private String closedDate;
    private Float interest = 0.03F;
    private int duration = 6;
    private boolean interestChg = true;
    private boolean durationChg = true;
    //Constructor
    public FixedDepositAccount(String name, String createdDate, Float balance){
        this.name = name;
        this.createdDate = createdDate;
        this.balance = balance;
        SecureRandom rand = new SecureRandom();
        this.accNum = String.format("FD-%d",rand.nextInt(9000)+1000);
    }
    public FixedDepositAccount(String name, String createdDate, Float balance, Float interest){
        this.name = name;
        this.createdDate = createdDate;
        this.balance = balance;
        this.interest = interest;
        SecureRandom rand = new SecureRandom();
        this.accNum = String.format("FD-%d",rand.nextInt(9000)+1000);
    }
    public FixedDepositAccount(String name, String createdDate, Float balance, Float interest, int duration){
        this.name = name;
        this.createdDate = createdDate;
        this.balance = balance;
        this.interest = interest;
        this.duration = duration;
        SecureRandom rand = new SecureRandom();
        this.accNum = String.format("FD-%d",rand.nextInt(9000)+1000);
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
        return "NOP";
    }
    public String withdraw(int amount, String datetime) throws IllegalArgumentException{
        return "NOP";
    }
    public Float getFinalBalance(){
        return this.balance*(1+this.interest);
    }
    public void updateInterest(Float newInterest){
        if (interestChg){
            this.interest = newInterest;
            interestChg = false;
        }
    }
    public void updateDuration(int newDuration){
        if (durationChg){
            this.duration = newDuration;
            durationChg = false;
        }
    }
}
