package banking.transactions.application;

import banking.transactions.application.dto.RequestBankTransferDto;

public interface TransactionApplicationService {

	void performTransfer(RequestBankTransferDto requestBankTransferDto) throws Exception;

}