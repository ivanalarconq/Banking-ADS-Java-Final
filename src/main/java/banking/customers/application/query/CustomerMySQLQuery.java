package banking.customers.application.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.common.application.query.exception.EmptyResultException;
import banking.customers.application.CustomerQuery;
import banking.customers.application.dto.CustomerDto;
import banking.customers.application.mapper.CustomerMapper;
import banking.customers.constants.QueryConstants;

@Transactional
@Repository
public class CustomerMySQLQuery implements CustomerQuery {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional(readOnly = true)
	public CustomerDto findByIdentityDocument(String identityDocument) throws EmptyResultException {
		try {
			String query = ""
					+ "SELECT customer_id, first_name,last_name,identity_document, active "
					+ "FROM customer where identity_document like ?";
			return (CustomerDto) jdbcTemplate.queryForObject(query, new Object[] { identityDocument },
					new CustomerMapper());
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException(QueryConstants.CUSTOMER_DATA_NOT_FOUND);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<CustomerDto> findByLastName(String lastname) throws EmptyResultException {
		try {
			String query = ""
					+ "SELECT customer_id , first_name,last_name,identity_document, active FROM customer  where last_name like ?";
			return jdbcTemplate.query(query, new CustomerMapper(),
					new Object[] { "%" + lastname + "%"});
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException(QueryConstants.CUSTOMER_DATA_NOT_FOUND);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<CustomerDto> getPaginated(int page, int pageSize) {
		// TODO hay que trae paginado
		return null;
	}

}
