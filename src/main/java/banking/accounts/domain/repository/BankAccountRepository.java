package banking.accounts.domain.repository;

import java.util.List;

import banking.accounts.domain.entity.Account;
import banking.common.domain.repository.BaseRepository;

public interface BankAccountRepository extends BaseRepository<Account>{
	Account findByNumber(String accountNumber) throws Exception;
	Account findByNumberLocked(String accountNumber) throws Exception;
	List<Account> get(long customerId); 
}
