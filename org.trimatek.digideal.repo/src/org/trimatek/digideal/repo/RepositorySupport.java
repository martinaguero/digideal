package org.trimatek.digideal.repo;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.trimatek.digideal.model.Ticket;
import org.trimatek.digideal.model.utils.Config;

public class RepositorySupport {

	private EntityManager em;
	private EntityManagerFactory emf;
	private static RepositorySupport INSTANCE;

	private RepositorySupport() {
		com.objectdb.Enhancer.enhance("org.trimatek.digideal.model.Ticket");
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.user", Config.getValue("DB_USR_SUPPORT"));
		properties.put("javax.persistence.jdbc.password", Config.getValue("DB_PSW_SUPPORT"));
		emf = Persistence.createEntityManagerFactory(Config.getValue("DB_URL_SUPPORT"), properties);
		em = emf.createEntityManager();
	}

	public static RepositorySupport getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RepositorySupport();
		}
		return INSTANCE;
	}

	public void save(Ticket t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	public Ticket loadTicket(long id) {
		return em.find(Ticket.class, id);
	}

	public void shutdown() {
		em.close();
		emf.close();
	}

}
