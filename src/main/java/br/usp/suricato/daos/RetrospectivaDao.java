package br.usp.suricato.daos;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usp.suricato.models.Retrospectiva;

@Repository
public class RetrospectivaDao {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PostItDao postItDao;
	
	@Autowired
	private ComentarioDao comentarioDao;

	public void salva(Retrospectiva retrospectiva) {
		manager.persist(retrospectiva);
	}

	public List<Retrospectiva> listaRetrospectivasAbertasDoUsuario(Integer idUsuario) {
		return manager.createQuery("select r from Retrospectiva r where r.criador.id = :id and (r.dataFim >= :hoje or r.dataFim is null)", Retrospectiva.class)
					.setParameter("id", idUsuario)
					.setParameter("hoje", LocalDate.now())
					.getResultList();
	}
	
	public List<Retrospectiva> listaRetrospectivasEncerradasDoUsuario(Integer idUsuario) {
		return manager.createQuery("select r from Retrospectiva r where r.criador.id = :id and r.dataFim < :hoje", Retrospectiva.class)
				.setParameter("id", idUsuario)
				.setParameter("hoje", LocalDate.now())
				.getResultList();
	}

	public Retrospectiva load(Integer id) {
		return (Retrospectiva) manager.createQuery("select r from Retrospectiva r join fetch r.lousa left join fetch r.postIts "
				+ "left join fetch r.comentarios join fetch r.criador left join fetch r.time where r.id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}

	public List<Retrospectiva> listaRetrospectivasAbertasDoTime(Integer idTime) {
		return manager.createQuery("select r from Retrospectiva r where r.time.id = :id and (r.dataFim >= :hoje or r.dataFim is null)", Retrospectiva.class)
				.setParameter("id", idTime)
				.setParameter("hoje", LocalDate.now())
				.getResultList();
	}

	public List<Retrospectiva> listaRetrospectivasEncerradasDoTime(Integer idTime) {
		return manager.createQuery("select r from Retrospectiva r where r.time.id = :id and r.dataFim < :hoje", Retrospectiva.class)
				.setParameter("id", idTime)
				.setParameter("hoje", LocalDate.now())
				.getResultList();
	}
	
}
