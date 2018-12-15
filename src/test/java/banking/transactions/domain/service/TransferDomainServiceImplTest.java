package banking.transactions.domain.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import banking.accounts.domain.entity.Account;
import banking.transactions.domain.TransferDomainService;

@ContextConfiguration({ "classpath:test-spring-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TransferDomainServiceImplTest {

    @Autowired
	private TransferDomainService transferDomainService;
    private String originBankAccountNumber = "123-456-001";
    private String destinationBankAccountNumber = "123-456-002";

	@Before
	public void setUp() {
	}

	private Account createAccount(String number, BigDecimal balance) {
		Account bankAccount = new Account();
		bankAccount.setBalance(balance);
		bankAccount.setNumber(number);
		return bankAccount;
	}

	@Test
	public void performTransferSuccess() throws Exception {
		Account originBankAccount = createAccount(originBankAccountNumber, new BigDecimal(100));
		Account destinationBankAccount = createAccount(destinationBankAccountNumber, new BigDecimal(10));
		transferDomainService.performTransfer(originBankAccount, destinationBankAccount, new BigDecimal(10));
		assertEquals(new BigDecimal(90), originBankAccount.getBalance());
		assertEquals(new BigDecimal(20), destinationBankAccount.getBalance());
	}

	@Test(expected = IllegalArgumentException.class)
	public void performTransferErrorSameAccount() throws Exception {
		Account originBankAccount = createAccount(originBankAccountNumber, new BigDecimal(100));
		Account destinationBankAccount = createAccount(originBankAccountNumber, new BigDecimal(10));
		transferDomainService.performTransfer(originBankAccount, destinationBankAccount, new BigDecimal(10));
	}

	@Test(expected = IllegalArgumentException.class)
	public void performTransferErrorEmptyAccount() throws Exception {
		Account originBankAccount = null;
		Account destinationBankAccount = null;
		transferDomainService.performTransfer(originBankAccount, destinationBankAccount, new BigDecimal(10));

	}

	@Test(expected = IllegalArgumentException.class)
	public void performTransferErrorLockedDestinationAccount() throws Exception {
		Account originBankAccount = createAccount(originBankAccountNumber, new BigDecimal(100));
		Account destinationBankAccount = createAccount(destinationBankAccountNumber, new BigDecimal(10));
		destinationBankAccount.lock();
		transferDomainService.performTransfer(originBankAccount, destinationBankAccount, new BigDecimal(10));
	}

	@Test(expected = IllegalArgumentException.class)
	public void performTransferErrorNoMoneyOriginAccount() throws Exception {
		Account originBankAccount = createAccount(originBankAccountNumber, new BigDecimal(5));
		Account destinationBankAccount = createAccount(destinationBankAccountNumber, new BigDecimal(10));
		transferDomainService.performTransfer(originBankAccount, destinationBankAccount, new BigDecimal(10));
	}

	@Test(expected = IllegalArgumentException.class)
	public void performTransferErrorNegativeTransference() throws Exception {
		Account originBankAccount = createAccount(originBankAccountNumber, new BigDecimal(5));
		Account destinationBankAccount = createAccount(destinationBankAccountNumber, new BigDecimal(10));
		transferDomainService.performTransfer(originBankAccount, destinationBankAccount, new BigDecimal(-10));
	}
}
