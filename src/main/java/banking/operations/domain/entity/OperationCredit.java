package banking.operations.domain.entity;

import java.math.BigDecimal;

import banking.accounts.domain.entity.Account;
import banking.transactions.domain.enumeration.OperationType;

public class OperationCredit extends Operation {

	public OperationCredit(Account account, BigDecimal amount) {
		super(account, amount);
		this.setOperationType( OperationType.CREDIT.getCode());
	}

}
