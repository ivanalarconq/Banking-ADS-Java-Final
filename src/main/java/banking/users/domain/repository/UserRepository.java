package banking.users.domain.repository;

import java.util.List;

import banking.common.domain.repository.BaseRepository;
import banking.users.domain.entity.User;

public interface UserRepository extends BaseRepository<User> {

	public User getById(long userId);

	public User getByName(String name);

	public List<User> getPaginated(int page, int pageSize);
}
