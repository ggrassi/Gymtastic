package ch.hsr.gymtastic.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Main {
	public static void main(String[] args) {
		// Open a database connection
		// (create a new database if it doesn't exist yet):
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("$objectdb/db/points.odb");
		EntityManager em = emf.createEntityManager();

		// Store 1000 Point objects in the database:
		em.getTransaction().begin();
		for (int i = 0; i < 10; i++) {
			Squad s = new Squad(i);
			Athlete a = new Athlete(i, 3, "pg", "name", "vorname", "adresse",
					1900, new Association("name", "location"));
			em.persist(s);
			em.persist(a);
			s.addAthlet(a);
			em.persist(s);
		}
		em.getTransaction().commit();

		// Find the number of Point objects in the database:
		Query q1 = em.createQuery("SELECT COUNT(p) FROM Squad p");
		System.out.println("Total SQUAD: " + q1.getSingleResult());

		// Find the average X value:
		Query q2 = em.createQuery("SELECT COUNT(p) FROM Athlet p");
		System.out.println("TOTAL ATHLET: " + q2.getSingleResult());

		
		// Remove an Object from DB
		em.getTransaction().begin();
		Squad removable = new Squad(2);
		Squad squad = em.find(Squad.class, removable.getSquadId());
		em.remove(squad);
		em.getTransaction().commit();
		System.out.println("squad 2 removed");
		
		// Retrieve all the Squad objects
		TypedQuery<Squad> query = em.createQuery("SELECT p FROM Squad p",
				Squad.class);
		List<Squad> results = query.getResultList();
		for (Squad p : results) {
			System.out.println("" + p.getSquadId() + " " + p.getAthlets());
		}
		
		
		// Update an athlete mark
		em.getTransaction().begin();
		
		Athlete temp = new Athlete();
		temp.setStartNr(1);
	    Athlete foundAthlete = em.find(Athlete.class, temp.getStartNr());
	    foundAthlete.addMark(DeviceType.POMMEL_HORSE,new Mark(4,4,4,4,4,4)); 
	    em.getTransaction().commit();
		

		// Close the database connection:
		em.close();
		emf.close();
	}
}