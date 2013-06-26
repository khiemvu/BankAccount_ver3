package BankAccountTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: All_in_one
 * Date: 6/24/13
 * Time: 5:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestBankAccount {
    @Mock
    private BankAccountDAO bankAccountDAO;
    @Mock
    private Date timestamp;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        BankAccountService.setData(bankAccountDAO);
    }
    @Test
    public void testOpentAccount(){
        BankAccountService.openAccount("0123456789",100L);
        ArgumentCaptor<BankAccount> argument = ArgumentCaptor.forClass(BankAccount.class);
        verify(bankAccountDAO).saveAccount(argument.capture());
        assertEquals(0, argument.getValue().getBalance(), 0.01);
    }
    @Test
    public void testGetInfoAccount(){
        BankAccount bankAccount = BankAccountService.openAccount("0123456789", 100L);
        BankAccountService.getInfo("0123456789");

        when(bankAccountDAO.getInfo("0123456789")).thenReturn(bankAccount);
        verify(bankAccountDAO).getInfo("0123456789");
        assertEquals(0, bankAccount.getBalance(),0.01);
    }

    @Test
    public void testTransactionDepositAndWithdraw(){
        BankAccount bankAccount = BankAccountService.openAccount("0123456789", 100L);
        when(bankAccountDAO.getInfo("0123456789")).thenReturn(bankAccount);
        BankAccountService.transaction("0123456789", 100, "deposit");
        BankAccountService.transaction("0123456789", -50, "withdraw");

        ArgumentCaptor<BankAccount> argumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
        verify(bankAccountDAO, times(3)).saveAccount(argumentCaptor.capture());
        List<BankAccount> listAccount = argumentCaptor.getAllValues();
        assertEquals(50, listAccount.get(2).getBalance(), 0.01);
    }
    @Test
    public void testSaveTimestampWhenOpenAccount(){
        when(timestamp.getTime()).thenReturn(1000L);
        BankAccountService.openAccount("0123456789", 1000L);

        ArgumentCaptor<BankAccount> record = ArgumentCaptor.forClass(BankAccount.class);
        verify(bankAccountDAO).saveAccount(record.capture());

        assertEquals(timestamp.getTime(), record.getValue().getTime());
    }

}
