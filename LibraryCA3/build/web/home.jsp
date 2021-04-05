<%-- 
    Document   : home
    Created on : 08-Dec-2020, 23:19:00
    Author     : Brianan Johnson
--%>
<!--




-->
<%@page import="java.util.ResourceBundle"%>
<%@page import="business.User"%>
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
        <title><%=dataBundle.getString("home_title")%></title>
       <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <!-- Bootstrap core CSS -->
        <link href="../../../../dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
        <link href="blog.css" rel="stylesheet">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>

 <div>
            <form action="controller.jsp" method="post">
               
                <!-- Include a hidden field to identify what the user wants to do -->
                <input type="hidden" name ="action" value="update" />
            </form>
        </div>
        <!--
        <%
            // Get the user object from the session
            User user = (User) session.getAttribute("loggedInUser");
            // If the user has successfully logged in (and their session hasn't expired yet)
            // Then display a greeting to them
            if (user != null) {
        %>
         
        <div></div>
        <a href="LibraryServlet?action=logout">Logout</a>
       Include a footer so that there is always a link back to the home page! -->
        <div class="container">
            <header class="blog-header py-3">
                <div class="row flex-nowrap justify-content-between align-items-center">
                    <div class="col-4 pt-1">
                        <a href="home.jsp"><%=dataBundle.getString("home_name")%></a>
                    </div>

                    <div class="col-4 d-flex justify-content-end align-items-center">

                        <a class="btn btn-sm btn-outline-secondary" href="FactoryServlet?action=logout"><%=dataBundle.getString("menu_logout")%></a>
                    </div>
                </div>

            </header>

            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">

                    <a class="p-2 text-muted" href="FactoryServlet?action=displayBooks"><%=dataBundle.getString("menu_viewBooks")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=displayBooksAndAuthors"><%=dataBundle.getString("menu_viewBooksByAuthor")%></a>
                    <a class="p-2 text-muted" href="searchBook.jsp"><%=dataBundle.getString("menu_searchBooks")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=borrowBook"><%=dataBundle.getString("menu_borrowBook")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=returnBook"><%=dataBundle.getString("menu_returnBook")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=viewUserDetails"><%=dataBundle.getString("menu_viewProfile")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=changeUserDetails"><%=dataBundle.getString("menu_editProfile")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=viewCurrentLoans"><%=dataBundle.getString("menu_currentLoans")%></a>
                    <a class="p-2 text-muted" href="FactoryServlet?action=viewPreviousLoans"><%=dataBundle.getString("menu_previousLoans")%></a>
                  <a class="p-2 text-muted" href="FactoryServlet?action=OverdueFees"><%=dataBundle.getString("menu_OverdueFees")%></a>
                    <a class="p-2 text-muted" href="#"><%=dataBundle.getString("menu_payFees")%></a>

                </nav>
            </div>

            <div class="jumbotron p-3 p-md-5 text-white rounded bg-dark">
                <div class="col-md-6 px-0">
                    <h1 class="display-4 font-italic"> <td> <%=dataBundle.getString("home_welcome")%>  <%=user.getUsername()%></h1>
                    <p class="lead my-3"><%=dataBundle.getString("home_deliver")%></p>
                    <p class="lead mb-0"><a href="#" class="text-white font-weight-bold"><%=dataBundle.getString("home_continue")%></a></p>
                </div>
            </div>

            

            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
            <script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
            <script src="../../../../assets/js/vendor/popper.min.js"></script>
            <script src="../../../../dist/js/bootstrap.min.js"></script>
            <script src="../../../../assets/js/vendor/holder.min.js"></script>
            <script>
                Holder.addTheme('thumb', {
                    bg: '#55595c',
                    fg: '#eceeef',
                    text: 'Thumbnail'
                });
            </script>



            <%
                } // Otherwise, redirect them to the login page and tell them to try again
                else {
                    String sessionExpired = dataBundle.getString("error_sessionExpired");
                    session.setAttribute("sessionExpired", sessionExpired);
                    response.sendRedirect("login.jsp");
                }
            %>  




        </div>    
    </body>
</html>

