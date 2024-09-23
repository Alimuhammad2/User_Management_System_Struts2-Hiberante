package Entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class Logout {

	private static final Logger logger = LogManager.getLogger(Logout.class);
	
	public String execute() {
		
        HttpServletRequest request = ServletActionContext.getRequest();
        
        HttpSession session = request.getSession(false);
       
        if (session != null) { 
        	session.invalidate(); 
        	logger.info("Session Expired");
        	return "success"; 
        }
        return null; 
    }
	
}
