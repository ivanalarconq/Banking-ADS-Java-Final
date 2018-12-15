package banking.transactions.api.controller;

import java.util.List;

import org.omg.CORBA.TRANSACTION_MODE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import banking.accounts.application.dto.BankAccountDTO;
import banking.common.api.controller.ResponseHandler;
import banking.transactions.application.TransactionApplicationService;
import banking.transactions.application.TransactionQuery;
import banking.transactions.application.dto.BankTransactionDto;
import banking.transactions.application.dto.RequestBankTransferDto;

@RestController
@RequestMapping("api/transactions")
public class BankTransferController {
	@Autowired
	TransactionApplicationService transactionApplicationService;

	@Autowired
	TransactionQuery transactionQuery;

	@Autowired
	ResponseHandler responseHandler;

	// TODO MANDAR MENSAJE A PROPERTIES O CONSTANTES
	@RequestMapping(method = RequestMethod.POST, path = "/transfer", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> performTransfer(@RequestBody RequestBankTransferDto requestBankTransferDto)
			throws Exception {
		try {
			transactionApplicationService.performTransfer(requestBankTransferDto);
			return this.responseHandler.getResponse("Transfer done!", HttpStatus.CREATED);
		} catch (IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
	}

	// TODO 
	@RequestMapping(method = RequestMethod.GET, path = "", produces = "application/json; charset=UTF-8")
	@ResponseBody
	ResponseEntity<Object> get(@PathVariable("customerId") long customerId) {
		try {
			List<BankTransactionDto> bankTransactionDtos = transactionQuery.getTransactionsByCustomer(customerId);
			return this.responseHandler.getDataResponse(bankTransactionDtos, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
	}
}
