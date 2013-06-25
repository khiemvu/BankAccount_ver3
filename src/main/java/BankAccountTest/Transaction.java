package BankAccountTest;
/**
 * Created with IntelliJ IDEA.
 * User: All_in_one
 * Date: 6/24/13
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction
{
    public String des;
    public Double balance;
    public Long timestamp;
    public String numAcc;

    public Transaction(String numberAcc, long timestamp, double balance, String des)
    {
        this.numAcc = numberAcc;
        this.timestamp = timestamp;
        this.balance = balance;
        this.des = des;
        //To change body of created methods use File | Settings | File Templates.
    }

    public long getTime()
    {
        return this.timestamp;  //To change body of created methods use File | Settings | File Templates.
    }

    public double getBalance()
    {
        return this.balance;  //To change body of created methods use File | Settings | File Templates.
    }
}
