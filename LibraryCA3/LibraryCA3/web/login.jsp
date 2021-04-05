<%-- 
    Document   : login
    Created on : 08-Dec-2020, 23:12:44
    Author     : Brianan Johnson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <div>
            <p>Please login here</p>
            <%
                // Check if they have landed here because their session expired
                String sessionExpired = (String) session.getAttribute("sessionExpired");
                // If so, then display the session expired message to the user
                if(sessionExpired != null){
                    // Display the error message to the user
                    out.println("<b>" + sessionExpired + "</b>");
                    // Remove the message from the session as it's no longer useful
                    session.removeAttribute("sessionExpired");
                }
            %>
            <form action="LibraryServlet" method="post">
                <table>
                    <tr>
                        <td> Username  : </td><td> <input name="username" required size=20 type="text" /> </td> 
                    </tr>
                    <tr>
                        <td> Password  : </td><td> <input name="password" required size=50 type="password" /> </td> 
                    </tr>
                </table>
                <input type="submit" value="Login" />
                <!-- Include a hidden field to identify what the user wants to do -->
                <input type="hidden" name ="action" value="login" />
            </form>
        </div>
        
        <!-- Include a footer so that there is always a link back to the home page! -->
     
    </body>
</html>
