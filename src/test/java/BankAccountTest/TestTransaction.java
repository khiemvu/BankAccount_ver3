package BankAccountTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: All_in_one
 * Date: 6/24/13
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestTransaction
{
    @Mock public Date timestamp;
    @Mock public TransactionDAO transactionDAO;
    @Before
    public void initData(){
        MockitoAnnotations.initMocks(this);
        TransactionService.setupData(transactionDAO);
    }
    @Test
    public void testSaveTimestampWhenDoDeposit(){
        when(timestamp.getTime()).thenReturn(1000L);

        TransactionService.transactionDeposit("0123456789", 1000L, 100, "deposit");

        ArgumentCaptor<Transaction> transactionRecord = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionDAO).saveTransaction(transactionRecord.capture());

        //assertEquals(1000L, transactionRecord.getValue().getTime());
    }
    @Test
    public void testSavetimestampWhenDoWithdraw(){
        when(timestamp.getTime()).thenReturn(1000L);

        TransactionService.transactionDeposit("0123456789", 500L, 100, "deposit");
        TransactionService.transactionWithdraw("0123456789", 1000L, 10, "withdraw");

        ArgumentCaptor<Transaction> transactionRecord = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionDAO).saveTransaction(transactionRecord.capture());
        List<Transaction> listTransaction = transactionRecord.getAllValues();
        assertEquals(1000L, listTransaction.get(1).getTime());
    }
}
