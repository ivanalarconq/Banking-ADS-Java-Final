package banking.accounts.application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.accounts.application.AccountApplicationService;
import banking.accounts.application.dto.BankAccountDTO;
import banking.accounts.domain.entity.Account;
import banking.accounts.domain.repository.BankAccountRepository;
import banking.common.application.Notification;
import banking.customers.domain.entity.Customer;
import banking.customers.domain.repository.CustomerRepository;

@Service
public class AccountApplicationServiceImpl implements AccountApplicationService {
	@Autowired
	BankAccountRepository bankAccountRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper mapper;
    
	@Override
	public void create(long customerId, BankAccountDTO bankAccountDto) throws Exception {
		Notification notification = this.createValidation(bankAccountDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		Account bankAccount = mapper.map(bankAccountDto, Account.class);
		bankAccount.setIsLocked(false);
		Customer customer = customerRepository.get(customerId);
		bankAccount.setCustomer(customer);
		bankAccount = bankAccountRepository.save(bankAccount);
		bankAccountDto = mapper.map(bankAccount, BankAccountDTO.class);
    }
	//TODO MANDAR MENSAJE A PROPERTIES O CONSTANTES
	private Notification createValidation(BankAccountDTO bankAccountDto) throws Exception {
		Notification notification = new Notification();
		Account bankAccount = bankAccountRepository.findByNumber(bankAccountDto.getNumber());
		if (bankAccount != null) {
			notification.addError("BankAccount number is already registered");
		}
		return notification;
	}
	
	
}
