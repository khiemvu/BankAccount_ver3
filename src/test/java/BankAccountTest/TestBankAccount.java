package BankAccountTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        BankAccountService.setData(bankAccountDAO);
    }
    @Test
    public void testOpentAccount(){
        BankAccountService.openAccount("0123456789");
        ArgumentCaptor<BankAccount> argument = ArgumentCaptor.forClass(BankAccount.class);
        verify(bankAccountDAO).saveAccount(argument.capture());
        assertEquals(0, argument.getValue().getBalance(), 0.01);
    }
    @Test
    public void testGetInfoAccount(){
        BankAccount bankAccount = BankAccountService.openAccount("0123456789");
        BankAccountService.getInfo("0123456789");

        when(bankAccountDAO.getInfo("0123456789")).thenReturn(bankAccount);
        verify(bankAccountDAO).getInfo("0123456789");
        assertEquals(0, bankAccount.getBalance(),0.01);
    }
    @Test
    public void testTransactionDeposit(){
        BankAccount bankAccount = BankAccountService.openAccount("0123456789");
        when(bankAccountDAO.getInfo("0123456789")).thenReturn(bankAccount);
        BankAccountService.transactionDeposit("0123456789", 100, "deposit");

        ArgumentCaptor<BankAccount> argumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
        verify(bankAccountDAO, times(2)).saveAccount(argumentCaptor.capture());
        assertEquals(100, argumentCaptor.getValue().getBalance(), 0.01);
    }


}
