package ch.hsr.gymtastic.technicalServices.database;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ch.hsr.gymtastic.domain.Mark;
import ch.hsr.gymtastic.domain.Squad;

public class DBConnection {
	private EntityManager em;
	private EntityManagerFactory emf;
	private String path = "$objectdb/db/gymtastic.odb";
	private Query query;
	private Mark mark;

	public DBConnection(String path) {
		super();
		connect(path);
		this.mark = new Mark(4, 5, 3, 5, 3, 4);
	}

	public void closeConnection() {

		em.close();
		emf.close();
	}

	public DBConnection() {
		super();
		connect(path);
	}

	public Query querySQL(String sqlQuery) {
		return em.createQuery(sqlQuery);
	}

	public void commit() {
		em.getTransaction().commit();
	}

	public void insert(Map<Integer, Squad> squads) {
		Collection<Squad> c = squads.values();
		Iterator<Squad> it = c.iterator();
		while (it.hasNext()) {
			Squad temp;
			em.persist(temp = it.next());
		}
		commit();

	}

	public void getAllSquads() {
		TypedQuery<Squad> query = em.createQuery("SELECT s FROM Squad s",
				Squad.class);
		for (Squad sq : query.getResultList()) {
			System.out.println(sq);
		}
	}

	private void connect(String path) {
		this.emf = Persistence.createEntityManagerFactory(path);
		this.em = emf.createEntityManager();
		startTransaction();
	}

	private void startTransaction() {
		this.em.getTransaction().begin();
	}

	public void persist(Object o) {
		em.persist(o);
	}

	public EntityManager getEm() {
		return em;
	}

	public <T> TypedQuery<T> createQuery(String str, Class<T> cla) {
		return em.createQuery(str, cla);
	}

}
