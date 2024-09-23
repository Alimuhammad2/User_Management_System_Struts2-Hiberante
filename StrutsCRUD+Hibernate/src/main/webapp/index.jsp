<%-- <%response.sendRedirect("userregister.jsp");  %> --%>

 <% 
    // Check if the session is null
     
    if (session.getAttribute("user1") != null) {
    
        // Redirect to user login page
        response.sendRedirect("userdata.jsp");
    } else {
        // User is logged in, you can proceed with loading the index page content
    	response.sendRedirect("userlogin.jsp"); 
    }
%>
