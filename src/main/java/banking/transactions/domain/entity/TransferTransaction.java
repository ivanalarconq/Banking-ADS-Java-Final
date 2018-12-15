package banking.transactions.domain.entity;

import java.util.List;

import banking.operations.domain.entity.Operation;
import banking.transactions.domain.enumeration.TransactionType;

public class TransferTransaction extends Transaction {

	public TransferTransaction(List<Operation> operations) {
		setTransactionType(TransactionType.TRANSFER.getCode());
		this.operations = operations;
	}

}
