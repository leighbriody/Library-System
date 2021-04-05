<%-- 
    Document   : borrowBook
    Created on : 02-Jan-2021, 15:52:40
    Author     : Osama Kheireddine
--%>

<%@page import="java.util.ResourceBundle"%>
<%@page import="business.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="daos.BookDao"%>
<%@page import="business.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

        <!-- Bootstrap core CSS -->
        <link href="../../../../dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
        <link href="blog.css" rel="stylesheet">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <header class="blog-header py-3">
                <div class="row flex-nowrap justify-content-between align-items-center">
                    <div class="col-4 pt-1">
                        <a class="text-muted" href="#">The Library</a>
                    </div>

                    <div class="col-4 d-flex justify-content-end align-items-center">

                        <a class="btn btn-sm btn-outline-secondary" href="FactoryServlet?action=logout">Logout</a>
                    </div>
                </div>

            </header>

            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between nav-tabs">
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
                    <h1 class="display-4 font-italic"><%=dataBundle.getString("borrowBook_name")%></h1>
                    <p class="lead my-3"><%=dataBundle.getString("borrowBook_message")%></p>

                </div>
            </div>

       
        <form action="FactoryServlet" method="POST">
            <select name="bookId" class="form-control">
            <%
                // Get the user object from the session
                User u = (User) session.getAttribute("loggedInUser");

                // If the user has successfully logged in (and their session hasn't expired yet)
                // Then display a greeting to them
                if (u != null) {
                    //we want to take the books and display them
                    BookDao bDao = new BookDao("librarydb");
                    ArrayList<Book> books = bDao.viewAllBookDetails();
                    for (Book b : books) {
            %>
                <option  value="<%= b.getBookId() %>"><%= b.getBookName() %></option>
            <%
                }
            %>
            </select>
            <br>
            <button type="hidden" class="btn btn-primary" name="action" value="borrowBook">Borrow !</button>
           
        </form>
        <%
                // Otherwise, redirect them to the login page and tell them to try again
            } else {
                String sessionExpired = "Your session has expired, please log in again.";
                session.setAttribute("sessionExpired", sessionExpired);
                response.sendRedirect("login.jsp");
            }
        %>
        </div>
    </body>
</html>
