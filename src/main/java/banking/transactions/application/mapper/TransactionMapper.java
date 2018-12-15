package banking.transactions.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import banking.transactions.application.dto.BankTransactionDto;

public class TransactionMapper implements RowMapper<BankTransactionDto> {

	@Override
	public BankTransactionDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		BankTransactionDto accountDto   = new BankTransactionDto(); 
		 
        return accountDto;
	}

}
