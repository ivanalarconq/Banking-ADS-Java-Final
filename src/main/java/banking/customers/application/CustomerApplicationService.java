package banking.customers.application;

import java.util.List;

import banking.customers.application.dto.CustomerDto;

public interface CustomerApplicationService {

	CustomerDto create(CustomerDto customerDto);

	List<CustomerDto> get(int page, int pageSize);

	CustomerDto getById(long id);

	CustomerDto update(CustomerDto customerDto);

	

}