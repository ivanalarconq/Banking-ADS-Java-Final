package banking.users.domain.entity;

import java.io.Serializable;
import java.util.Set;

import banking.common.domain.entity.BaseEntity;

public class User extends BaseEntity implements Serializable {
	private String name;
	private String password;

	private String firstName;
	private String lastName;
	private char gender;
	private String emailAddress;
	private Boolean isActive;

	private Set<UserClaim> claims;

	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Set<UserClaim> getClaims() {
		return claims;
	}

	public void setClaims(Set<UserClaim> claims) {
		this.claims = claims;
	}

}
