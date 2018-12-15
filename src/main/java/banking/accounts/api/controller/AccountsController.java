package banking.accounts.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import banking.accounts.application.AccountApplicationService;
import banking.accounts.application.AccountQuery;
import banking.accounts.application.dto.BankAccountDTO;
import banking.common.api.controller.ResponseHandler;

@RestController
@RequestMapping("api/customers/{customerId}/accounts")
public class AccountsController {
	@Autowired
	AccountApplicationService accountApplicationService;
	
	@Autowired
	AccountQuery accountQuery;
	
	@Autowired
	ResponseHandler responseHandler;
	
	@RequestMapping(path = "", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@PathVariable("customerId") long customerId, @RequestBody BankAccountDTO bankAccountDto) throws Exception {
        try {
        	accountApplicationService.create(customerId, bankAccountDto);
            return new ResponseEntity<Object>(bankAccountDto, HttpStatus.OK);
        } catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
    }
	
	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> get(@PathVariable("customerId") long customerId) {
        try {
            List<BankAccountDTO> customers = accountQuery.get(customerId);
            return this.responseHandler.getDataResponse(customers, HttpStatus.OK);
        } catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
    }
}
