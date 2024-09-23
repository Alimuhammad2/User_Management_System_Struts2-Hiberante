package Hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	private static SessionFactory sessionfactory;
	private static Logger logger = LogManager.getLogger();
	
	static{
		try{
			sessionfactory = new Configuration().configure().buildSessionFactory();
		}catch(Exception e){
			logger.error("Exception Message: " + e.getMessage());
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionfactory;
	}
	
}
