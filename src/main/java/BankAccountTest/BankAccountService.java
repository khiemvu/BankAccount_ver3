package BankAccountTest;

/**
 * Created with IntelliJ IDEA.
 * User: All_in_one
 * Date: 6/24/13
 * Time: 5:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountService {
    private static BankAccountDAO bankAccountDAO;
    public static void setData(BankAccountDAO bankAccountDAO) {
        BankAccountService.bankAccountDAO = bankAccountDAO;
    }

    public static BankAccount openAccount(String accNumber, long time) {
        BankAccount bankAccount = new BankAccount(accNumber, time);
        bankAccountDAO.saveAccount(bankAccount);
        return bankAccount;//To change body of created methods use File | Settings | File Templates.
    }

    public static BankAccount getInfo(String numberAcc) {
        BankAccount bankAccount = bankAccountDAO.getInfo(numberAcc);
        return bankAccount;  //To change body of created methods use File | Settings | File Templates.
    }

    public static BankAccount transaction(String numberAcc, double amount, String des) {
        BankAccount bankAccount = bankAccountDAO.getInfo(numberAcc);
        bankAccount.setBalance(bankAccount.getBalance()+ amount);
        bankAccountDAO.saveAccount(bankAccount);
        return bankAccount;  //To change body of created methods use File | Settings | File Templates.
    }
}
