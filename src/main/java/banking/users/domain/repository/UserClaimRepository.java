package banking.users.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import banking.users.domain.entity.UserClaim;

public interface UserClaimRepository {
	public List<UserClaim> findByUserId(BigDecimal userId) throws Exception;
}
