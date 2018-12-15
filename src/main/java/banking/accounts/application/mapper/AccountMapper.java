package banking.accounts.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import banking.accounts.application.dto.BankAccountDTO;

public class AccountMapper implements RowMapper<BankAccountDTO> {

	@Override
	public BankAccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BankAccountDTO account = new BankAccountDTO();
		account.setId(rs.getBigDecimal("account_id"));
		account.setNumber(rs.getString("number"));
		account.setBalance(rs.getBigDecimal("balance"));
		account.setIsLocked(rs.getBoolean("locked"));
		account.setCustomerId(rs.getBigDecimal("customer_id"));
		return account;
	}

}