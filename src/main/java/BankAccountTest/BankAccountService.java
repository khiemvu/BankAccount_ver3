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

    public static BankAccount openAccount(String accNumber) {
        BankAccount bankAccount = new BankAccount(accNumber);
        bankAccountDAO.saveAccount(bankAccount);
        return bankAccount;//To change body of created methods use File | Settings | File Templates.
    }
}
