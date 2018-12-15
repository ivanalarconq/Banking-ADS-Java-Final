package banking.accounts.application;

import banking.accounts.application.dto.BankAccountDTO;

public interface AccountApplicationService {

	void create(long customerId, BankAccountDTO bankAccountDto) throws Exception;

}