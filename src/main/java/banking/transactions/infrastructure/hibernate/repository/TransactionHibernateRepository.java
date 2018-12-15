package banking.transactions.infrastructure.hibernate.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.operations.domain.entity.Operation;
import banking.transactions.domain.entity.Transaction;
import banking.transactions.domain.repository.TransactionRepository;

@Transactional
@Repository
public class TransactionHibernateRepository extends BaseHibernateRepository<Transaction>  implements TransactionRepository   {
	 
	public Transaction save(Transaction transaction) {
		for ( Operation operation : transaction.getOperations()) {
			operation.setTransaction(transaction); 
		}
		return super.save(transaction);
	}
}
