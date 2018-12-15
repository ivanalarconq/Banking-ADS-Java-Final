package banking.customers.application.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import banking.accounts.application.dto.BankAccountDTO;

public class CustomerDto {
	private long id;
	private String firstName;
	private String lastName;
	private String identityDocument;
	private Boolean isActive;
	private Set<BankAccountDTO> bankAccountsDto;
	
	public CustomerDto() {
    }
	
	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getIdentityDocument() {
		return identityDocument;
	}
	
	public void setIdentityDocument(String identityDocument) {
		this.identityDocument = identityDocument;
	}
	
	@JsonProperty(value="isActive")
	public Boolean isActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Set<BankAccountDTO> getBankAccountsDto() {
		return bankAccountsDto;
	}

	public void setBankAccountsDto(Set<BankAccountDTO> bankAccountsDto) {
		this.bankAccountsDto = bankAccountsDto;
	}
}
