package org.trimatek.digideal.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Source;

public class Repository {

	private EntityManager em;
	private EntityManagerFactory emf;
	private static Repository INSTANCE;
	private static final String URL = "objectdb://localhost:6136/digideal.odb";
	private static final String USR = "admin";
	private static final String PSW = "admin";

	private Repository() {
		com.objectdb.Enhancer.enhance("org.trimatek.digideal.model.Contract");
		com.objectdb.Enhancer.enhance("org.trimatek.digideal.model.Receipt");
		com.objectdb.Enhancer.enhance("org.trimatek.digideal.model.Transaction");
		com.objectdb.Enhancer.enhance("org.trimatek.digideal.model.Source");
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.user", USR);
		properties.put("javax.persistence.jdbc.password", PSW);
		emf = Persistence.createEntityManagerFactory(URL, properties);
		em = emf.createEntityManager();
	}

	public static Repository getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Repository();
		}
		return INSTANCE;
	}

	public void save(Contract c) {
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
	}

	public Contract loadContract(long id) {
		return em.find(Contract.class, id);
	}

	public void save(Source d) {
		em.getTransaction().begin();
		em.persist(d);
		em.getTransaction().commit();
	}

	public void shutdown() {
		em.close();
		emf.close();
	}

	public List<Contract> loadUndoneContracts() {
		em.getTransaction().begin();
		List<Contract> results = em.createNamedQuery("loadUndoneContracts", Contract.class).setMaxResults(10)
				.getResultList();
		em.getTransaction().commit();
		return results;
	}

}
