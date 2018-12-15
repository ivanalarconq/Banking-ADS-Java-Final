package banking.accounts.infrastructure.hibernate.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.accounts.domain.entity.Account;
import banking.accounts.domain.repository.BankAccountRepository;
import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;

@Transactional
@Repository
@SuppressWarnings("deprecation")
public class BankAccountHibernateRepository extends BaseHibernateRepository<Account> implements BankAccountRepository {
	
	@Override
	public Account findByNumber(String accountNumber) throws Exception {
		Account bankAccount = null;
		Criteria criteria = getSession().createCriteria(Account.class);
		criteria.add(Restrictions.eq("number", accountNumber));
		bankAccount = (Account) criteria.uniqueResult();
		return bankAccount;
	}

	@Override
	public Account findByNumberLocked(String accountNumber) throws Exception {
		Account bankAccount = null;
		Criteria criteria = getSession().createCriteria(Account.class);
		criteria.add(Restrictions.eq("number", accountNumber));
		criteria.setLockMode(LockMode.PESSIMISTIC_WRITE);
		bankAccount = (Account) criteria.uniqueResult();
		return bankAccount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> get(long customerId) {
		List<Account> bankAccounts = null;
		Criteria criteria = getSession().createCriteria(Account.class, "a");
		criteria.createAlias("a.customer", "c");
		criteria.add(Restrictions.eq("c.id", customerId));
		bankAccounts = criteria.list();
		return bankAccounts;
	}

	public Account save(Account bankAccount) {
		return super.save(bankAccount);
	}
}
