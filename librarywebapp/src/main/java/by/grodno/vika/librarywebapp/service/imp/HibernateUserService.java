package by.grodno.vika.librarywebapp.service.imp;

import java.util.List;

import javax.activity.InvalidActivityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.grodno.vika.librarywebapp.domain.OldUser;
import by.grodno.vika.librarywebapp.service.UserRepository;

@Service
@Transactional(noRollbackFor = NullPointerException.class)
public class HibernateUserService implements UserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	static Boolean thrown = false;

	@Override
	public void addUser(List<OldUser> users) {
		int i = 0;
		for (OldUser user : users) {
			entityManager.persist(user);
			i++;

			if (i == 3 && thrown == false) {
				thrown = true;
				throw new IndexOutOfBoundsException();
				
			}
			if (i == 3) {
				thrown = true;
				throw new NullPointerException();
			}
		}
	}

	@Override
	public List<OldUser> getUsers() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<OldUser> cr = cb.createQuery(OldUser.class);

		return entityManager.createQuery(cr.select(cr.from(OldUser.class))).getResultList();

	}

	@Override
	public void deleteUser(Integer number) {
		entityManager.remove(new OldUser(number, null, null, null, false));
	}
}