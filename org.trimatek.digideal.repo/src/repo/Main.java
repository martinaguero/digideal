package repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Ticket;

public class Main {

	public static void main(String[] args) {

		com.objectdb.Enhancer.enhance("org.trimatek.digideal.model.Ticket");

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.user", "admin");
		properties.put("javax.persistence.jdbc.password", "admin");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb://localhost:6136/support.odb",
				properties);

		EntityManager em = emf.createEntityManager();

		Ticket t = new Ticket();
		t.setLocale("ES");
		
		 em.getTransaction().begin();
		 em.persist(t);
		 em.getTransaction().commit();

//		TypedQuery<Contract> query = em.createQuery("SELECT c FROM Contract c", Contract.class);
//		List<Contract> results = query.getResultList();
//		for (Contract c : results) {
//			System.out.println(c.getId());
//			//System.out.println("\t" + c.getLastPayTransaction().getRaw());
//		}

		em.close();
		emf.close();

	}

}