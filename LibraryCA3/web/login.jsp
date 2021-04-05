

<%@page import="java.util.ResourceBundle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
              
       <%@include file="internationalisationHeader.jsp" %>
       <%
            // Retrieve the resource bundle from the session
            ResourceBundle dataBundle = (ResourceBundle) session.getAttribute("dataBundle");
            // If there is no bundle stored (i.e. if this is the first time you're coming to the site)
            if(dataBundle == null){
                // Create a resource bundle based on the client's current locale settings
                dataBundle = ResourceBundle.getBundle("languages.librarydb", clientLocale);

                // Store this resource bundle for future use
                session.setAttribute("dataBundle", dataBundle);
            }
            
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=dataBundle.getString("login_login")%></title>
       <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <style>
            .login-form {
                width: 340px;
                margin: 50px auto;
                font-size: 15px;
            }
            .login-form form {
                margin-bottom: 15px;
                background: #f7f7f7;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                padding: 30px;
            }
            .login-form h2 {
                margin: 0 0 15px;
            }
            .form-control, .btn {
                min-height: 38px;
                border-radius: 2px;
            }
            .btn {        
                font-size: 15px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>



        <div class="login-form">

            <%
                // Check if they have landed here because their session expired
                String sessionExpired = (String) session.getAttribute("sessionExpired");
                // If so, then display the session expired message to the user
                if (sessionExpired != null) {
                    // Display the error message to the user
                    out.println("<b>" + sessionExpired + "</b>");
                    // Remove the message from the session as it's no longer useful
                    session.removeAttribute("sessionExpired");
                }
            %>
            <form action="FactoryServlet" method="post">
                <!-- Include a hidden field to identify what the user wants to do -->
                <input type="hidden" name ="action" value="login" />
                <h2 class="text-center"><%=dataBundle.getString("login_login")%></h2>
                <div class="form-group">
                    <input type="text" class="form-control"  name="username" placeholder="<%=dataBundle.getString("login_username")%>" required="required">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control"  name="password" placeholder="<%=dataBundle.getString("login_password")%>" required="required">
                </div>
                <div class="form-group">
                    <button type="submit" value="Login" class="btn btn-primary btn-block"><%=dataBundle.getString("login_button")%></button>
                </div>
                <div class="clearfix">

                    <a href="#" class="float-right"><%=dataBundle.getString("login_forgotPassword")%></a>
                </div>        
            </form>
            <p class="text-center"><a href="signUp.jsp"><%=dataBundle.getString("login_createAccount")%></a></p>

        
    </div>

   
</body>
</html>
