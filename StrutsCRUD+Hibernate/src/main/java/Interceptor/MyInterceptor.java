package Interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import Entity.user;

import java.net.InetAddress;
import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.query.criteria.internal.expression.function.CurrentTimeFunction;
public class MyInterceptor implements Interceptor{

	 private static final Logger logger = LogManager.getLogger(MyInterceptor.class);
	 
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String intercept(ActionInvocation ai) throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String ipAddress = request.getRemoteAddr();
        
        logger.info("Before IP: " + ipAddress);
        
        LocalTime startTime  = LocalTime.now();
        logger.info("Starting Time: " + startTime);
        
        request.setAttribute("clientIP", ipAddress);
        
        String result =  ai.invoke();
        InetAddress ip = InetAddress.getLocalHost();
        LocalTime endTime  = LocalTime.now();
        logger.info("Ending Time: " + endTime);
        logger.info("After IP: " + ip);
		return result;
		
		
	/*	System.out.println("Before action: " + ai.getAction().getClass().getName());

        // Continue with action invocation
        String result = ai.invoke();

        System.out.println("After action: " + ai.getAction().getClass().getName());
  
        return result;*/
	}

}
