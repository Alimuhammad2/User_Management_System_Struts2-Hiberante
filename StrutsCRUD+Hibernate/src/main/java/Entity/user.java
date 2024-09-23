package Entity;

import java.net.InetAddress;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.opensymphony.xwork2.ActionSupport;

import DAO.crudOperations;
import Hibernate.HibernateUtils;

@Entity
@Table(name = "register")
public class user extends ActionSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Transient
	private user u;
	@Transient
	private List<user> userList;

	private String name;
	private String email;
	private String password;
	
	
	
	//pagination attributes
	@Transient
	private int pageNumber = 1;  // Default/Starting page number
	@Transient
	private int pageSize = 5;   // Show 5 records per page
	@Transient
	private long totalUsers;     // Total number of users
	@Transient
	private int totalPages; 
    
	
	//for search
	@Transient
	private String searchQuery;
    
    
	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
    
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(long totalUsers) {
		this.totalUsers = totalUsers;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	
	
	
	
	
	public List<user> getUserList() {
		return userList;
	}

	public void setUserList(List<user> userList) {
		this.userList = userList;
	}

	public user getU() {
		return u;
	}

	public void setU(user u) {
		this.u = u;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "user [id=" + id + ", u=" + u + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

	private static final Logger logger = LogManager.getLogger(user.class);

	public String Add() {

		String result = crudOperations.addStudent(name, email, password);

		if ("success".equals(result)) {
			addActionMessage("User Registered Successfully!");
			return SUCCESS;
		} else {
			addActionError("User Registration Failed.");
			return "fail";
		}
	}

	public String getAllStudents() {
		
		
		
		totalUsers = crudOperations.countTotalUsers();
		 totalPages = (int) Math.ceil((double) totalUsers / pageSize);
		 userList = crudOperations.getAllStudents(pageNumber, pageSize);
		
		
		 if (userList != null) {
			return SUCCESS;
		} else {
			return "fail";
		}
	}

	public String updateStudent() {
		u = crudOperations.getUserByID(id);
		if (u != null) {
			crudOperations.updateUser(u);
			return "success";
		}
		return "fail";
	}

	// Yeh method updated student ko save karta hai

	public String updatedStudent() {

		user u = new user();
		u.setId(this.getId());
		u.setName(this.getName());
		u.setEmail(this.getEmail());
		u.setPassword(this.getPassword());

		crudOperations.updateUser(u);

		return "success";
	}

	public String deletedstudent() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("id"));
		crudOperations.deletestudent(id);
		return SUCCESS;
	}

	public String loginuser() {

		user u1 = null;
		Session session1 = null;

		try {

			session1 = HibernateUtils.getSessionFactory().openSession();
			session1.beginTransaction();

			Query<user> query = session1.createQuery("FROM user WHERE name = :name AND password = :password",
					user.class);
			query.setParameter("name", getName());
			query.setParameter("password", getPassword());

			u1 = query.uniqueResult();

			if (u1 != null) {
				crudOperations.getUserDetailsById(u1.getId());
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();

				try {

					// set user1 object in session
					session.setAttribute("user1", u1);

					logger.info("User Data Save in Session: " + u1.getName());

					String clientIP = (String) request.getAttribute("clientIP");

					// getting system IP
					InetAddress ip = InetAddress.getLocalHost();
					
					logger.info("Logged In User Id: " + u1.getId() + " Logged In User Name: " + u1.getName()
					+ " Logged In User Email: " + u1.getEmail() + " Logged In User Password: "
					+ u1.getPassword());

					//logger.info(clientIP);
					logger.info("System IP: " + ip);

					return "success";

				} catch (Exception e) {
					logger.error("Exception Message: " + e);
					return "fail";
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "success";
	}
	
	
	//this method is for search user by name
	public String searchUser(){
		
		Session session = null;
		
		try{
			
			session = HibernateUtils.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "FROM user WHERE name LIKE :searchQuery";
			Query<user> query = session.createQuery(hql, user.class);
			query.setParameter("searchQuery", "%" + searchQuery + "%");
			
			userList = query.list();
			
			return SUCCESS;
			
		}catch(Exception e){
			logger.error("Exception Message: " + e.getMessage());
			return "fail";
		}finally {
	        if (session != null) {
	            session.close();  
	        }
	    }
		
	}

	

}
