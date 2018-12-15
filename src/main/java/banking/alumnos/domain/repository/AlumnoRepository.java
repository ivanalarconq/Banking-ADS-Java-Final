package banking.alumnos.domain.repository;

import java.util.List;

import banking.common.domain.repository.BaseRepository;
import banking.users.domain.entity.User;

public interface AlumnoRepository extends BaseRepository<Alumno> {

	public Alumno getById(long alumnoId);

	public Alumno getByName(String name);

	public List<Alumno> getPaginated(int page, int pageSize);
}
