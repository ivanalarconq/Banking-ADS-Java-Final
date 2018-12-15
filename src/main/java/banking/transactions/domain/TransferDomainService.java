package banking.transactions.domain;

import java.math.BigDecimal;

import banking.accounts.domain.entity.Account;
import banking.transactions.domain.entity.Transaction;

public interface TransferDomainService {

	void performTransfer(Account originAccount, Account destinationAccount, BigDecimal amount)
			throws IllegalArgumentException;

}