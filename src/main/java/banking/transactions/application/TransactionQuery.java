package banking.transactions.application;

import java.util.List;

import banking.transactions.application.dto.BankTransactionDto;

public interface TransactionQuery {

	public List<BankTransactionDto> getTransactionsByCustomer(long id);
}
