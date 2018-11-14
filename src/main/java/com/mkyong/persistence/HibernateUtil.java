package com.mkyong.persistence;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import com.mkyong.models.Category;
import com.mkyong.models.Stock;
import com.mkyong.models.StockDailyRecord;
import com.mkyong.models.StockDetail;








/**
 * HibernateUtil shall be a singleton so as to minimize open sessions on the DB. 
 * To use, do getHibernateUtil()
 * @author Admin
 *
 */
public class HibernateUtil {
    
    private static final SessionFactory sf = buildSessionFactory();
    static Logger log = Logger.getRootLogger();
    /**
     * Many to Many relationship
     */
    public Stock addStockToCategories(Stock stock, Category...cats) {
        System.out.println("Hibernate many to many (XML Mapping)");
		try(Session session = sf.openSession()) {	
			session.beginTransaction();
//			stock = session.get(Stock.class, stock.getId()); //Unnessecary because the obj. would already be loaded by now.
			Hibernate.initialize(stock.getCategories());
			for(Category cat : cats) {
//				cat = session.get(Category.class, cat.getCategoryId());  // Same as above.
				stock.getCategories().add(cat);
			}
			session.getTransaction().commit();
			return stock;
		}
    }
    /**
     * Add stocks to categories when both objects are already in a persistent state.
     * @param cat
     * @param stocks
     * @return
     */
    public Category addCategoryToStocks(Category cat, Stock... stocks) {
		try(Session session = sf.openSession()) {	
			session.beginTransaction();
		//	cat = session.get(Category.class, cat.getCategoryId());
			Hibernate.initialize(cat.getStocks());
			for(Stock st : stocks) {
		//		st = session.get(Stock.class, st.getId());
		        cat.getStocks().add(st);
			}
			session.getTransaction().commit();
			return cat;
		}
    }
    
    /**
     * OneToMany relationship, with stock as the owner of the stockDailyRecords
     * @return
     */
    public StockDailyRecord newDailyRecord(Stock stock, StockDailyRecord stockDailyRecords) {
        try (Session session = sf.openSession()) {
            stock = session.get(Stock.class, stock.getId());
            
            stockDailyRecords.setStock(stock);        
            stock.getDailyRecords().add(stockDailyRecords);
            
            session.save(stockDailyRecords);
            return stockDailyRecords;
        }
    }
    
    /**
     * Saves mapped object into the DB
     * @param Object ( should be mapped... ) 
     * @return transient stock (tracked by hibernate) with DB id#
     */
    public Stock save(Stock stock) {
    	try (Session session = sf.openSession()){
    		
    	}
    }
    
    public <T> T save(T obj) {
        try (Session session = sf.openSession()) {
        	session.beginTransaction();
        	session.save(obj);
        	session.getTransaction().commit();   
        	return obj;
        }
    }
    
    /** 
     * Gets Stock Object by ID, just for reading data.
     */
	public <T> T read(Class<T> myClass, int id) {
		try(Session session = sf.openSession()) {
			return session.get(myClass, id);
		}
    }

	/**
	 * Get Object by Name - Only works if the object has a name field!
	 * @param color
	 * @return
	 */
	public <T> List<T> criteriaGetObjectsByField(Class<T> theClass, String search, String variableName) {
		// Criteria is a JPA spec.
		try(Session session = sf.openSession()){
			//Gets the Criteria Builder singleton instance - a utility class for creating critera
			CriteriaBuilder cb = session.getCriteriaBuilder();
			//Creating an instance of the CirteriaQuery object for type bear
			CriteriaQuery<T> initQuery = cb.createQuery(theClass);
			//Setting the root of the query - because we may be getting out info by joining data
			// it's necessary to specify which table the query actually begins on
			Root<T> root = initQuery.from(theClass);
			// the query logic itself
			initQuery
				.select(root)	// data to be returned is the root
				.where(cb.equal(root.get(variableName), search)); // filter applied equal operand (==) on the
															// root.name column with the value of 'color'
														// CHECK FOR OBJECT variable, not column!
			Query<T> query = session.createQuery(initQuery);
			List<T> results = query.getResultList();
			return results;
		}
	}
	/*
	 * Singleton-related Methods:
	 */
    private static final HibernateUtil hibernateUtil = new HibernateUtil();
    private HibernateUtil() {
    	super();
    }
    public static HibernateUtil getHibernateUtil() {
    	return hibernateUtil;
    }

    /**
     * Builds sessionFactory with AnnotatedClass configuration
     * @return
     */
	private static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration()
				.configure()
				// For XML route, were this t not use Annotations :
//				.setProperty("hibernate.connection.url", System.getenv("DB_URL"))
				.addAnnotatedClass(Stock.class)
				.addAnnotatedClass(StockDetail.class)
				.addAnnotatedClass(StockDailyRecord.class)
				.addAnnotatedClass(Category.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
	

    public static SessionFactory getSession() {
    	return sf;
    }
    public static void shutdown() {
    	// Close caches and connection pools
    	sf.close();
    }

}