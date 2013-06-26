package BankAccountTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
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
    @Test
    public void testGetAllTransaction(){
        for(int i = 0; i < 3; i++)
        {
            TransactionService.doTransaction("0123456789", 1000L + i, 100+ i, "deposit");
        }

        ArgumentCaptor<Transaction> transactionArgument = ArgumentCaptor.forClass(Transaction.class);
        List<Transaction> transactionList = transactionArgument.getAllValues();
        when(transactionDAO.getAllTransaction("0123456789")).thenReturn(transactionList);
        when(timestamp.getTime()).thenReturn(1000L);
        assertEquals(100, transactionList.get(0).getBalance(), 0.01);
        assertEquals(timestamp.getTime(), transactionList.get(0).getTime());

    }
}
