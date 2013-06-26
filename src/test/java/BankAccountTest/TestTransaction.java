package BankAccountTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.Assert.assertEquals;
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
    @Mock private Date timestamp;
    @Mock private TransactionDAO transactionDAO;
    @Before
    public void initData(){
        MockitoAnnotations.initMocks(this);
        TransactionService.setupData(transactionDAO);
    }
    @Test
    public void testSaveTimestampWhenDoDeposit(){
        when(timestamp.getTime()).thenReturn(1000L);

        TransactionService.doTransaction("0123456789", 1000L, 100, "deposit");

        ArgumentCaptor<Transaction> transactionRecord = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionDAO).saveTransaction(transactionRecord.capture());

        assertEquals(timestamp.getTime(), transactionRecord.getValue().getTime());
    }
    @Test
    public void testSavetimestampWhenDoWithdraw(){
        when(timestamp.getTime()).thenReturn(1000L);

        TransactionService.doTransaction("0123456789", 1000L, 10, "withdraw");

        ArgumentCaptor<Transaction> transactionRecord = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionDAO).saveTransaction(transactionRecord.capture());
        assertEquals(timestamp.getTime(), transactionRecord.getValue().getTime());
        assertEquals(10, transactionRecord.getValue().getBalance(), 0.01);
    }
}
