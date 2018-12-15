package banking.customers.domain.entity;

import java.io.Serializable;
import java.util.Set;

import banking.accounts.domain.entity.Account;
import banking.common.application.Notification;
import banking.common.domain.entity.BaseEntity;

public class Customer extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private Boolean isActive;
	private String identityDocument;
	private Set<Account> accounts;

	public Customer() {
	}

	public String getFullName() {
		return String.format("%s, %s", this.lastName, this.firstName);
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Set getAccounts() {
		return accounts;
	}

	public void setAccounts(Set accounts) {
		this.accounts = accounts;
	}

	public String getIdentityDocument() {
		return identityDocument;
	}

	public void setIdentityDocument(String identityDocument) {
		this.identityDocument = identityDocument;
	}

	private void validateCustomer(Notification notification, Customer customer) {
		if (customer == null) {
			notification.addError("Customer doesn't exist");
			return;
		}
		if (customer.getIsActive() == null || !customer.getIsActive()) {
			notification.addError("Customer Inactive");
			return;
		}
	}
}
