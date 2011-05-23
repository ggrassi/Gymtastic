package ch.hsr.gymtastic.technicalServices.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DBConnection {
	private EntityManager em;
	private EntityManagerFactory emf;
	private static String path = "$objectdb/db/gymtastic_"+System.currentTimeMillis()+".odb";


	public DBConnection(String path) {
		super();
		setPath(path);
		connect(path);
	}

	public void merge(Object o ){
		em.merge(o);
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
	
	public void insert(Object ob){
		em.persist(ob);
		commit();
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

	public static void setPath(String path) {
		DBConnection.path = path;
	}
	public static String getPath() {
		return path;
	}

	public void remove(Object o) {
		em.remove(o);
		
	}

}
