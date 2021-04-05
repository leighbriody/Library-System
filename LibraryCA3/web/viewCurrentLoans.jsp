<%-- 
    Document   : viewCurrentLoans
    Created on : 30-Dec-2020, 21:52:15
    Author     : ME
--%>

<%@page import="java.util.ResourceBundle"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="business.Book"%>
<%@page import="business.Loan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="daos.BookDao"%>
<%@page import="daos.LoanDao"%>
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
        <title><%=dataBundle.getString("currentLoans_title")%></title>

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
                        <a href="home.jsp"><%=dataBundle.getString("home_name")%></a>
                    </div>

                    <div class="col-4 d-flex justify-content-end align-items-center">

                         <a class="btn btn-sm btn-outline-secondary" href="FactoryServlet?action=logout"><%=dataBundle.getString("menu_logout")%></a>
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
                    <a class="p-2 text-muted" href="#"><%=dataBundle.getString("menu_OverdueFees")%></a>
                    <a class="p-2 text-muted" href="#"><%=dataBundle.getString("menu_payFees")%></a>

                </nav>
            </div>
            <%
                //get user object from the session
                User u = (User) session.getAttribute("loggedInUser");

                //if the user is not empty then they're logged in, provide the following
                if (u != null) {
                    //create a loan dao
                    LoanDao lDao = new LoanDao("librarydb");
                    //create a book dao to get the book names
                    BookDao bDao = new BookDao("librarydb");
                    //populate an arraylist containing all active loans
                    ArrayList<Loan> currentLoans = lDao.allLoansActive(u.getUserId());
                    if (currentLoans.size() < 1) {
            %>
            <!--the above if statement is needed for design purposes-->
            <div class="jumbotron p-3 p-md-5 text-white rounded bg-dark">
                <div class="col-md-6 px-0">
                    <h1 class="display-4 font-italic"><%=u.getUsername()%>'s <%=dataBundle.getString("currentLoans_loans")%> </h1>
                    <p class="lead my-3">You have no active loans</p>
                </div>
                <%
                } else {%>
                <div class="jumbotron p-3 p-md-5 text-white rounded bg-dark">
                    <div class="col-md-6 px-0">
                        <h1 class="display-4 font-italic"><%=u.getUsername()%>'s <%=dataBundle.getString("currentLoans_loans")%> </h1>
                        <p class="lead my-3"><%=dataBundle.getString("currentLoans_msg1")%> <i><%= currentLoans.size()%></i> <%=dataBundle.getString("currentLoans_mgs2")%></p>
                    </div>
                </div>
                <!--now to create a table as there are loans present-->
                <table class="table">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col"><%=dataBundle.getString("currentLoans_book")%></th>
                            <th scope="col"><%=dataBundle.getString("currentLoans_LoanDate")%></th>
                            <th scope="col"><%=dataBundle.getString("currentLoans_DueDate")%></th>
                            <th scope="col"><%=dataBundle.getString("currentLoans_Overdue")%></th>
                        </tr>
                    </thead>
                    <!--now to loop and print out all details-->
                    <%
                        for (Loan l : currentLoans) {
                            //this is to get the book name
                            Book b = bDao.getBookById(l.getBookId());
                            //this is to state if the overdue field is active
                            boolean overDue = false;
                            //need to create a date format
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            //need current and due date
                            String dueDate = l.getDueDate();

                            Date date1 = format.parse(dueDate);
                            Date date2 = new Date();
                            //if the duedate is earlier than the current date
                            if (date2.compareTo(date1) <= 0) {
                    %>
                    <!--Create a row for each book-->
                    <tbody>

                        <tr>
                            <td><%= b.getBookName()%></td>
                            <td><%= l.getDateTaken()%></td>
                            <td><%= dueDate%></td>

                        </tr>
                        <%
                        } else {%>
                        <tr>
                            <td><%= b.getBookName()%></td>
                            <td><%= l.getDateTaken()%></td>
                            <td><%= dueDate%></td>
                            <td class="text-danger">OVERDUE</td>
                        </tr>
                        <%}
                        %>
                        <% }
                                }
                            }
                        %>
                </table>
            </div>
        </div>
                </body>
                </html>
