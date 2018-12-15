package banking.alumnos.infrastructure.hibernate.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.users.domain.entity.User;
import banking.users.domain.repository.UserRepository;

@Transactional
@Repository
public class AlumnoHibernateRepository extends BaseHibernateRepository<Alumno> implements AlumnoRepository {
	public Alumno getById(long AlumnoId) {
		Alumno alumno = null;
		Criteria criteria = getSession().createCriteria(Alumno.class, "u");
		criteria.add(Restrictions.eq("u.id", alumnoId));
		alumno = (Alumno) criteria.uniqueResult();
		return alumno;
	}
	
	public Alumno getByName(String name) {
		Alumno alumno = null;
		Criteria criteria = getSession().createCriteria(Alumno.class, "u");
		alumno = (Alumno) criteria.uniqueResult();
		return alumno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Alumno> getPaginated(int page, int pageSize) {
		List<Alumno> alumnos = null;
		Criteria criteria = getSession().createCriteria(Alumno.class);
		criteria.setFirstResult(page);
		criteria.setMaxResults(pageSize);
		alumnos = criteria.list();
		return alumnos;
	}
	
	public Alumno save(Alumno alumno) {
		return super.save(alumno);
	}
}
