<%-- 
    Document   : home
    Created on : 08-Dec-2020, 23:19:00
    Author     : Brianan Johnson
--%>


<%@page import="business.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
      <%  
      // Get the user object from the session
            User user = (User) session.getAttribute("loggedInUser");
            // If the user has successfully logged in (and their session hasn't expired yet)
            // Then display a greeting to them
            if(user != null){
        %>
            <h1>Welcome, <%=user.getUsername()%></h1>
        <%
            }
            // Otherwise, redirect them to the login page and tell them to try again
            else{
                String sessionExpired = "Your session has expired, please log in again.";
                session.setAttribute("sessionExpired", sessionExpired);
                response.sendRedirect("login.jsp");
            }
        %>  
        
      <div></div>
        <a href="LibraryServlet?action=logout">Logout</a>
        <!-- Include a footer so that there is always a link back to the home page! -->
       
        
    </body>
</html>
  
