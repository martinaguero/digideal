package repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Transaction;

public class Main {

	public static void main(String[] args) {

		com.objectdb.Enhancer.enhance("org.trimatek.digideal.model.Contract,org.trimatek.digideal.model.Transaction");

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.user", "admin");
		properties.put("javax.persistence.jdbc.password", "admin");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb://localhost:6136/digideal.odb",
				properties);

		EntityManager em = emf.createEntityManager();

//		 Contract c = new Contract();
//		 Transaction t = new Transaction("signer", "raw");
//		 Transaction t1 = new Transaction("signer1", "raw1");
//		 c.addPayTransaction(t);
//		 c.addPayTransaction(t1);
//		
//		 em.getTransaction().begin();
//		 em.persist(c);
//		 em.getTransaction().commit();

		TypedQuery<Contract> query = em.createQuery("SELECT c FROM Contract c", Contract.class);
		List<Contract> results = query.getResultList();
		for (Contract c : results) {
			System.out.println(c.getId());
			System.out.println("\t" + c.getLastPayTransaction().getRaw());
		}

		em.close();
		emf.close();
		
//		Contract c = Repository.getInstance().loadContract(5);
		
//		System.out.println(c.getLastPayTransaction().getRaw());
		

	}

}