package banking.transactions.application.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import banking.accounts.domain.entity.Account;
import banking.accounts.domain.repository.BankAccountRepository;
import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.operations.domain.entity.Operation;
import banking.operations.domain.entity.OperationCredit;
import banking.operations.domain.entity.OperationDebit;
import banking.transactions.application.TransactionApplicationService;
import banking.transactions.application.dto.RequestBankTransferDto;
import banking.transactions.domain.TransferDomainService;
import banking.transactions.domain.entity.Transaction;
import banking.transactions.domain.entity.TransferTransaction;
import banking.transactions.domain.repository.TransactionRepository;

@Service
public class TransactionApplicationServiceImpl implements TransactionApplicationService {
	@Autowired
	private BankAccountRepository bankAccountRepository; 
	@Autowired
	private TransferDomainService transferDomainService;
	@Autowired
	private TransactionRepository transactionRepository; 
	 
	@Override
	@Transactional
	public void performTransfer(RequestBankTransferDto requestBankTransferDto) throws Exception {
		Notification notification = this.validation(requestBankTransferDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		Account originAccount = this.bankAccountRepository.findByNumberLocked(requestBankTransferDto.getFromAccountNumber());
		Account destinationAccount = this.bankAccountRepository.findByNumberLocked(requestBankTransferDto.getToAccountNumber());
		this.transferDomainService.performTransfer(originAccount, destinationAccount, requestBankTransferDto.getAmount());
		Transaction transaction = generateTransaction(originAccount, destinationAccount, requestBankTransferDto.getAmount());
 		this.bankAccountRepository.save(originAccount);
		this.bankAccountRepository.save(destinationAccount);
//		this.transactionRepository.save(transaction);
	}
	
	private Transaction generateTransaction(Account originAccount, Account destinationAccount, BigDecimal amount) {
		Operation credit = new OperationCredit(originAccount, amount);
		Operation debit = new OperationDebit(destinationAccount, amount);		
		List<Operation> operations = new ArrayList<>();
		operations.add(credit);
		operations.add(debit);
		return  new TransferTransaction(operations);
	}

	 
	
	private Notification validation(RequestBankTransferDto requestBankTransferDto) {
		Notification notification = new Notification();
		if (requestBankTransferDto == null || requestBankTransferDto.getFromAccountNumber().equals(RequestBodyType.INVALID.toString())) {
			notification.addError("Invalid JSON data in request body.");
		}
		return notification;
	}
}
