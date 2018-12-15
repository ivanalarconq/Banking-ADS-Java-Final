package banking.transactions.domain.entity;

import java.math.BigDecimal;
import java.util.List;

import banking.common.application.Notification;
import banking.common.domain.entity.BaseEntity;
import banking.operations.domain.entity.Operation;

public class Transaction extends BaseEntity {

	private int transactionType;
	protected List<Operation> operations;

	public Transaction() { 
	}
	public Notification transactionValidation(BigDecimal amount) {
		Notification notification = new Notification();
		this.validateAmount(notification, amount);
		this.validateDetails(notification, operations);
		return notification;
	}

	private void validateAmount(Notification notification, BigDecimal amount) {
		if (amount == null) {
			notification.addError("amount is missing");
			return;
		}
		if (amount.signum() <= 0) {
			notification.addError("The amount must be greater than zero");
			return;
		}
	}

	private void validateDetails(Notification notification, List<Operation> details) {
		if (details == null) {
			notification.addError("Operations are missing");
			return;
		}
	}

	public int getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

}
