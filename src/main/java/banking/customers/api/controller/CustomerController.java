package banking.customers.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import banking.common.api.controller.ResponseHandler;
import banking.common.application.query.exception.EmptyResultException;
import banking.customers.application.CustomerApplicationService;
import banking.customers.application.CustomerQuery;
import banking.customers.application.dto.CustomerDto;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
	@Autowired
	CustomerApplicationService customerApplicationService;

	@Autowired
	CustomerQuery customerQuery;

	@Autowired
	ResponseHandler responseHandler;

	@RequestMapping(path = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody CustomerDto customerDto) throws Exception {
		try {
			customerDto = customerApplicationService.create(customerDto);
			return new ResponseEntity<Object>(customerDto, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch (Exception ex) {
			return this.responseHandler.getAppExceptionResponse();
		}
	}

	// TODO CAMBIAR POR QUERY
	@ResponseBody
	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Object> getPaginated(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize) {
		try {
			List<CustomerDto> customers = customerApplicationService.get(page, pageSize);
			return this.responseHandler.getDataResponse(customers, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
	}

	@ResponseBody
	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Object> getCustomer(@PathVariable(value = "id", required = false) long id) {
		try {
			CustomerDto customerDto = customerApplicationService.getById(id);
			return this.responseHandler.getDataResponse(customerDto, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody CustomerDto customerDto) throws Exception {
		try {
			customerDto = customerApplicationService.update(customerDto);
			return new ResponseEntity<Object>(customerDto, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch (Exception ex) {
			return this.responseHandler.getAppExceptionResponse();
		}
	}

	@ResponseBody
	@RequestMapping(path = "/getByIdentityDoc/{document}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Object> getCustomerByIdentityDocument(
			@PathVariable(value = "document", required = false) String identityDocument) {
		try {
			CustomerDto customerDto = customerQuery.findByIdentityDocument(identityDocument);
			return this.responseHandler.getDataResponse(customerDto, HttpStatus.OK);
		} catch (EmptyResultException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
	}

	@RequestMapping(path = "/getByLastName/{query}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<Object> getCustomer(@PathVariable(value = "query", required = false) String lastName) {
		try {
			if (lastName.length() < 3) {
				return this.responseHandler.getAppCustomErrorResponse("Mínimo 3 caracteres.");
			}
			List<CustomerDto> customerDto = customerQuery.findByLastName(lastName);
			return this.responseHandler.getDataResponse(customerDto, HttpStatus.OK);
		} catch (EmptyResultException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
	}

}
