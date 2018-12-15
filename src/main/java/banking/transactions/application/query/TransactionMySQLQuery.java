package banking.transactions.application.query;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import banking.transactions.application.TransactionQuery;
import banking.transactions.application.dto.BankTransactionDto;
@Repository
@Transactional
public class TransactionMySQLQuery  implements TransactionQuery{
	//TODO TEMINIAR LA QUEY
	@Override
	public List<BankTransactionDto> getTransactionsByCustomer(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
