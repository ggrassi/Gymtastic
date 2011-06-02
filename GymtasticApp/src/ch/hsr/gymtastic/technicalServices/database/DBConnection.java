package ch.hsr.gymtastic.technicalServices.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The Class DBConnection offers all the methods you need to CRUD on the DB.
 */
public class DBConnection {

	private EntityManager em;
	private EntityManagerFactory emf;
	private static String path = "";

	/**
	 * Instantiates a new dB connection.
	 * 
	 * @param path
	 *            the path
	 */
	public DBConnection(String path) {
		setPath(path);
		connect(path);
	}

	/**
	 * Instantiates a new dB connection.
	 */
	public DBConnection() {
		connect(path);
	}

	/**
	 * Closes the DB connection.
	 */
	public void closeConnection() {
		em.close();
		emf.close();
	}

	/**
	 * Commits the changes into the DB
	 */
	public void commit() {
		em.getTransaction().commit();
	}

	private void connect(String path) {
		emf = Persistence.createEntityManagerFactory(path);
		em = emf.createEntityManager();
		startTransaction();
	}

	/**
	 * Starts a DB transaction.
	 */
	private void startTransaction() {
		em.getTransaction().begin();
	}

	/**
	 * Persists an Object into the DB
	 * 
	 * @param o
	 *            the o
	 */
	public void persist(Object o) {
		em.persist(o);
	}

	/**
	 * Gets the EntityManager
	 * 
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	public static void setPath(String path) {
		DBConnection.path = path;
	}

	public static String getPath() {
		return path;
	}
	
	/**
	 * Removes the Object from DB
	 * 
	 * @return the em
	 */
	public void remove(Object o) {
		em.remove(o);
	}

}
