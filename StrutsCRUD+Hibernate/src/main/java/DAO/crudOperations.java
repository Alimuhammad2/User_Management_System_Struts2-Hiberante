package DAO;

import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Entity.user;
import Hibernate.HibernateUtils;

public class crudOperations {

	private static final Logger logger = LogManager.getLogger(crudOperations.class);

	public static String addStudent(String name, String email, String password) {

		Transaction tx = null;

		try {

			Session session = HibernateUtils.getSessionFactory().openSession();
			tx = session.beginTransaction();

			user user = new user();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			
			session.save(user);
			tx.commit();
			session.close();

			logger.info("User Registered Successfully");
			return "success";

		} catch (Exception e) {
			logger.error("Exception Message:" + e.getMessage());
			return "fail";
		}
	}

	public static user getUserByID(int id) {

		Session session = null;
		user user = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			user = session.get(user.class, id);

		} catch (Exception e) {
			logger.error("Exception Message: " + e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return user;
	}

	// add two parameters in this method
	public static List<user> getAllStudents(int pageNumber, int pageSize) {

		List<user> list = new ArrayList<user>();

		try {
			Session session = HibernateUtils.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query<user> query = session.createQuery("FROM user", user.class);

			// agr in dono 77 or 78 line ko comment kr diya jae toh sare records show honge har page per.
			query.setFirstResult((pageNumber - 1) * pageSize); // Start from (pageNumber-1) pageSize												
			query.setMaxResults(pageSize);

			// list = session.createQuery("FROM user", user.class).list();

			list = query.list();

			tx.commit();
			logger.info("All users retrieved successfully.");

		} catch (Exception e) {
			logger.error("Exception Message: " + e);
		}
		return list;
	}

	// add new method to count total users
	public static long countTotalUsers() {
		
		long count = 0;

		try {
			Session session = HibernateUtils.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();

			Query<Long> query = session.createQuery("select count(*) from user", Long.class);
			count = query.uniqueResult();

			tx.commit();
			logger.info("Total user count: " + count);

		} catch (Exception e) {
			logger.error("Exception Message: " + e);
		}

		return count;
	}

	public static String updateUser(user u) {

		Transaction tx = null;
		Session session = null;
		
		try {

			session = HibernateUtils.getSessionFactory().openSession();
			user user = session.get(user.class, u.getId());
			if (user != null) {

				tx = session.beginTransaction();
				user.setName(u.getName());
				user.setEmail(u.getEmail());
				user.setPassword(u.getPassword());
				session.update(user);
				tx.commit();
				logger.info("User updated successfully");
				return "success";
			}
		} catch (Exception e) {
			logger.error("Exception Message: " + e);
		}
		return "success";
	}

	public static String deletestudent(int id) {

		Transaction tx = null;
		Session session = null;
		try {

			session = HibernateUtils.getSessionFactory().openSession();
			user u = session.get(user.class, id);
			if (u != null) {
				tx = session.beginTransaction();
				session.remove(u);
				tx.commit();
				logger.info("User Deleted Successfully..!");
				return "success";
			} else {
				logger.info("User Not Deleted Successfully..!");
				return "fail";
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Exception Message: " + e.getMessage());
			return "fail";
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static user getUserDetailsById(int id) {

		user user = null;
		Session session = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			// session.beginTransaction();

			user = session.get(user.class, id);

			if (user != null) {
				logger.info("Login Successful..!");
			} else {
				logger.error("Login Failed: Invalid username or password");
			}

			session.getTransaction().commit();

		} catch (Exception e) {
			logger.error("Exception Message: " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return user;
	}

	
	//ab ek or kam krna hai jb name likhna start krain toh suggestion aana start hojae
	//sarch bar k ander filter lana hai take search name se jo bhi data ho table mein show hojae.
	
	
	
	
	
	
	
	// ye kam krna hai;

	// pagination wala kam krna hai 20 recoard mangwany hain backend k through
	// example 100 recaord hain db mein toh 20
	// records at a time aaen or screen per show honjaen agr user next per click
	// kre toh phir dosre 20 recoards
	// usko show hojaen
	
	
	
}
