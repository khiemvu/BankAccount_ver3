package BankAccountTest;

/**
 * Created with IntelliJ IDEA.
 * User: All_in_one
 * Date: 6/24/13
 * Time: 5:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    private String numberAcc;
    private double balance;

    BankAccount(String numberAcc){
        this.numberAcc = numberAcc;
        this.balance = 0;
    }
    public double getBalance() {
        return this.balance;  //To change body of created methods use File | Settings | File Templates.
    }

    public void setBalance(double balance) {
        this.balance = balance;//To change body of created methods use File | Settings | File Templates.
    }
}
