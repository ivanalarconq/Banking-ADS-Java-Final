package banking.accounts.application;

import java.util.List;

import banking.accounts.application.dto.BankAccountDTO;
import banking.common.application.query.exception.EmptyResultException;

public interface AccountQuery {

	List<BankAccountDTO> get(long customerId) throws EmptyResultException;

}