<%-- 
    Document   : index
    Created on : 08-Dec-2020, 22:58:39
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

        <% //Check if user logged in is not equal to null
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if (loggedInUser != null) {

        %>
        <div>
            <h2>Welcome, <%=loggedInUser.getUsername()%></h2>
        </div>
        <div>
            <a href="LibraryServlet?action=logout">Logout</a>
        </div>
        <%
        } else {
        %>
        <div>
            <p>
                Click Here To Login:</br>
                <a href="login.jsp">Login </a>
            </p>
        </div>

        <div>
            <p>
                Click Here To Sign Up:</br>
                <a href="signUp.jsp">Sign Up</a>
            </p>
        </div>

        <%
            }
        %>

        <div>
            
        </div>



    </body>
</html>
