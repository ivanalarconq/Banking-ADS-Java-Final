package banking.customers.application.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import banking.common.application.Notification;
import banking.customers.application.CustomerApplicationService;
import banking.customers.application.dto.CustomerDto;
import banking.customers.domain.entity.Customer;
import banking.customers.domain.repository.CustomerRepository;

@Service
public class CustomerApplicationServiceImpl implements CustomerApplicationService {
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	private ModelMapper mapper;

	@Value("${maxPageSize}")
	private int maxPageSize;

	 
	@Override
	public CustomerDto create(CustomerDto customerDto) {
		Customer customer = mapper.map(customerDto, Customer.class);
		customer.setIsActive(true);
		customer = customerRepository.save(customer);
		customerDto = mapper.map(customer, CustomerDto.class);
		return customerDto;
	}

	 
	@Override
	public List<CustomerDto> get(int page, int pageSize) {
		Notification notification = this.getValidation(page, pageSize);
		if (notification.hasErrors()) {
			throw new IllegalArgumentException(notification.errorMessage());
		}
		List<Customer> customers = this.customerRepository.get(page, pageSize);
		List<CustomerDto> customersDto = mapper.map(customers, new TypeToken<List<CustomerDto>>() {
		}.getType());
		return customersDto;
	}
	//TODO MANDAR MENSAJE A PROPERTIES O CONSTANTES
	private Notification getValidation(int page, int pageSize) {
		Notification notification = new Notification();
		if (pageSize > maxPageSize) {
			notification.addError("Page size can not be greater than 100");
		}
		return notification;
	}

	 
	@Override
	public CustomerDto getById(long id) {
		Customer customer = customerRepository.get(id);
		CustomerDto customerDto = mapper.map(customer, CustomerDto.class);
		return customerDto;
	}

	 
	@Override
	public CustomerDto update(CustomerDto customerDto) {
		Notification notification = this.updateValidation(customerDto);
		if (notification.hasErrors()) {
			throw new IllegalArgumentException(notification.errorMessage());
		}

		Customer customer = mapper.map(customerDto, Customer.class);
		customer.setIsActive(true);
		customer = this.customerRepository.save(customer);
		customerDto = mapper.map(customer, CustomerDto.class);
		return customerDto;
	}
	//TODO MANDAR MENSAJE A PROPERTIES O CONSTANTES
	private Notification updateValidation(CustomerDto customerDto) {
		Notification notification = new Notification();

		if (!customerDto.isActive()) {
			notification.addError("Customer Inactive");
			return notification;
		}
		if (StringUtils.isEmpty(customerDto.getFirstName())) {
			notification.addError("First Name can't be blank");
			return notification;
		}
		if (StringUtils.isEmpty(customerDto.getLastName())) {
			notification.addError("Last Name can't be blank");
			return notification;
		}
		if (StringUtils.isEmpty(customerDto.getIdentityDocument())) {
			notification.addError("Identity Document can't be blank");
			return notification;
		}
		Customer customer = null;
		customer = this.customerRepository.get(customerDto.getId());
		if (customer == null) {
			notification.addError("Customer doesn't exist");
			return notification;
		}

		return notification;

	}
 
}
