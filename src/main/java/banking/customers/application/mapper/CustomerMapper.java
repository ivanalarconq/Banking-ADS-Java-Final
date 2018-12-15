package banking.customers.application.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import banking.customers.application.dto.CustomerDto;

public class CustomerMapper implements RowMapper<CustomerDto> {

	@Override
	public CustomerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomerDto user = new CustomerDto();
        user.setId(rs.getInt("customer_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setIdentityDocument(rs.getString("identity_document"));
        user.setIsActive(rs.getBoolean("active")); 
        return user;
	}

}
