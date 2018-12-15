package banking.transactions.domain.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import banking.accounts.domain.entity.Account;
import banking.common.application.Notification;
import banking.operations.domain.entity.Operation;
import banking.operations.domain.entity.OperationCredit;
import banking.operations.domain.entity.OperationDebit;
import banking.transactions.domain.TransferDomainService;
import banking.transactions.domain.entity.Transaction;
import banking.transactions.domain.entity.TransferTransaction;

@Service
public class TransferDomainServiceImpl implements TransferDomainService {
	 
	@Override
	public void performTransfer(Account originAccount, Account destinationAccount, BigDecimal amount)
			throws IllegalArgumentException {
		Notification notification = this.validation(originAccount, destinationAccount, amount);
		if (notification.hasErrors()) {
			throw new IllegalArgumentException(notification.errorMessage());
		}
		originAccount.withdrawMoney(amount);
		destinationAccount.depositMoney(amount);
	 
	}

	private Notification validation(Account originAccount, Account destinationAccount, BigDecimal amount) {
		Notification notification = new Notification();
		this.validateAmount(notification, amount);
		this.validateBankAccounts(notification, originAccount, destinationAccount);
		return notification;
	}

	private void validateAmount(Notification notification, BigDecimal amount) {
		if (amount == null) {
			notification.addError("amount is missing");
			return;
		}
		if (amount.signum() <= 0) {
			notification.addError("The amount must be greater than zero");
		}
	}

	private void validateBankAccounts(Notification notification, Account originAccount, Account destinationAccount) {
		if (originAccount == null || destinationAccount == null) {
			notification.addError("Cannot perform the transfer. Invalid data in bank accounts specifications");
			return;
		}
		if (originAccount.getNumber().equals(destinationAccount.getNumber())) {
			notification.addError("Cannot transfer money to the same bank account");
		}
	}
}
