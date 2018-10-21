package org.trimatek.digideal.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Source;
import org.trimatek.digideal.model.utils.Config;

public class Repository {

	private EntityManager em;
	private EntityManagerFactory emf;
	private static Repository INSTANCE;

	private Repository() {
		com.objectdb.Enhancer.enhance("org.trimatek.digideal.model.Contract");
		com.objectdb.Enhancer.enhance("org.trimatek.digideal.model.Receipt");
		com.objectdb.Enhancer.enhance("org.trimatek.digideal.model.Transaction");
		com.objectdb.Enhancer.enhance("org.trimatek.digideal.model.Source");
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.user", Config.getValue("DB_USR"));
		properties.put("javax.persistence.jdbc.password", Config.getValue("DB_PSW"));
		emf = Persistence.createEntityManagerFactory(Config.getValue("DB_URL"), properties);
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

	public int setRunningFalse() {
		em.getTransaction().begin();
		int result = em.createNamedQuery("setRunningFalse", Contract.class).executeUpdate();
		em.getTransaction().commit();
		return result;
	}

	public Contract loadContract(String id) {
		em.getTransaction().begin();
		List<Contract> result = em.createNamedQuery("loadContract", Contract.class).setParameter("id", id)
				.getResultList();
		em.getTransaction().commit();
		return !result.isEmpty() ? result.get(0) : null;
	}

}
