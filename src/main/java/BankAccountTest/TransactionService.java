package BankAccountTest;

/**
 * Created with IntelliJ IDEA.
 * User: All_in_one
 * Date: 6/24/13
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionService
{
    private static TransactionDAO transactionDAO;
    public static void setupData(TransactionDAO transactionDAO)
    {
        TransactionService.transactionDAO = transactionDAO;
    }

    public static Transaction transactionDeposit(String numberAcc, long timestamp, double amount, String deposit)
    {
        Transaction transaction = new Transaction(numberAcc, timestamp, amount, deposit);//To change body of created methods use File | Settings | File Templates.
        TransactionDAO.saveTransaction(transaction);
        return transaction;
    }
}
