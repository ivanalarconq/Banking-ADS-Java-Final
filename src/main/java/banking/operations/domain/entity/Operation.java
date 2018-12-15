package banking.operations.domain.entity;

import java.math.BigDecimal;

import banking.accounts.domain.entity.Account;
import banking.common.domain.entity.BaseEntity;
import banking.transactions.domain.entity.Transaction;

public class Operation extends BaseEntity {

	protected Transaction transaction;
	protected Account account;
	private int operationType;
	protected BigDecimal amount;

	public Operation() { 
	}
	public Operation(Account account, BigDecimal amount) { 
		this.account = account;
		this.amount = amount; 
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getOperationType() {
		return operationType;
	}
	
	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
