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
	private static String path = "$objectdb/db/gymtastic_"
			+ System.currentTimeMillis() + ".odb";

	/**
	 * Instantiates a new dB connection.
	 * 
	 * @param path
	 *            the path
	 */
	public DBConnection(String path) {
		super();
		setPath(path);
		connect(path);
	}
	
	/**
	 * Instantiates a new dB connection.
	 */
	public DBConnection() {
		super();
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
		this.emf = Persistence.createEntityManagerFactory(path);
		this.em = emf.createEntityManager();
		startTransaction();
	}

	/**
	 * Starts a DB transaction.
	 */
	private void startTransaction() {
		this.em.getTransaction().begin();
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

	/**
	 * Sets the path to the objectDB File
	 * 
	 * @param path
	 *            the new path
	 */
	public static void setPath(String path) {
		DBConnection.path = path;
	}

	/**
	 * Gets the path of the objectDB File
	 * 
	 * @return the path
	 */
	public static String getPath() {
		return path;
	}

}
