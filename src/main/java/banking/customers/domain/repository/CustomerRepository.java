package banking.customers.domain.repository;

import java.util.List;

import banking.common.domain.repository.BaseRepository;
import banking.customers.domain.entity.Customer;

public interface CustomerRepository extends BaseRepository<Customer> { 
	public List<Customer> get(int page, int pageSize); 
	public Customer get(long customerId);
}
