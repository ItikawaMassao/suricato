package br.usp.suricato.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usp.suricato.models.PostIt;

@Repository
public class PostItDao {

	@PersistenceContext
	private EntityManager manager;

	public void saveAsync(PostIt postIt) {
		EntityManagerFactory managerFactory = manager.getEntityManagerFactory();
		EntityManager entityManager = managerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(postIt);	
		transaction.commit();
	}

	public void removeAsync(PostIt postIt) {
		EntityManagerFactory managerFactory = manager.getEntityManagerFactory();
		EntityManager entityManager = managerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.createQuery("delete from PostIt where id = :id").setParameter("id", postIt.getId()).executeUpdate();
		transaction.commit();		
	}

	public PostIt load(int id) {
		return manager.find(PostIt.class, id);
	}

	public void updateAsync(PostIt postIt) {
		EntityManagerFactory managerFactory = manager.getEntityManagerFactory();
		EntityManager entityManager = managerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(postIt);
		transaction.commit();
	}

}
