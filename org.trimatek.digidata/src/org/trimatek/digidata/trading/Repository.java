package org.trimatek.digidata.trading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.trimatek.digidata.Config;
import org.trimatek.digidata.trading.model.Strategy;
import org.trimatek.digidata.trading.model.Trade;

public class Repository {

	private EntityManager em;
	private EntityManagerFactory emf;
	private static Repository INSTANCE;

	private Repository() {
		com.objectdb.Enhancer.enhance("org.trimatek.digidata.trading.model.Trade");
		com.objectdb.Enhancer.enhance("org.trimatek.digidata.trading.model.Strategy");
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.user", Config.TRADING_DB_USERNAME);
		properties.put("javax.persistence.jdbc.password", Config.TRADING_DB_PASSWORD);
		emf = Persistence.createEntityManagerFactory(Config.TRADING_URL, properties);
		em = emf.createEntityManager();
	}

	public static Repository getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Repository();
		}
		return INSTANCE;
	}

	public void save(Trade t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	public void save(Strategy s) {
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
	}

	public Trade loadTrade(long id) {
		return em.find(Trade.class, id);
	}

	public Strategy loadStrategy(long id) {
		return em.find(Strategy.class, id);
	}

	public void shutdown() {
		em.close();
		emf.close();
	}

	public List<Trade> loadTrades() {
		em.getTransaction().begin();
		List<Trade> result = em.createNamedQuery("LoadTrades", Trade.class).setMaxResults(Config.TRADING_MAX_RESULTS)
				.getResultList();
		em.getTransaction().commit();
		return result;
	}

	public Strategy loadStrategy(String name, String version) {
		em.getTransaction().begin();
		List<Strategy> result = em.createNamedQuery("LoadStrategyByVersion", Strategy.class).setParameter("name", name)
				.setParameter("version", version).getResultList();
		em.getTransaction().commit();
		return !result.isEmpty() ? result.get(0) : null;
	}

	public Strategy refreshStrategy(Strategy strategy) {
		em.getTransaction().begin();
		em.refresh(strategy);
		em.getTransaction().commit();
		return strategy;
	}

}
