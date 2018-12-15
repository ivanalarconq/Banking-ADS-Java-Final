package banking.customers.application;

import java.util.List;

import banking.common.application.query.exception.EmptyResultException;
import banking.customers.application.dto.CustomerDto;

public interface CustomerQuery {

	CustomerDto findByIdentityDocument(String identityDocument)  throws EmptyResultException;

	List<CustomerDto> findByLastName(String lastName)  throws EmptyResultException;
	
	public List<CustomerDto> getPaginated(int page, int pageSize);

}