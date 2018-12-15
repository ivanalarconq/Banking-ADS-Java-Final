package banking.accounts.application.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.accounts.application.AccountQuery;
import banking.accounts.application.dto.BankAccountDTO;
import banking.accounts.application.mapper.AccountMapper;
import banking.common.application.query.exception.EmptyResultException;

@Transactional
@Repository
public class AccountMySQLQuery implements AccountQuery {

	@Autowired
	private JdbcTemplate jdbcTemplate;
    
	//TODO MANDAR MENSAJE A PROPERTIES O CONSTANTES
	@Transactional(readOnly = true)
	@Override
	public List<BankAccountDTO> get(long customerId) throws EmptyResultException {
		
		try {
			String query = "" 
					+" SELECT"  
					+" account_id,"
					+" number,"
				    +" balance,"
				    +" locked,"
				    +" customer_id"
				    +" FROM account"
				    +" WHERE customer_id = ?";
			
			return  jdbcTemplate.query(query, new AccountMapper(), new Object[] { customerId });
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException("Customer data not found");
		} 
    }
}
